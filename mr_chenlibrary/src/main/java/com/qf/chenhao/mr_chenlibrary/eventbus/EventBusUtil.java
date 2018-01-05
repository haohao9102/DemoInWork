package com.qf.chenhao.mr_chenlibrary.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus 工具类
 * Created by Administrator on 2017/5/12.
 */

public class EventBusUtil {

    /**
     * 注册EventBus
     * @param subscriber
     */
    public static void register(Object subscriber){
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 注销EventBus
     * @param subscriber
     */
    public static void unRegister(Object subscriber){
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送事件
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送黏性事件
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
