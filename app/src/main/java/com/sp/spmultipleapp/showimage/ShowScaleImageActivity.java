package com.sp.spmultipleapp.showimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.customview.ZoomImageView;
import com.sp.spmultipleapp.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date:2020/3/14,11:13
 * author:jy
 */
public class ShowScaleImageActivity extends Activity {
    ZoomImageView iv_scale;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scale_type_image);

        iv_scale  = findViewById(R.id.iv_scale);

       loadImage();
    }

    private void loadImage() {
        //03-14 13:51:00.658 E/AndroidRuntime(12957): java.lang.RuntimeException: Canvas: trying to draw too large(111120000bytes) bitmap.
        try {
//            InputStream inputStream = getAssets().open("tangyan.jpg");
            InputStream inputStream = getAssets().open("test_pic_compress/big_img_test.jpg");
//            InputStream inputStream = getAssets().open("qm.jpg");

            //获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            iv_scale.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * crash : for load "qm.jpg". 30000 * 926  = 27780000.
     *  itmap.Config ARGB_8888:: 27780000 * 4 = 111120000bytes = (111120000bytes / (1024*1024)) = 105MB
     *
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): java.lang.RuntimeException: Canvas: trying to draw too large(111120000bytes) bitmap.
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.DisplayListCanvas.throwIfCannotDraw(DisplayListCanvas.java:229)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.RecordingCanvas.drawBitmap(RecordingCanvas.java:98)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.graphics.drawable.BitmapDrawable.draw(BitmapDrawable.java:545)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.widget.ImageView.onDraw(ImageView.java:1360)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20355)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.updateDisplayListIfDirty(View.java:19195)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20068)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.drawChild(ViewGroup.java:4397)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4174)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.updateDisplayListIfDirty(View.java:19186)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20068)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.drawChild(ViewGroup.java:4397)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4174)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.updateDisplayListIfDirty(View.java:19186)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20068)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.drawChild(ViewGroup.java:4397)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4174)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20363)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at com.android.internal.widget.ActionBarOverlayLayout.draw(ActionBarOverlayLayout.java:515)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.updateDisplayListIfDirty(View.java:19195)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20068)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.drawChild(ViewGroup.java:4397)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4174)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.draw(View.java:20363)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at com.android.internal.policy.DecorView.draw(DecorView.java:785)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.View.updateDisplayListIfDirty(View.java:19195)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ThreadedRenderer.updateViewTreeDisplayList(ThreadedRenderer.java:686)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ThreadedRenderer.updateRootDisplayList(ThreadedRenderer.java:692)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ThreadedRenderer.draw(ThreadedRenderer.java:801)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewRootImpl.draw(ViewRootImpl.java:3540)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewRootImpl.performDraw(ViewRootImpl.java:3320)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2674)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1592)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:7659)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:1044)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.Choreographer.doCallbacks(Choreographer.java:840)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.Choreographer.doFrame(Choreographer.java:768)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:1030)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.os.Handler.handleCallback(Handler.java:873)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.os.Handler.dispatchMessage(Handler.java:99)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.os.Looper.loop(Looper.java:201)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at android.app.ActivityThread.main(ActivityThread.java:6820)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at java.lang.reflect.Method.invoke(Native Method)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
     * 03-14 13:51:00.658 E/AndroidRuntime(12957): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:922)
     */
}
