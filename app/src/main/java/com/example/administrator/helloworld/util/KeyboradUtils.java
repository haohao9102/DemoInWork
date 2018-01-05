package com.example.administrator.helloworld.util;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/5/26.
 */

public class KeyboradUtils {

    private Activity act;
    private Context con;
    private EditText ed;
    private Keyboard k;
    private KeyboardView keyboardView;

    public KeyboradUtils(Activity act, Context con,EditText edit){
        this.act = act;
        this.con = con;
        this.ed = edit;
        k = new Keyboard(con, R.xml.keyboard);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k);//加载键盘布局
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable text = ed.getText();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL){//判断code是否是隐藏
                hideKeyboard();
            }else if (primaryCode == Keyboard.KEYCODE_DELETE){//判断code是否是删除
                if (text!= null&&  text.length()>0){
                    if (start>0){
                        text.delete(start-1,start);//删除
                    }
                }
            }else {
                text.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE){
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }
}
