package com.example.administrator.helloworld.cunpiao;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.helloworld.R;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class BuyTicketActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {
    @BindView(R.id.group)
    public RadioGroup group;
    @BindView(R.id.banner)
    public Banner banner;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    private List<String> imageList;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.buyticket_layout;
    }

    @Override
    protected void init() {
        super.init();
        group.getChildAt(0).performClick();

        list.add(MyFragment.getInstance("1"));
        list.add(MyFragment.getInstance("2"));


        imageList = new ArrayList<>();
        imageList.add("http://img2.niutuku.com/desk/anime/0105/0105-12596.jpg");
        imageList.add("http://wallpapers2.hellowallpaper.com/game_heroes-of-might-and-magic-ol_08-1920x1440.jpg");
        imageList.add("http://wallpapers1.hellowallpaper.com/art_black-wallpaper--01_04-1680x1050.jpg");
        imageList.add("http://bbs.zhezhe168.com/data/attachment/forum/201601/03/164636jp373notn9js9o0m.jpg");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageList);
        banner.setBannerAnimation(Transformer.Stack);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
//        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        group.setOnCheckedChangeListener(this);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected boolean isOpenStatus() {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.button1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        group.getChildAt(position).performClick();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(BuyTicketActivity.this).load(path).into(imageView);
        }
    }
}
