package com.example.administrator.helloworld.baserecyclerviewadapterhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import qdx.bezierviewpager_compile.BezierRoundView;
import qdx.bezierviewpager_compile.vPage.BezierViewPager;
import qdx.bezierviewpager_compile.vPage.CardPagerAdapter;

public class BaseRecyclerViewAdapterHelper extends AppCompatActivity {

    private BezierViewPager bezierViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_view_adapter_helper);

        initView();
    }

    private void initView() {

        int width = getWindowManager().getDefaultDisplay().getWidth();

        float heightRatio = 0.565f;

        bezierViewPager = (BezierViewPager) findViewById(R.id.view_page);
        BezierRoundView bezierRoundView = (BezierRoundView) findViewById(R.id.round_view);

        CardPagerAdapter cardAdapter = new CardPagerAdapter(getApplicationContext());
        List<String> imageList = new ArrayList<>();
        imageList.add("http://img2.niutuku.com/desk/anime/0105/0105-12596.jpg");
        imageList.add("http://wallpapers2.hellowallpaper.com/game_heroes-of-might-and-magic-ol_08-1920x1440.jpg");
        imageList.add("http://wallpapers1.hellowallpaper.com/art_black-wallpaper--01_04-1680x1050.jpg");
        imageList.add("http://bbs.zhezhe168.com/data/attachment/forum/201601/03/164636jp373notn9js9o0m.jpg");
        cardAdapter.addImgUrlList(imageList);

        cardAdapter.setMaxElevationFactor(width/25);

        int mWidthPading = width / 8;

        float heightMore = (1.5f *(width/25) + dp2px(3))-((width/25) + dp2px(3))* heightRatio;
        int mHeightPading = (int) (mWidthPading * heightRatio - heightMore);

        bezierViewPager.setLayoutParams(new LinearLayout.LayoutParams(width,(int) (width * heightRatio)));

        bezierViewPager.setPadding(mWidthPading,mHeightPading,mWidthPading,mHeightPading);

        cardAdapter.setOnCardItemClickListener(new CardPagerAdapter.OnCardItemClickListener() {
            @Override
            public void onClick(int i) {
                Toast.makeText(BaseRecyclerViewAdapterHelper.this, "点击了第" + i + "个item", Toast.LENGTH_SHORT).show();
            }
        });
        bezierViewPager.setClipToPadding(false);
        bezierViewPager.setAdapter(cardAdapter);
        bezierViewPager.showTransformer(0.2f);


        bezierRoundView.attach2ViewPage(bezierViewPager);


    }

    private int dp2px(int i) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (i * scale + 0.5f);
    }
}
