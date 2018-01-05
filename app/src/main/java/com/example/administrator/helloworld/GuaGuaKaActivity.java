package com.example.administrator.helloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cooltechworks.views.ScratchTextView;
import com.example.administrator.helloworld.util.TextColorUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public class GuaGuaKaActivity extends AppCompatActivity {
    String  url = "https://www.baidu.com";

    static final int GB_SP_DIFF = 160;
    // 存放国标一级汉字不同读音的起始区位码
    static final int[] secPosValueList = { 1601, 1637, 1833, 2078, 2274, 2302,
            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
            4086, 4390, 4558, 4684, 4925, 5249, 5600 };
    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z' };

    private static final String TAG = "print";
    private List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guagua_layout);

        /*ScratchTextView textView = new ScratchTextView(this);
        textView.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView scratchTextView) {
                Toast.makeText(GuaGuaKaActivity.this, ""+scratchTextView, Toast.LENGTH_SHORT).show();
            }
        });*/
      /*  GuaGuaKa guaGuaKa = (GuaGuaKa) findViewById(R.id.guagua);
        guaGuaKa.setText("傻逼傻逼");*/
        TextView test = (TextView) findViewById(R.id.test);
        TextView tv = (TextView) findViewById(R.id.color);



        test.setTextColor(0x330000ff);


        com.example.administrator.helloworld.ScratchTextView textView = (com.example.administrator.helloworld.ScratchTextView) findViewById(R.id.scratch);
        textView.setText("hello!!!");
        textView.initScratchCard(0xFFFFFFFF, 40, 1f);

        String text = "王牌逗-2王牌";


        String spell = getSpells(text);
        TextColorUtil.getInstance().setColor(Color.BLUE).into(tv).setTextColor("p", text);
        textView.setText(spell.toUpperCase());

//        Glide.with(this).load(url).into(imageview);
//        color.setText(spannableStringBuilder);
//        if (spell.contains("w")){
//            int a = spell.indexOf("w");
//            getStrLocation(text,a);
//            SpannableStringBuilder style = new SpannableStringBuilder(text);
////            style.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            for (int i = 0; i < list.size(); i++) {
//                int location = list.get(i);
//                style.setSpan(new ForegroundColorSpan(Color.RED), location, location+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            color.setText(style);
//        }

    }

    private static String getSpells(String text) {
        StringBuffer  sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >> 7) == 0){
            }else {
                char spell = getFirstLetter(c);
                sb.append(String.valueOf(spell));
            }
        }
        return sb.toString();
    }

    private static Character getFirstLetter(char c) {
        byte[] unicode = null;
        try {
            unicode = String.valueOf(c).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (unicode[0] < 128 && unicode[0]>0){
            return null;
        }else{
            return convert(unicode);
        }
    }
    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */
    private static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for ( i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 +bytes[1];
        for (i = 0;i< 23;i++){
            if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i+1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }

    private void getStrLocation(String text, int a) {
        char c = text.charAt(a);
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                list.add(i);
            }
        }
    }
}
