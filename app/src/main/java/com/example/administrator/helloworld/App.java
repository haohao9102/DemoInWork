package com.example.administrator.helloworld;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/5/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);  //极光推送初始化
        JPushInterface.init(this);
        ShareSDK.initSDK(this);//share  sdk





        //设置通知栏格式
        /*BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                |Notification.FLAG_SHOW_LIGHTS;
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                |Notification.DEFAULT_VIBRATE
                |Notification.DEFAULT_LIGHTS;
        JPushInterface.setPushNotificationBuilder(1,builder);*/


        /*MultiActionsNotificationBuilder builder = new MultiActionsNotificationBuilder(this);
//添加按钮，参数(按钮图片、按钮文字、扩展数据)
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "first", "my_extra1");
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "second", "my_extra2");
        builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "third", "my_extra3");
        JPushInterface.setPushNotificationBuilder(2, builder);*/

       /* CustomPushNotificationBuilder builder = new
                CustomPushNotificationBuilder(this,
                R.layout.customer_notitfication_layout,
                R.id.icon,
                R.id.title,
                R.id.text);
        // 指定定制的 Notification Layout
        builder.statusBarDrawable = R.drawable.your_notification_icon;
        // 指定最顶层状态栏小图标
        builder.layoutIconDrawable = R.drawable.your_2_notification_icon;
        // 指定下拉状态栏时显示的通知图标
        JPushInterface.setPushNotificationBuilder(2, builder);*/

//        ViseHttp.init(this);
//        ViseHttp.CONFIG()
//                .baseUrl("http://192.168.1.100/")
//                .globalHeaders(new HashMap<String, String>())
//                .globalParams(new HashMap<String, String>())
//                .readTimeout(30)
//                .writeTimeout(30)
//                .connectTimeout(30)
//                .retryCount(3)
//                .retryDelayMillis(1000)
//                .setCookie(true)
//                .apiCookie(new ApiCookie(this))
//                .setHttpCache(true)
//                .setHttpCacheDirectory(new File(ViseHttp.getContext().getCacheDir(),ViseConfig.CACHE_HTTP_DIR))
//                .httpCache(new Cache(new File(ViseHttp.getContext().getCacheDir(),ViseConfig.CACHE_HTTP_DIR),ViseConfig.CACHE_MAX_SIZE))
//                .cacheOffline(new Cache(new File(ViseHttp.getContext().getCacheDir(),ViseConfig.CACHE_HTTP_DIR),ViseConfig.CACHE_MAX_SIZE))
//                .cacheOnline(new Cache(new File(ViseHttp.getContext().getCacheDir(),ViseConfig.CACHE_HTTP_DIR),ViseConfig.CACHE_MAX_SIZE))
//                .postGzipInterceptor()
//                .interceptor(new HttpLogInterceptor().setLevel(HttpLogInterceptor.Level.BODY))
//                .networkInterceptor(new NoCacheInterceptor())
//                .converterFactory(GsonConverterFactory.create())
//                .callFactory(new Call.Factory() {
//                    @Override
//                    public Call newCall(Request request) {
//                        return null;
//                    }
//                })
//                .connectionPool(new ConnectionPool())
//                .hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier("http://192.168.1.100/"))
//                //配置SSL证书验证
//                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null))
//                //配置主机代理
//                .proxy(new Proxy(Proxy.Type.HTTP, new SocketAddress() {}));
//
//        DiskCache diskCache = new DiskCache(this);

    }
}
