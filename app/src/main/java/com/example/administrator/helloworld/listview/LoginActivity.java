package com.example.administrator.helloworld.listview;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.util.SharedPreferencesUtil;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 爱享365 on 2017/7/18.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "print";
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_icon)
    ImageView user_icon;
    @BindView(R.id.user_sex)
    TextView user_sex;
    private CameraManager manager;
    private Camera camera = null;
    private Camera.Parameters parameters = null;
    public static boolean kaiguan = true; // 定义开关状态，状态为false，打开状态，状态为true，关闭状态

    @Override
    protected int getContentId() {
        return R.layout.login_activity_layout;
    }

    @Override
    protected void init() {

        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIdList = manager.getCameraIdList();
            for (String str: cameraIdList){
                Log.d(TAG, "init: ----->>>"+str);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        String name = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userName");
        String icon = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userIcon");
        String gender = SharedPreferencesUtil.readString(getApplicationContext(), "user", "userGender");
        user_name.setText("用户名: " + name);
        Glide.with(getApplicationContext())
                .load(icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(user_icon);
        user_sex.setText("性别: " + gender);
        Button open_btn = (Button) findViewById(R.id.open_btn);

        open_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isLoLLIPOP()) {
                    try {
                        manager.setTorchMode("0", true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button close_btn = (Button) findViewById(R.id.close_btn);
        close_btn.setOnClickListener(closeOnClickListener);

        ToggleButton toggle_btn = (ToggleButton) findViewById(R.id.toggle_btn);
        toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    manager.setTorchMode("1", isChecked);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private View.OnClickListener closeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isLoLLIPOP()) {
                try {
                    manager.setTorchMode("0", false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private boolean isLoLLIPOP() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            return true;
        }else{
            return false;
        }
    }
}
