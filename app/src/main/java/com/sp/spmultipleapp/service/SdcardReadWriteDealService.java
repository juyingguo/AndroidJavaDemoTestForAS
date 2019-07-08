package com.sp.spmultipleapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import util.FileUtils;

/**
 * Created by 123 on 2018/4/19.
 */

public class SdcardReadWriteDealService extends Service {
    private String TAG = SdcardReadWriteDealService.class.getSimpleName();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    AtomicInteger aiCount;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
//        readAndWriteFile2();

    }

    private void readAndWriteFile() {
        aiCount = new AtomicInteger();
        singleThreadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"readAndWriteFile>>" + aiCount.get());
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/ifiletest/","abcdefg"+ aiCount.get() +".txt"));

                aiCount.getAndIncrement();

                singleThreadExecutor.submit(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/ifiletest2/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/ifiletest3/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/ifiletest4/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
    }

    private void readAndWriteFile2() {
        aiCount = new AtomicInteger();
        singleThreadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"readAndWriteFile>>" + aiCount.get());
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/tftest/tfgarbled/","abcdefg"+ aiCount.get() +".txt"));
                aiCount.getAndIncrement();
                singleThreadExecutor.submit(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/tftest/tfgarbled2/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/tftest/tfgarbled3/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String randomFileName = "test" + (new Random().nextInt(1000000));
                FileUtils.createOrExistsFile(new File("/storage/sd-ext/tftest/tfgarbled4/",randomFileName +".txt"));
                newFixedThreadPool.execute(this);
            }
        });
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"onStartCommand()");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,  "onStartCommand()，intent：" + intent + ",flags:" + flags + ",startId:" + startId);
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind()");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    public class  MyBinder extends Binder{
        public SdcardReadWriteDealService getService(){
            return SdcardReadWriteDealService.this;
         }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}
