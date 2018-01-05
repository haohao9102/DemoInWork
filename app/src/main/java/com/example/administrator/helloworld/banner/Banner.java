package com.example.administrator.helloworld.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.helloworld.R;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public class Banner extends AppCompatActivity{

    private com.youth.banner.Banner banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabswitcher_layout);
        List<String> title = new ArrayList<>();
        title.add("第一天");
        title.add("第二天");
        title.add("第三天");
        title.add("第四天");
        List<String> imageList = new ArrayList<>();
        imageList.add("http://img2.niutuku.com/desk/anime/0105/0105-12596.jpg");
        imageList.add("http://wallpapers2.hellowallpaper.com/game_heroes-of-might-and-magic-ol_08-1920x1440.jpg");
        imageList.add("http://wallpapers1.hellowallpaper.com/art_black-wallpaper--01_04-1680x1050.jpg");
        imageList.add("http://bbs.zhezhe168.com/data/attachment/forum/201601/03/164636jp373notn9js9o0m.jpg");

        banner = (com.youth.banner.Banner) findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageList);
        banner.setBannerAnimation(Transformer.Stack);
        banner.setBannerTitles(title);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();

        
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

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(Banner.this).load(path).into(imageView);
        }
    }
}
