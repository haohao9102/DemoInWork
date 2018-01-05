package com.example.administrator.helloworld.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/6/13.
 * 苹果样式
 */

public class AlertDialog {
    private Context mContext;
    private LinearLayout ll;
    private TextView title;
    private TextView msg;
    private TextView msg2;
    private Button btn_neg;
    private Button btn_pos;
    private final Display display;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private LinearLayout.LayoutParams layoutParams;
    private Dialog dialog;

    public AlertDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public AlertDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alertdialog_layout, null);

        ll = (LinearLayout) view.findViewById(R.id.ll_container);

        title = (TextView) view.findViewById(R.id.title);
        title.setVisibility(View.VISIBLE);

        msg = (TextView) view.findViewById(R.id.msg_1);
        msg.setVisibility(View.VISIBLE);

        msg2 = (TextView) view.findViewById(R.id.msg_2);
        msg2.setVisibility(View.VISIBLE);

        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        // 退出
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        ll.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.75), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public AlertDialog setTitle(String title) {
        if ("".equals(title)) {
            this.title.setText("标题");
        } else {
            this.title.setText(title);
        }
        return this;
    }

    public AlertDialog setMsg(String msg) {
        if ("".equals(msg)) {
            this.msg.setText("内容");
        } else {
            this.msg.setText(msg);
        }
        return this;
    }

    public AlertDialog setMsg2(String msg2) {
        if ("".equals(msg)) {
            this.msg2.setText("内容");
        } else {
            this.msg2.setText(msg2);
        }
        return this;
    }
    public AlertDialog setCancelable(boolean cancel) {

        // dialog.setCancelable(cancel);或者用这个方法
        dialog.dismiss();
        return this;
    }

    /**
     * 确认按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public AlertDialog setPositiveButton(String text,
                                         final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("退款");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }
    /**
     * 取消按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public AlertDialog setNegativeButton(String text,
                                         final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
        }
    }

    /**
     * 显示dialog视图
     */
    public void show() {
        setLayout();
        dialog.show();
    }
}
