package com.example.administrator.helloworld.util;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 设置单个字体颜色的类（for TV的野熊）
 * Created by Administrator on 2017/5/10.
 */

public class TextColorUtil {

    static final int GB_SP_DIFF = 160;
    // 存放国标一级汉字不同读音的起始区位码
    static final int[] secPosValueList = {1601, 1637, 1833, 2078, 2274, 2302,
            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
            4086, 4390, 4558, 4684, 4925, 5249, 5600};
    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z'};
    private static String mKey ; //搜索关键字
    private static String mValue; //搜索结果
    private static TextView mView;
    private static List<Integer> list = new ArrayList<>();
    private static int mColor;
    private static TextColorUtil instance ;

//    private TextColorUtil(String key,String value,TextView view){
//        this.view = view;
//        this.key = key;
//        this.value = value;
//    }

    public static TextColorUtil getInstance(){
        if (instance == null){
            synchronized (TextColorUtil.class){
                if (instance == null){
                    instance = new TextColorUtil();
                }
            }
        }
        return instance;
    }
    /**
     * 只要调用这个方法
     * @param key 搜索关键字
     * @param value 搜索结果
     * @return  返回的是SpannableStringBuilder 类型的数据
     */
    public static TextColorUtil setTextColor(String key,String value){
        if (value != null){
            mKey = key;
            mValue = value;
            String spell = getSpells(value);
            if (spell.contains(key)){
                int a = spell.indexOf(key);
                getStrLocation(value,a);
                SpannableStringBuilder style = new SpannableStringBuilder(value);
                for (int i = 0; i < list.size(); i++) {
                    int location = list.get(i);
                    style.setSpan(new ForegroundColorSpan(mColor), location, location+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                mView.setText(style);
            }
        }
        return instance;
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

    private static void getStrLocation(String text, int a) {
        char c = text.charAt(a);
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                list.add(i);
            }
        }
    }

    public static TextColorUtil setColor(int color){
        mColor = color;
        return instance;
    }

    public static TextColorUtil into(TextView view){
        mView = view;
        return instance;
    }

}
