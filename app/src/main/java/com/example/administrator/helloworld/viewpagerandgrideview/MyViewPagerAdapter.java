package com.example.administrator.helloworld.viewpagerandgrideview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> viewLists ;
    /**
     * 将当前View添加到ViewGroup容器中
     * 这个方法，return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }
    /**
     *这个方法，是从ViewGroup中移出当前View
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }

    public MyViewPagerAdapter(List<View> viewPagerList) {
        this.viewLists = viewPagerList;
    }

    /**
     *这个方法，是获取当前窗体界面数
     */
    @Override
    public int getCount() {
        return viewLists != null ?viewLists.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
