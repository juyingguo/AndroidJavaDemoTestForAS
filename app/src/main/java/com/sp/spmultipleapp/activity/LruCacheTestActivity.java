package com.sp.spmultipleapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sp.spmultipleapp.lrutest.ImageDownloader;
import com.sp.spmultipleapp.R;
import com.utils.ImageUtils;

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
            bitmapUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570106276989&di=41dd2795f11855097ac59f361e8b569d&imgtype=0&src=http%3A%2F%2Fimg3.redocn.com%2F20120331%2FRedocn_2012033106470673.jpg";
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
//                    bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmap = ImageUtils.decodeSampledBitmapFromStream02(inputStream,200,300);
                }
                Log.i(TAG, "run，bitmap: " + bitmap);
                if (bitmap != null){
                    imageDownloader.addBitmapToMemory("bitmap", bitmap);
                    handler.obtainMessage(DOWNLOAD_IMAGE, bitmap).sendToTarget();

                }
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
