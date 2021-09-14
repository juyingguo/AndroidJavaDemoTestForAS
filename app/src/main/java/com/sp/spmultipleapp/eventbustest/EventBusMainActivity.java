package com.sp.spmultipleapp.eventbustest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus test;
 * <p>
 *     reference::android-advanced-light\chapter_7\MoonEventBus3.0\app\src\main\java\com\example\liuwangshu\mooneventbus
 * </p>
 */
public class EventBusMainActivity extends AppCompatActivity {

    private TextView tv_message;
    private Button bt_message;
    private Button bt_subscription;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_main);
        tv_message=(TextView)this.findViewById(R.id.tv_message);
        tv_message.setText("EventBusMainActivity");
        bt_subscription=(Button)this.findViewById(R.id.bt_subscription);
        bt_subscription.setText("注册事件");
        bt_message=(Button)this.findViewById(R.id.bt_message);
        bt_message.setText("跳转到EventBusSecondActivity");
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(EventBusMainActivity.this,EventBusSecondActivity.class));
            }
        });
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!EventBus.getDefault().isRegistered(EventBusMainActivity.this)) {
                    //注册事件
                    EventBus.getDefault().register(EventBusMainActivity.this);
                }else{
                    Toast.makeText(EventBusMainActivity.this,"请勿重复注册事件",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    public void clickForSubscriptionCustomBuilder(View view) {
        if(!EventBusCustom.CUSTOM_DEFAULT_EVENT_BUS.isRegistered(EventBusMainActivity.this)) {
            //注册事件
            EventBusCustom.CUSTOM_DEFAULT_EVENT_BUS.register(EventBusMainActivity.this);
        }else{
            Toast.makeText(EventBusMainActivity.this,"请勿重复注册事件(定制builder)",Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        tv_message.setText(messageEvent.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMoonEventTest(MessageEvent messageEvent){
        tv_message.setText(messageEvent.getMessage());
    }
    @Subscribe(sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent){
        tv_message.setText(messageEvent.getMessage());
    }
}
