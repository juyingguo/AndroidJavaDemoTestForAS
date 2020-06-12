package com.example.liuwangshu.moonmvpsimple.net;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;

import com.example.liuwangshu.moonmvpsimple.LoadTasksCallBack;
import com.example.liuwangshu.moonmvpsimple.model.IpInfo;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class IpInfoTask implements NetTask<String> {
    private final String TAG = "com.example.liuwangshu.moonmvpsimple.net.IpInfoTask";
    private static IpInfoTask INSTANCE = null;
    private static final String HOST = "http://ip.taobao.com/service/getIpInfo.php";
    private LoadTasksCallBack loadTasksCallBack;

    private IpInfoTask() {

    }

    public static IpInfoTask getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpInfoTask();
        }
        return INSTANCE;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void execute(String ip, final LoadTasksCallBack loadTasksCallBack) {
        Log.d(TAG,"execute,ip:" + ip);
        final RequestParams requestParams = new RequestParams();
        requestParams.addFormDataPart("ip", ip);
        /**
         * com.example.liuwangshu.moonmvpsimple.net.IpInfoTask: execute,onFailure,errorCode:1002,msg:Data parse exception
         */
        /*HttpRequest.get(HOST, requestParams, new BaseHttpRequestCallback<IpInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                loadTasksCallBack.onStart();
            }

            @SuppressLint("LongLogTag")
            @Override
            protected void onSuccess(IpInfo ipInfo) {
                super.onSuccess(ipInfo);
                Log.d(TAG,"execute,onSuccess,ipInfo:" + ipInfo);
                loadTasksCallBack.onSuccess(ipInfo);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadTasksCallBack.onFinish();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.d(TAG,"execute,onFailure,errorCode:" + errorCode + ",msg:" + msg);
                loadTasksCallBack.onFailed();
            }
        });*/
        Log.d(TAG,"execute,call [SystemClock.sleep(3000)].");
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                HttpRequest.get(HOST, requestParams, new BaseHttpRequestCallback<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        loadTasksCallBack.onStart();
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    protected void onSuccess(String s) {
                        super.onSuccess(s);
                        Log.d(TAG,"execute,onSuccess,result:" + s);
                        Log.d(TAG,"execute,onSuccess,call [SystemClock.sleep(3000)].");
                        SystemClock.sleep(3000);
                        loadTasksCallBack.onSuccess(null);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        loadTasksCallBack.onFinish();
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        Log.d(TAG,"execute,onFailure,errorCode:" + errorCode + ",msg:" + msg);
                        loadTasksCallBack.onFailed();
                    }
                });

            }
        }).start();

    }

    @SuppressLint("LongLogTag")
    @Override
    public void cancelTask() {
        Log.d(TAG,"cancelTask");
        HttpRequest.cancel(HOST);//模拟取消请求
    }
}


