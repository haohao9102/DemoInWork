package com.example.administrator.helloworld.viewpagerandgrideview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.helloworld.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ViewPagerAndGrideView extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewGroup points;
    private ImageView[] ivPoints;//小圆点图片集合
    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页

    private String[] proName = {"名称0", "名称1", "名称2", "名称3", "名称4", "名称5",
            "名称6", "名称7", "名称8", "名称9", "名称10", "名称11", "名称12", "名称13",
            "名称14", "名称15", "名称16", "名称17", "名称18", "名称19"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_grideview_layout);
        initView();

        setDatas();
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.grideview_viewpager_layout, viewPager, false);
            gridView.setAdapter(new MyGrideViewAdapter(this, listDatas, mPageSize, i));
            viewPagerList.add(gridView);
        }

        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < ivPoints.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));

            if (i == 0){
                imageView.setBackgroundResource(R.drawable.page__selected_indicator);
            }else{
                imageView.setBackgroundResource(R.drawable.page__normal_indicator);
            }
            ivPoints[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;//设置点点点view的左边距
            layoutParams.rightMargin = 10;//设置点点点view的右边距
            points.addView(imageView,layoutParams);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivPoints.length; i++) {
                    if (i == position) {
                        ivPoints[i].setBackgroundResource(R.drawable.page__selected_indicator);
                    } else {
                        ivPoints[i].setBackgroundResource(R.drawable.page__normal_indicator);
                    }
                }
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDatas() {
        listDatas = new ArrayList<>();
        for (int i = 0; i < proName.length; i++) {
            listDatas.add(new ProductListBean(proName[i], R.mipmap.ic_launcher));
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        points = (ViewGroup) findViewById(R.id.points);
    }
}
