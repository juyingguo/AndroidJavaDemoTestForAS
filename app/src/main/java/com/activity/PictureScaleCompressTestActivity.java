package com.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.utils.BitmapUtil;
import com.utils.FileUtils;
import com.utils.LogUtil;
import com.utils.PictureUtil;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PictureScaleCompressTestActivity extends AppCompatActivity {
    private static final String TAG = "PictureScaleCompressTestActivity";
    Unbinder butterKnifeBind = null;

    private Context mContext;

    String path = "";
    String basePath = Environment.getExternalStorageDirectory() + "/take_photo/";
    String compressBasePath =  basePath + "compress";
    private String permissionInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_compress_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
    @OnClick({
            R.id.btn_picture_scale
            ,R.id.btn_picture_compress
            ,R.id.btn_picture_luban_compress
    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_picture_scale){
            pictureScaleTest();

        }else  if (view.getId() == R.id.btn_picture_compress){
            pictureQualityCompressTest();
        }else  if (view.getId() == R.id.btn_picture_luban_compress){
            pictureLubanCompressTest();
        }
    }

    private void pictureLubanCompressTest() {
        LogUtil.d(TAG,"pictureLubanCompressTest");
        FileUtils.createOrExistsDir(compressBasePath);
        path = compressBasePath + File.separator + "big_img_test02.jpg";
        boolean fileExists = FileUtils.isFileExists(path);

        LogUtil.d(TAG,"pictureLubanCompressTest,path:" + path + ",fileExists:" + fileExists);
        final  String lubanTargetDir = compressBasePath + File.separator + "luban";
        FileUtils.createOrExistsDir(lubanTargetDir);
        Luban.with(this)
                .load(path)
                .ignoreBy(500)
                .setTargetDir(lubanTargetDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        LogUtil.d(TAG,"onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        LogUtil.d(TAG,"onSuccess,file:" + file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        LogUtil.d(TAG,"onError,e:" + e);
                    }
                }).launch();
    }


    private void pictureScaleTest() {

        /**
         * #01.
         * PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 1, 100);
         * fileSize:4408k ; 3136*4224
         * 2019-10-22 11:09:59.736 15021-15126/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,path:/storage/emulated/0/take_photo/compress/big_img_test.jpg,bitmapSize:5704.4697265625
         * 2019-10-22 11:09:59.736 15021-15126/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:4408
         *
         * 2019-10-22 11:10:01.363 15021-15126/com.sp.spmultipleapp E/PictureUtil: pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = 3136 height = 4224 length = 5704.4697
         *
         * 2019-10-22 11:10:02.678 15021-15126/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:5859.12109375
         * 2019-10-22 11:10:02.679 15021-15126/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:5704
         *
         * #02 PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 2, 100);
         *
         * 2019-10-22 11:25:58.838 19269-19350/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,path:/storage/emulated/0/take_photo/compress/big_img_test.jpg,bitmapSize:5704.4697265625
         * 2019-10-22 11:25:58.839 19269-19350/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:4408
         *
         * 2019-10-22 11:25:59.796 19269-19350/com.sp.spmultipleapp E/PictureUtil: pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = 1568 height = 2112 length = 1693.3809
         *
         * 2019-10-22 11:26:00.158 19269-19350/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:1722.822265625
         * 2019-10-22 11:26:00.158 19269-19350/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:1693
         *
         *
         * #03 PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 4, 100);
         *
         * 2019-10-22 11:43:10.864 22601-22670/com.sp.spmultipleapp E/PictureUtil: pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = 784 height = 1056 length = 419.5332
         *
         * 2019-10-22 11:43:10.958 22601-22670/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:427.1943359375
         * 2019-10-22 11:43:10.958 22601-22670/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:419
         *
         * #04 PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 2, 95);
         *
         * 2019-10-22 15:55:47.883 7743-7792/com.sp.spmultipleapp E/PictureUtil: pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = 1568 height = 2112 length = 713.2246
         *
         *  2019-10-22 15:55:48.147 7743-7792/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:1193.66015625
         * 2019-10-22 15:55:48.147 7743-7792/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,fileSize:713
         *
         * #05 PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 2, 90);
         *
         * 2019-10-25 10:39:02.014 20575-20627/com.sp.spmultipleapp E/PictureUtil: pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = 1568 height = 2112 length = 433.4336
         *
         * 2019-10-25 10:39:02.244 20575-20627/com.sp.spmultipleapp I/PictureScaleCompressTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:932.755859375
         * 2019-10-25 10:39:02.245 20575-20627/com.sp.spmultipleapp I/PictureScaleCompressTestActivity: subscribe,fileSize:433
         *
         */

        FileUtils.createOrExistsDir(compressBasePath);
        path = compressBasePath + File.separator + "big_img_test.jpg";
        LogUtil.i(TAG, "pictureQualityCompressTest,path:" + path);

        LogUtil.i(TAG, "pictureQualityCompressTest,FileUtils.isFileExists(path):" + FileUtils.isFileExists(path));
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                LogUtil.d(TAG, "subscribe,currentThread():" + Thread.currentThread());
                LogUtil.d(TAG, "subscribe,currentThread().getName():" + Thread.currentThread().getName());
                int degree = PictureUtil.readPictureDegree(path);
                String newPath2 = path;
                LogUtil.i(TAG, "subscribe,degree:" + degree);
                if (degree > 0) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        LogUtil.e(TAG, "subscribe bitmap width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
                        Bitmap rotateBitmap = BitmapUtil.rotateBitmap(degree, bitmap);
                        String newPath = PictureUtil.rawBitmapToFilepath(rotateBitmap, path, 100);
                        LogUtil.i(TAG, "subscribe,newPath:" + newPath);

                                    /*Bitmap bitmap2 = BitmapFactory.decodeFile(newPath);
                                    String newPath2 = PictureUtil.rawBitmapToFilepath(rotateBitmap, newPath, 60);//再次质量压缩
                                    LogUtil.i(TAG, "onActivityResult,newPath:" + newPath2);*/
                        newPath2 = PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 2, 100);//再次质量压缩
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (degree == 0){
                    try {
                        long fileSize = FileUtils.getFileSize(path)/1024;
                        LogUtil.i(TAG, "subscribe,fileSize:" + fileSize);
                        if(fileSize < PictureUtil.MIN_COMPRESS_PICTURE_FILE_SIZE){
                            @SuppressLint("DefaultLocale") String format = String.format("fileSize %d < %f not compress", fileSize, PictureUtil.MIN_COMPRESS_PICTURE_FILE_SIZE);
                            LogUtil.i(TAG, "subscribe ," + format);
                            emitter.onNext(newPath2);
                            return ;
                        }

                        Bitmap bitmap = BitmapFactory.decodeFile(path);
//                        double bitmapSize = BitmapUtil.bitmapSize(bitmap);
                        double bitmapSize = BitmapUtil.bitmapSize(path);
                        LogUtil.i(TAG, "subscribe,path:" + path + ",bitmapSize:" + bitmapSize);
                        LogUtil.i(TAG, "subscribe,fileSize:" + fileSize);

                        newPath2 = PictureUtil.pictureScaleAndQualityCompressToFilepath(path, 2, 90);//再次质量压缩
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2);

                        BitmapUtil.recycleBitmap(bitmap);
                        bitmap = BitmapFactory.decodeFile(newPath2);
                        bitmapSize = BitmapUtil.bitmapSize(bitmap);
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2 + ",bitmapSize:" + bitmapSize);
                        fileSize = FileUtils.getFileSize(newPath2)/1024;
                        LogUtil.i(TAG, "subscribe,fileSize:" + fileSize);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                emitter.onNext(newPath2);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String filePath) throws Exception {
                        LogUtil.d(TAG, "accept,currentThread():" + Thread.currentThread());
                        LogUtil.d(TAG, ">>accept()>>filePath:" + filePath);
                    }
                });
    }


    private void pictureQualityCompressTest() {
        /**
         * 2019-10-21 15:46:14.731 22718-22778/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,path:/storage/emulated/0/take_photo//compress/big_img_test.jpg,bitmapSize:5.570771217346191
         * 2019-10-21 15:46:17.590 22718-22778/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,newPath2:/storage/emulated/0/take_photo/compress/big_img_test_deal.jpg,bitmapSize:5.721797943115234
         *
         * quality 100 compress
         * 5.5-》5.5
         *
         *
         * quality 50 compress
         * 5.5-》0.455994
         * 在压缩过程中，且quality !=100 时，才可以获取，该bitmap保存为文件时的大小。否则只能通过普通文件对待，获取文件大小。【还需深入验证，分析】。
         * 通过读取图片文件，到bitmap,再压缩为输出流ByteArrayOutputStream。
         *
         * bitmapSize:1.4005451202392578.
         *
         *
         *  2019-10-21 17:28:27.578 11378-11445/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,path:/storage/emulated/0/take_photo/compress/big_img_test.jpg,bitmapSize:5704.4697265625
         *  2019-10-21 17:35:55.380 12770-12824/com.sp.spmultipleapp I/CameraTakePhotoTestActivity: subscribe,path:/storage/emulated/0/take_photo/compress/big_img_test.jpg,bitmapSize:5704.4697265625
         *
         */
//        path = basePath + File.separator + "compress" + "big_img_test_landscape.png";
        FileUtils.createOrExistsDir(compressBasePath);
        path = compressBasePath + File.separator + "big_img_test.jpg";
        LogUtil.i(TAG, "pictureQualityCompressTest,path:" + path);

        LogUtil.i(TAG, "pictureQualityCompressTest,FileUtils.isFileExists(path):" + FileUtils.isFileExists(path));
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                int degree = PictureUtil.readPictureDegree(path);
                String newPath2 = path;
                LogUtil.i(TAG, "subscribe,degree:" + degree);
                if (degree > 0) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        LogUtil.e(TAG, "subscribe bitmap width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
                        Bitmap rotateBitmap = BitmapUtil.rotateBitmap(degree, bitmap);
                        String newPath = PictureUtil.rawBitmapToFilepath(rotateBitmap, path, 100);
                        LogUtil.i(TAG, "subscribe,newPath:" + newPath);

                                    /*Bitmap bitmap2 = BitmapFactory.decodeFile(newPath);
                                    String newPath2 = PictureUtil.rawBitmapToFilepath(rotateBitmap, newPath, 60);//再次质量压缩
                                    LogUtil.i(TAG, "onActivityResult,newPath:" + newPath2);*/
                        newPath2 = PictureUtil.pictureScaleAndQualityCompressToFilepath(newPath, 2, 100);//再次质量压缩
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (degree == 0){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
//                        double bitmapSize = BitmapUtil.bitmapSize(bitmap);
                        double bitmapSize = BitmapUtil.bitmapSize(path);
                        LogUtil.i(TAG, "subscribe,path:" + path + ",bitmapSize:" + bitmapSize);
                        long fileSize = FileUtils.getFileSize(path);
                        LogUtil.i(TAG, "subscribe,fileSize:" + fileSize);

                        newPath2 = PictureUtil.pictureScaleAndQualityCompressToFilepath(path, 1, 50);//再次质量压缩
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2);

                        BitmapUtil.recycleBitmap(bitmap);
                        bitmap = BitmapFactory.decodeFile(newPath2);
                        bitmapSize = BitmapUtil.bitmapSize(bitmap);
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2 + ",bitmapSize:" + bitmapSize);
                        bitmapSize = BitmapUtil.bitmapSize(newPath2);
                        LogUtil.i(TAG, "subscribe,newPath2:" + newPath2 + ",bitmapSize:" + bitmapSize);
                        fileSize = FileUtils.getFileSize(newPath2);
                        LogUtil.i(TAG, "subscribe,fileSize:" + fileSize);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                emitter.onNext(newPath2);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String filePath) throws Exception {
                        LogUtil.d(TAG, ">>accept()>>filePath:" + filePath);
                    }
                });
//        subscribe.dispose();
    }
}
