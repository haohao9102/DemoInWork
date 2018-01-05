package com.example.administrator.helloworld.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.helloworld.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class ZxingActivity extends AppCompatActivity {
    public static final int CODE = 1;
    private TextView tv;
    private EditText ed;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        tv = (TextView) findViewById(R.id.tv);
        ed = (EditText) findViewById(R.id.ed);
        imageView = (ImageView) findViewById(R.id.iv_qr_image);
    }

    public void sao(View view){
        startActivityForResult(new Intent(this,CaptureActivity.class),CODE);
    }

    public void erweima(View view){
        String string = ed.getText().toString();
        if (!string.equals("")){
            Bitmap qrCode = EncodingUtils.createQRCode(string, 350, 350, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            imageView.setImageBitmap(qrCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE){
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");
                tv.setText(result);
            }
        }
    }
}
