package com.example.administrator.helloworld.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/5/3.
 */

public class TabLayoutTestActivity extends AppCompatActivity {

    String[] title ={"未使用(10)","已使用","已过期"};
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout_test);
        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(),TabLayoutTestActivity.this,title);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}
