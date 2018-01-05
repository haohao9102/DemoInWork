package com.example.administrator.helloworld.cunpiao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.helloworld.bean.Movie;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

class MyFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
