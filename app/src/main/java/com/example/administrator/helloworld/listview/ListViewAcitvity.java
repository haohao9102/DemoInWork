package com.example.administrator.helloworld.listview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.util.SharedPreferencesUtil;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ListViewAcitvity extends BaseActivity {

    //成功
    private static final int MSG_AUTH_COMPLETE = 1;
    //失败
    private static final int MSG_AUTH_ERROR = 2;
    //取消
    private static final int MSG_AUTH_CANCEL = 3;
    private static final String TAG = "print";
    private List<String> list;
    private ListView listView;
    @BindView(R.id.qq_login)
    TextView qq_login;
    @BindView(R.id.weixin_login)
    TextView weixin_login;
    @BindView(R.id.weibo_login)
    TextView weibo_login;
    private Handler handler;

//    @Bind(R.id.user_name)
//    TextView user_name;
//    @Bind(R.id.user_icon)
//    ImageView user_icon;
//    @Bind(R.id.user_sex)
//    TextView user_sex;

    @Override
    protected int getContentId() {
        return R.layout.listview_layout;
    }

    @Override
    public void setListener() {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    @OnClick({R.id.weibo_login, R.id.qq_login, R.id.weixin_login})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.qq_login:
                qqLogin();
                startActivity(new Intent(ListViewAcitvity.this,LoginActivity.class));
                finish();
                break;
            case R.id.weibo_login:
                break;
            case R.id.weixin_login:
                break;
        }
    }

//    private void loginOut() {
//        Platform platform = ShareSDK.getPlatform(QQ.NAME);
//        if (platform.isAuthValid()){
//            platform.removeAccount(true);
//        }
//        platform.authorize();
////        platform.showUser(null);
//    }

    private void qqLogin() {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);//QQ平台
        if (platform == null) {
            return;
        }
        //平台监听
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (i == Platform.ACTION_USER_INFOR) {
                    Message msg = Message.obtain();
                    msg.what = MSG_AUTH_COMPLETE;
                    msg.obj = new Object[]{platform.getName(), hashMap};
                    handler.handleMessage(msg);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                if (i == Platform.ACTION_USER_INFOR) {
                    handler.sendEmptyMessage(MSG_AUTH_ERROR);
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                if (i == Platform.ACTION_USER_INFOR) {
                    handler.sendEmptyMessage(MSG_AUTH_CANCEL);
                }
            }
        });

        //关闭SSO-->登录(客户端)授权
        platform.SSOSetting(false);

        platform.showUser(null);
    }

    @Override
    protected void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_AUTH_COMPLETE:
                        Object obj[] = (Object[]) msg.obj;
                        String platformName = (String) obj[0];
                        //获取平台
                        Platform platform = ShareSDK.getPlatform(ListViewAcitvity.this, platformName);
                        //获取身份信息
                        String userName = platform.getDb().getUserName();
                        String userIcon = platform.getDb().getUserIcon();
                        String userGender = platform.getDb().getUserGender();
                        String userId = platform.getDb().getUserId();
                        String token = platform.getDb().getToken();
                        //存储信息
                        SharedPreferencesUtil.writeString(getApplicationContext(),"user","userName",userName);
                        SharedPreferencesUtil.writeString(getApplicationContext(),"user","userIcon",userIcon);
                        SharedPreferencesUtil.writeString(getApplicationContext(),"user","userGender",userGender);
                        SharedPreferencesUtil.writeString(getApplicationContext(),"user","userId",userId);
                        SharedPreferencesUtil.writeString(getApplicationContext(),"user","token",token);
                        Log.i(TAG, "handleMessage: " + "用户名==" + userName + " 用户图标=="
                                + userIcon + " 用户性别==" + userGender + " 用户id==" + userId +" token=="+token);
                        break;
                    case MSG_AUTH_ERROR:
                        break;
                    //取消时候的操作
                    case MSG_AUTH_CANCEL:
                        break;
                }
            }
        };
//        listView = (ListView) findViewById(R.id.list_view);
//        list = new ArrayList<>();
//
//        for (int i = 0; i < 40; i++) {
//            if(i %2 == 0){
//                list.add("测试数据第"+i+"条");
//            }else{
//                list.add("实际数据第"+i+"条");
//            }
//        }
//        TestAdapter adapter = new TestAdapter(this,list);
//        listView.setAdapter(adapter);

//        String name = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userName");
//        String icon = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userIcon");
//        String gender = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userGender");
//        user_name.setText("用户名: "+ name);
//        Glide.with(getApplicationContext()).load(icon).diskCacheStrategy(DiskCacheStrategy.ALL)
//                .crossFade().into(user_icon);
//        user_sex.setText("性别: "+gender);
    }

    @Override
    protected boolean isOpenStatus() {
        return false;
    }
}
