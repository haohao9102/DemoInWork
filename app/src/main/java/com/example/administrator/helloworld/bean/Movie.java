package com.example.administrator.helloworld.bean;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Movie {
    public int image;
    public String title;
    public String count;
    public String detail;
    public String address;

    public Movie(int image, String title, String count, String detail, String address) {
        this.image = image;
        this.title = title;
        this.count = count;
        this.detail = detail;
        this.address = address;
    }
}
