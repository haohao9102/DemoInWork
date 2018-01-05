package com.example.administrator.helloworld.tablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2017/5/3.
 */
public class MyAdapter extends FragmentPagerAdapter{
    private Context context;
    private String[] titles;

    public MyAdapter(FragmentManager fm,Context context, String[] title) {
        super(fm);
        this.titles = title;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        return MyFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
