package com.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.module.lrutest.ImageDownloader;
import com.sp.spmultipleapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LruCacheTestActivity extends AppCompatActivity {
    private static final String TAG = "LruCacheTestActivity";
    ImageView imageView;
    ImageDownloader imageDownloader;
    private String bitmapUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrucache_test);

        imageView = findViewById(R.id.imageView);

        loadData();

    }

    private void loadData() {

        imageDownloader  = new ImageDownloader();

        showBitmap(imageView);
    }

    public void showBitmap(View view) {
        Bitmap bitmap = imageDownloader.getBitmapFromMemCache("bitmap");
        if (bitmap == null) {
            bitmapUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1569923124&di=53150827bfc13883ecfcf30449c3d8e7&src=http://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/bd315c6034a85edfe555ff7748540923dc54758c.jpg";
            new BitmapThread(bitmapUrl).start();
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    class BitmapThread extends Thread {
        private String bitmapUrl;

        BitmapThread(String bitmapUrl) {
            this.bitmapUrl = bitmapUrl;
        }

        @Override
        public void run() {
            Log.i(TAG, "run: " + Thread.currentThread().getName());
            Bitmap bitmap = null;
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(bitmapUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                }
                imageDownloader.addBitmapToMemory("bitmap", bitmap);
                handler.obtainMessage(DOWNLOAD_IMAGE, bitmap).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static final int DOWNLOAD_IMAGE = 101;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "hanlder handleMessage: " + Thread.currentThread().getName());
            switch (msg.what) {
                case DOWNLOAD_IMAGE:
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
            }
        }
    };
    public void remove(View view) {
        imageDownloader.removeBitmapFromMemory("bitmap");
    }
}
