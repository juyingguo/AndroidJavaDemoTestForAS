package com.thirdlibrary.eventbustest;

import com.utils.ThreadUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 类要为public修饰才可以被EventBus访问
 */
public class Second {
    private final String TAG = "Second";
    void register(){
        EventBus.getDefault().register(this);
    }
    void unRegister(){
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void showMessage(MessageEvent messageEvent){
        System.out.println(TAG + " showMessage:" + messageEvent.getMessage());
        ThreadUtil.printThread();
    }

    void post(){
        MessageEvent messageEvent = new MessageEvent("hello eventbus");
        EventBus.getDefault().post(messageEvent);
    }
}