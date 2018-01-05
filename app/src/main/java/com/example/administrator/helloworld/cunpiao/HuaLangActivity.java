package com.example.administrator.helloworld.cunpiao;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.helloworld.R;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/2.
 */

public class HuaLangActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.container)
    LinearLayout ll;
    private int mScreenWidth;
    private List<View> viewlist = new ArrayList<>();
    private VpAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.hualang_layout;
    }

    @Override
    protected boolean isOpenStatus() {
        return false;
    }

    @Override
    protected void init() {
        for (int i = 0; i < 8; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.hualang_item_viewpager_layout, null);
            viewlist.add(view);
        }

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;//屏幕宽度

        mViewPager.setOffscreenPageLimit(8);//设置缓存数量
        mViewPager.setPageMargin(15);//设置间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                mScreenWidth / 5, ViewPager.LayoutParams.WRAP_CONTENT);
        mViewPager.setLayoutParams(params);

        adapter = new VpAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setCurrentItem(0);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }


    class VpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewlist.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(viewlist.get(position));
            return viewlist.get(position);
        }
    }

    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.9f;
        private static final float MIN_ALPHA = 0.5f;

        private float defaultScale = 0.9f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
//                view.setAlpha(0);
                view.setScaleX(defaultScale);
                view.setScaleY(defaultScale);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
//                view.setAlpha(MIN_ALPHA +
//                        (scaleFactor - MIN_SCALE) /
//                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
//                view.setAlpha(0);
                view.setScaleX(defaultScale);
                view.setScaleY(defaultScale);
            }
        }
    }
}

