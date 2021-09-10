package com.sp.spmultipleapp.eventbustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;


public class EventBusSecondActivity extends AppCompatActivity {
    private Button bt_message;
    private TextView tv_message;
    private Button bt_subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_main);
        tv_message=(TextView)this.findViewById(R.id.tv_message);
        tv_message.setText("EventBusSecondActivity");
        bt_subscription=(Button)this.findViewById(R.id.bt_subscription);
        bt_subscription.setText("发送粘性事件");
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                finish();
            }
        });
        bt_message=(Button)this.findViewById(R.id.bt_message);
        bt_message.setText("发送事件");
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("欢迎关注刘望舒的博客"));
                finish();
            }
        });

    }
}
