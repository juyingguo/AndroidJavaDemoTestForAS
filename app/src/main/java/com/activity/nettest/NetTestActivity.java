package com.activity.nettest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.sp.spmultipleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.utils.NetUtils;

public class NetTestActivity extends AppCompatActivity {
    Unbinder butterKnifeBind = null;


    @BindView(R.id.btn_net)
    Button btn_net;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);


        testNet();

    }

    private void testNet() {

        boolean networkConnected = NetUtils.isNetworkConnected(this);
        boolean networkConnected02 = NetUtils.isNetworkConnected02(this);
        boolean netWorkAvailable = NetUtils.isNetWorkAvailable();

        btn_net.setText("networkConnected:" + networkConnected + "\n networkConnected02:" + networkConnected02 + "\n netWorkAvailable:" + netWorkAvailable);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
