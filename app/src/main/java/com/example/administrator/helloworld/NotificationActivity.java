package com.example.administrator.helloworld;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/2.
 */
public class NotificationActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifaction_layout);

        NotificationManager manager = (NotificationManager)getSystemService((NOTIFICATION_SERVICE));
        manager.cancel(1);
    }
}
