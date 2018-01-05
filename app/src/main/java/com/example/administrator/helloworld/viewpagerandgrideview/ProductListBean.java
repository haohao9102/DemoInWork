package com.example.administrator.helloworld.viewpagerandgrideview;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ProductListBean implements Serializable {
    private String proName;
    private int imgUrl;

    public ProductListBean(String proName, int imgUrl) {
        this.proName = proName;
        this.imgUrl = imgUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
