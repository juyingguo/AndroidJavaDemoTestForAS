package com.sp.spmultipleapp.activity;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.sp.spmultipleapp.R;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Date:2020/11/28,17:32
 * author:jy<br/>
 * 前后摄像头配置在函数{@link com.sp.spmultipleapp.activity.CameraTest#createCamera(int, int)}中
 */
public class CameraTest extends AppCompatActivity implements TextureView.SurfaceTextureListener ,EasyPermissions.PermissionCallbacks{
    private static final String TAG = "CameraTest";

    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate.");
//        setTheme(android.R.style.Theme_Black_NoTitleBar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏,需要当前activity继承Activity,否则无效果
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏,需要当前activity继承AppCompatActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_for_test);

        ((TextureView) findViewById(R.id.textureView)).setSurfaceTextureListener(this);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                camera.startPreview();
            }
        });

        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                camera.stopPreview();
            }
        });

        toCheckPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy.");
    }
    private static final int RC_PREM =100;
    private static final String[] perm ={Manifest.permission.CAMERA};
    private void toCheckPermission() {
        Log.d(TAG, "toCheckPermission.");
        if (EasyPermissions.hasPermissions(this,perm)){
            Log.d(TAG, "toCheckPermission,hasPermissions.");
            // 应用已经拥有了权限
        }else {
            // 应用没有拥有权限
            Log.d(TAG, "toCheckPermission,to call requestPermissions.");
            EasyPermissions.requestPermissions(
                    this,
                    "use cancel"/*getResources().getString(R.string.request_loacation)*/,//使用官方提供的弹窗(`AppSettingsDialog`)的时候会用到这个字符串,作为用户拒绝之后弹窗的内容
                    RC_PREM,
                    perm
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult,requestCode" + requestCode);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted,requestCode" + requestCode);
        Log.d(TAG, "onPermissionsGranted,perms.size()" + perms.size());
        if (perms.contains(Manifest.permission.CAMERA)){
            Log.d(TAG, "onPermissionsGranted,has camera permission.");
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied,requestCode" + requestCode);
        Log.d(TAG, "onPermissionsDenied,perms.size()" + perms.size());

    }
    private Camera createCamera(int width, int height) {
        Camera camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//        Camera camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        Log.d(TAG, "createCamera: " + width + ", " + height);

//        Camera.Parameters parameters = camera.getParameters();
//        Camera.CameraInfo camInfo = new Camera.CameraInfo();
//        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, camInfo);
//        Camera.Size bestSize = getOptimalPreviewSize(parameters.getSupportedPreviewSizes(), width, height);
//        Log.i(TAG, "createCamera best preview size: " + bestSize.width + ", " + bestSize.height);
//        parameters.setPreviewSize(bestSize.width, bestSize.height);
//        parameters.setPreviewSize(width, height);
//        Camera.Size bestPictureSize = getOptimalPictureSize(parameters.getSupportedPictureSizes(), width, height);
//        Log.i(TAG, "best picture size: " + bestPictureSize.width + ", " + bestPictureSize.height);
//        parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);
//        camera.setParameters(parameters);
        //调用setCameraDisplayOrientation;如果不调用，预览方向与实体不一致。
        setCameraDisplayOrientation(CameraTest.this, Camera.CameraInfo.CAMERA_FACING_BACK,camera);
        return camera;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow() call.");
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.i(TAG, "onSurfaceTextureAvailable: " + width + ", " + height);
        camera = createCamera(width, height);
        camera.setPreviewCallback(new Camera.PreviewCallback() {

            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                // Log.i(TAG, "onPreviewFrame: " + data.length);
            }
        });
        try {
            camera.setPreviewTexture(surface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.i(TAG, "onSurfaceTextureDestroyed");
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.lock();
        camera.release();
        return false;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.i(TAG, "onSurfaceTextureSizeChanged: " + width + ", " + height);
        // NOOP
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//        Log.i(TAG, "onSurfaceTextureUpdated");
        // NOOP
    }

    // ======================================== 工具方法 ========================================

    //获取相机最佳预览大小、图像大小
    //注意每台手机获取的PreviewSize顺序不一样
    public static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double aspectTolerance = 0.1;
        int width = w;
        int height = h;

        if (height > width) {
            int temp = width;
            width = height;
            height = temp;
        }

        double targetRatio = (double) width / height;
        if (sizes == null) {
            return null;
        }

        if (Math.abs(targetRatio - (double) 4 / 3) < 0.1) {
            targetRatio = (double) 4 / 3;
        } else {
            targetRatio = (double) 16 / 9;
        }

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;
        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > aspectTolerance) {
                continue;
            }
            //获取的最佳分辨率是经过压缩的
            if (Math.abs(size.height - targetHeight) < minDiff && (size.width <= w && size.height <= h)
                    && (optimalSize == null || optimalSize.width < size.width)) {
                optimalSize = size;
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            optimalSize = getOptimalSize(sizes, targetHeight);
        }
        return optimalSize;
    }

    public static Camera.Size getOptimalPictureSize(List<Camera.Size> sizes, int w, int h) {
        int minHeight = 1080;
        final double ASPECT_TOLERANCE = 0.2;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (minHeight <= size.height && Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (minHeight <= size.height && Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

    private static Camera.Size getOptimalSize(List<Camera.Size> sizes, int targetHeight) {
        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        for (Camera.Size size : sizes) {
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }
        if (optimalSize == null) {
            optimalSize = sizes.get(0);
        }
        return optimalSize;
    }

    /**
     * 设置方向。无论如何旋转，预览画面和看到的实体是一致的。
     * @param activity
     * @param cameraId
     * @param camera
     */
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        Log.d(TAG,"setCameraDisplayOrientation,cameraId:" + cameraId);
        android.hardware.Camera.CameraInfo info =  new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Log.d(TAG,"setCameraDisplayOrientation,rotation:" + rotation);
        Log.d(TAG,"setCameraDisplayOrientation,info.orientation:" + info.orientation);
          int degrees = 0;
          switch (rotation) {
              case Surface.ROTATION_0: degrees = 0; break;
              case Surface.ROTATION_90: degrees = 90; break;
              case Surface.ROTATION_180: degrees = 180; break;
              case Surface.ROTATION_270: degrees = 270; break;
          }

          int result;
          if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
              result = (info.orientation + degrees) % 360;
              result = (360 - result) % 360;  // compensate the mirror
          } else {  // back-facing
              result = (info.orientation - degrees + 360) % 360;
          }
          Log.d(TAG,"setCameraDisplayOrientation,result:" + result);
          camera.setDisplayOrientation(result);
      }

}