package com.example.administrator.helloworld;

import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.helloworld.banner.Banner;
import com.example.administrator.helloworld.baserecyclerviewadapterhelper.BaseRecyclerViewAdapterHelper;
import com.example.administrator.helloworld.camera.CameraActivity;
import com.example.administrator.helloworld.cunpiao.BuyTicketActivity;
import com.example.administrator.helloworld.cunpiao.HuaLangActivity;
import com.example.administrator.helloworld.dialog.MyTextDialog;
import com.example.administrator.helloworld.eventbus.EventBus_;
import com.example.administrator.helloworld.listview.ListViewAcitvity;
import com.example.administrator.helloworld.popwindow.PopWindowActivity;
import com.example.administrator.helloworld.search.SearchActivity;
import com.example.administrator.helloworld.tablayout.TabLayoutTestActivity;
import com.example.administrator.helloworld.util.MD5;
import com.example.administrator.helloworld.viewpagerandgrideview.ViewPagerAndGrideView;
import com.example.administrator.helloworld.zxing.ZxingActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "print";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private String mUerName;
    private String mRepo;
    private Button recyclerview2;
    private Button recyclerview;
    private ImageButton imageButton;
    private Button event_bus;
    private Button add;
    private Button get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        add = (Button) findViewById(R.id.add);
        get = (Button) findViewById(R.id.get);
        Button share = (Button) findViewById(R.id.share);
        Button drawerlayout = (Button) findViewById(R.id.drawerlayout);
        imageButton = (ImageButton) findViewById(R.id.iv_transcation1);
        recyclerview = (Button) findViewById(R.id.recyclerview);
        recyclerview2 = (Button) findViewById(R.id.recyclerview2);
        event_bus = (Button) findViewById(R.id.eb);
        add.setOnClickListener(this);
        get.setOnClickListener(this);
        share.setOnClickListener(this);
        drawerlayout.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        recyclerview.setOnClickListener(this);
        recyclerview2.setOnClickListener(this);
        event_bus.setOnClickListener(this);

       /* String[] titile = {"增加数据",};

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titile);
        listView.setAdapter(adapter);*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.push:
                startActivity(new Intent(this, JpushActivity.class));
                break;
            case R.id.add:
                break;
            case R.id.get:
                getNet();
                break;
            case R.id.login:
                shareLogin();
                break;
            case R.id.share:
                socialShare();
                break;
            case R.id.drawerlayout:
                jump();
                break;
            case R.id.iv_transcation1:
                transcation();
                break;
            case R.id.recyclerview:
                jump1();
                break;
            case R.id.recyclerview2:
                jump2();
                break;
            case R.id.eb:
                eventBus();
                Log.i(TAG, "get: ----->>>>执行了没？");
                break;
            case R.id.pop_jump:
                startActivity(new Intent(this, PopWindowActivity.class));
                break;
            case R.id.tabswitcher:
                startActivity(new Intent(this, Banner.class));
                break;
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.over:

                break;
            case R.id.dialog:
                startActivity(new Intent(this, MyTextDialog.class));
                break;
            case R.id.notifaction:
                sendNotifaction();
                break;
            case R.id.messsge:
                startActivity(new Intent(this, MessageActivity.class));
                break;
            case R.id.tab_layout:
                startActivity(new Intent(this, TabLayoutTestActivity.class));
                break;
            case R.id.viewpager_grideview:
                startActivity(new Intent(this, ViewPagerAndGrideView.class));
                break;
            case R.id.aaa:

                break;
            case R.id.stop:
                startActivity(new Intent(this, ListViewAcitvity.class));
                break;
            case R.id.cunpiao:
                startActivity(new Intent(this, BuyTicketActivity.class));
                break;
            case R.id.hualang:
                startActivity(new Intent(this, HuaLangActivity.class));
                break;
            case R.id.greendao:
                break;
            default:
                break;
        }
    }

    /**
     * shareSDK 第三方登录
     */
    private void shareLogin() {
        startActivity(new Intent(this, ListViewAcitvity.class));
    }

    /**
     * 发送通知
     */
    private void sendNotifaction() {
        //获取通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
        //通知意图
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //获得通知对象
        Notification notification = new Notification.Builder(HomeActivity.this)
                .setAutoCancel(true)
                .setContentTitle("title") //通知标题
                .setContentText("text") //通知内容
                .setContentIntent(pi)      //通知点击跳转意图
                .setSmallIcon(R.mipmap.ic_launcher)     //通知图标
                .setWhen(System.currentTimeMillis())
                .build();

        /*Uri uri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
        notification.sound = uri;
        long[] vibrates = {0,1000,1000,1000};
        notification.vibrate = vibrates;*/
        notification.defaults = Notification.DEFAULT_ALL;//设置通知系统铃声和震动(默认的）
        manager.notify(1, notification);//通知管理器通知
    }

    public void guaguaka(View view) {
        startActivity(new Intent(this, GuaGuaKaActivity.class));
    }

    public void playVideo(View view) {

    }

    public void camera(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }

    private void jump1() {
        startActivity(new Intent(this, PullFrushActivity.class));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        }
    }

    /**
     * eventBus
     */
    private void eventBus() {
        String hello = "ninhao";


        startActivity(new Intent(this, EventBus_.class));
        EventBus.getDefault().post(hello);
    }

    private void jump2() {
        //startActivity(new Intent(this,PullFrushActivity.class));
        startActivity(new Intent(this, BaseRecyclerViewAdapterHelper.class));
    }

    /**
     * 元素共享过场动画
     */
    private void transcation() {
        View view = findViewById(R.id.iv_transcation1);//共享的控件
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions shareOptions = ActivityOptions.makeSceneTransitionAnimation(this, view, "sharedName");
            startActivity(new Intent(this, TranscationActivity.class), shareOptions.toBundle());
        }
    }

    private void jump() {
        Intent intent = new Intent(this, DrawerLayoutAcitvity.class);
        startActivity(intent);
    }

    public void zxing(View view) {
        startActivity(new Intent(this, ZxingActivity.class));
    }

    /**
     *
     */
    private void socialShare() {
//        Platform platform = ShareSDK.getPlatform(QQ.NAME);//设置分享平台
//        Platform.ShareParams params= new Platform.ShareParams();
//        platform.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//
//            }
//
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCancel(Platform platform, int i) {
//
//            }
//        });
//        platform.share(params);
        OnekeyShare oks = new OnekeyShare();
//        oks.setPlatform(platform.getName());指定分享平台
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("我的第一次分享");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享内容是您好");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    /**
     * retrofit使用
     */
    private void getNet() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(Constants.MAIZUO)
                .build();
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(" ");
        Date date = new Date(l);
        String format = sdf.format(date);
        String sign = MD5.getMD5("client_id=52642103681&timestamp=" + format + "&key=xkGEr244(((<HAee4346fg");
        GitHubApi api = retrofit1.create(GitHubApi.class);
        Call<ResponseBody> call1 = api.postMaiZuo("52642103681", sign, format, 13
        );
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("maizuo", "onResponse: --------->>>>>>" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Map<String, String> parms = new HashMap<>();
        parms.put("client_id", "52642103681");
        parms.put("sign", sign);
        parms.put("start", 1 + "");
        parms.put("count", 20 + "");
        parms.put("isShow", 1 + "");
        parms.put("cityId", 13 + "");
        parms.put("timestamp", format);
        Call<ResponseBody> moive = api.getMoive(parms);
        moive.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: ----------->>>>>" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


//        ==================================================================================

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(Constants.DOUBAN)
                .build();

        GitHubApi gitHubApi = retrofit.create(GitHubApi.class);
        Call<ResponseBody> call = gitHubApi.getDobanUrl();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "线程" + Thread.currentThread().getName() + "......" + "onResponse: ----->>>" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Call<ResponseBody> call2 = gitHubApi.getWebView(4);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "线程" + Thread.currentThread().getName() + "......" + "onResponse: ----->>>>" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void callTest(View view) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
