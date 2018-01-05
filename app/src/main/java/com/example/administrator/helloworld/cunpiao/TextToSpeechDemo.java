package com.example.administrator.helloworld.cunpiao;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/6/14.
 */

public class TextToSpeechDemo implements android.speech.tts.TextToSpeech.OnInitListener {
    private Context mContext;
    private TextToSpeech mTextToSpeech;//TTS对象
    private ConcurrentLinkedQueue<String> mBufferedMessages;//消息队列
    private boolean mIsReady;//标识符

    public TextToSpeechDemo(Context context) {
        this.mContext = context;
        this.mBufferedMessages = new ConcurrentLinkedQueue<String>();//实例化队列
        this.mTextToSpeech = new TextToSpeech(context, this);//实例化TTS
    }

    @Override
    public void onInit(int status) {
        Log.i("TextToSpeechDemo", String.valueOf(status));
        if (status == TextToSpeech.SUCCESS) {
            int result = this.mTextToSpeech.setLanguage(Locale.CHINA);//设置识别语音为中文
            synchronized (this) {
                this.mIsReady = true;//设置标识符为true
                for (String bufferedMessage : this.mBufferedMessages) {
                    speakText(bufferedMessage);//读语音
                }
                this.mBufferedMessages.clear();
            }
        }
    }

    /**
     * @param message
     */
    public void speakText(String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_STREAM, "STREAM_NOTIFICATION");//设置播放类型（音频流类型）
        this.mTextToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, map);//将这个发音任务添加当前任务之后
        this.mTextToSpeech.playSilence(100, TextToSpeech.QUEUE_ADD, map);
    }

    /**
     * 更新消息队列，或者读语音
     *
     * @param lanaugh
     */
    public void notifyNewMessage(String lanaugh) {
        String message = lanaugh;
        synchronized (this) {
            if (this.mIsReady) {
                speakText(message);
            } else {
                this.mBufferedMessages.add(message);
            }
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        synchronized (this) {
            this.mTextToSpeech.shutdown();
            this.mIsReady = false;
        }
    }
}
