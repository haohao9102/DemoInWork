package com.example.administrator.helloworld.popwindow;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

public class PopWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
    }

    public void show(View view){
        showPopWindow(view);
    }

    private void showPopWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_window_layout,null);

        Button hh = (Button) view.findViewById(R.id.btn_hehe);
        Button xx = (Button) view.findViewById(R.id.btn_xixi);

        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setAnimationStyle(R.anim.pop);

        popupWindow.setFocusable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        popupWindow.showAsDropDown(v,50,0);

        xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopWindowActivity.this, "点击了嘻嘻", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopWindowActivity.this, "点击了哈哈", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }
}
