package com.example.administrator.helloworld.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MyTextDialog extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_layout);
    }

    public void show(View view){
        switch (view.getId()){
            case R.id.show_1:
                show();
                break;
            case R.id.show_2:
                show2();
                break;
        }


    }

    private void show2() {
        final AlertDialog dialog = new AlertDialog(this);
        dialog.builder().setTitle("温馨提示")
                .setMsg("请选择是否退款")
                .setMsg2("退款选项将退至余额账户")
                .setPositiveButton("退款", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.setCancelable(true);
                    }
                })
                .setPositiveButton("取消",null)
                .show();
    }

    private void show() {
        dialog =  new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_layout,null);
        /*root.findViewById(R.id.btn_open_camera).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_choose_img).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);*/

        TextView choosePhoto = (TextView) root.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) root.findViewById(R.id.takePhoto);
        choosePhoto.setOnClickListener(btnlistener);
        takePhoto.setOnClickListener(btnlistener);

        dialog.setContentView(root);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager manager = getWindow().getWindowManager();
        Display d = manager.getDefaultDisplay();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = 20;
        attributes.width = d.getWidth();
        window.setAttributes(attributes);
        dialog.show();
    }

    private View.OnClickListener btnlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.choosePhoto: // 打开照相机
                    Toast.makeText(MyTextDialog.this,"点击了相机",Toast.LENGTH_SHORT).show();
                    break;
                // 打开相册
                case R.id.takePhoto:
                    Toast.makeText(MyTextDialog.this,"点击了相册",Toast.LENGTH_SHORT).show();
                    break;
                // 取消
//                case R.id.btn_cancel:
//                    if (dialog != null) {
//                        dialog.dismiss();
//                    }
//                    break;
            }
            dialog.dismiss();
        }
    };
}
