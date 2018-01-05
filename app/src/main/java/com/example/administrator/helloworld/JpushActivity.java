package com.example.administrator.helloworld;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.helloworld.cunpiao.TextToSpeechDemo;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/24.
 */

public class JpushActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView textView;
    private int time = 900;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.edt)
    EditText edt;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    time--;
                    Date date = new Date(time * 1000);
                    String format1 = format.format(date);
                    textView.setText(format1);

                    if (time > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
                        textView.setVisibility(View.GONE);
                    }
            }
            super.handleMessage(msg);
        }
    };
    private String text;

    @Override
    protected int getContentId() {
        return R.layout.jpush_layout;
    }

    @Override
    protected void init() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);

        text = edt.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextToSpeechDemo speechDemo = new TextToSpeechDemo(JpushActivity.this);
                speechDemo.speakText(text);
            }
        });
    }

    @Override
    protected boolean isOpenStatus() {
        return false;
    }
}
