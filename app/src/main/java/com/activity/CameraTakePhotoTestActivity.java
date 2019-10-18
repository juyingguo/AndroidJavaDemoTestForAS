package com.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sp.spmultipleapp.R;
import com.utils.BitmapUtil;
import com.utils.LogUtil;
import com.utils.PictureUtil;
import com.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CameraTakePhotoTestActivity extends AppCompatActivity {
    private static final String TAG = "CameraTakePhotoTestActivity";
    Unbinder butterKnifeBind = null;
    public final static int REQUEST_CODE_CAMERA = 0x1002;
    public final static int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 0x1001;
    public static final int REQUEST_CAMERA_CAPTURE = 1002;
    private Context mContext;
    @BindView(R.id.button_take_photo)
    public Button button_take_photo;
    String path = "";
    String basePath = Environment.getExternalStorageDirectory() + "/take_photo/";
    private String permissionInfo;
    private final int SDK_PERMISSION_REQUEST = 127;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_take_photo_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

        getPersimmions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }

    @OnClick({R.id.button_take_photo})
    public void clickView(View view) {
        if (view.getId() == R.id.button_take_photo){
            openCamera();
        }
    }

    private void openCamera() {
        //  申请相机权限
        if (Build.VERSION.SDK_INT >= 24) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
            } else {
                initCamera();
            }
        } else {
            initCamera();
        }
    }
    private void initCamera() {
        if (Build.VERSION.SDK_INT >= 24) {
            if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android
                        .Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
                return;
            } else {
                createFile();
            }
        } else {
            createFile();
        }

        String name = System.currentTimeMillis() + ".png";
        path = basePath + name;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, mContext.getPackageName() + ".fileprovider", new File(basePath, name)));
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(basePath, name)));
            }
            startActivityForResult(intent, REQUEST_CAMERA_CAPTURE);
        } else {
            ToastUtils.showToast(mContext,"str_sdcard_error");
        }
    }
    //创建文件夹
    private void createFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }
    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            /*if(getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if(getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }*/
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //相机权限已经申请
                initCamera();
            } else {
                ToastUtils.showToast(mContext,"str_not_camera_permissions");

            }
        }
        //读写权限已经申请
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                createFile();
                initCamera();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA_CAPTURE && resultCode == RESULT_OK) {
            LogUtil.i(TAG, "onActivityResult,path:" + path);
            int degree = PictureUtil.readPictureDegree(path);
            LogUtil.i(TAG, "onActivityResult,path:" + degree);
            if (degree != 0){
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                LogUtil.e(TAG,"onActivityResult bitmap width = "+ bitmap.getWidth() + " height = "+ bitmap.getHeight() );
                Bitmap rotateBitmap = BitmapUtil.rotateBitmap(degree,bitmap);
                try {
                    String newPath = PictureUtil.rawBitmapToFilepath(rotateBitmap, path, 100);
                    LogUtil.i(TAG, "onActivityResult,newPath:" + newPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
        }
    }
}
