package com.sp.spmultipleapp.viewtest.defineimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 功能描述：一个简洁而高效的圆形ImageView
 *
 * @author (作者) edward（冯丰枫）
 * @link http://www.jianshu.com/u/f7176d6d53d2
 * 创建时间： 2018/4/17 0017
 * <p>
 *     onMeasure,width:100.0 height:100.0 radius:50.0
 *     initBitmapShader,bitmap.getWidth():1920 bitmap.getHeight():1080 scale:0.925
 *     采用float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());//效果不是从中间画的
 *     采用float scale = Math.min(width / bitmap.getWidth(), height / bitmap.getHeight());//效果居中,但可能高度不够时边缘会被一直延伸。
 *
 *      如何居中画图?
 *      要从BitmapShader中心画，宽高使用控件的（根据控件宽高计算圆的半径。）
 *
 * </p>
 */
public class CircleImageView extends AppCompatImageView {
    String TAG = "CircleImageView";
    private float width;
    private float height;
    private float radius;
    private Paint paint;
    private Matrix matrix;
    private float mBitmapWidth;
    private float mBitmapHeight;
    private float mScale;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿
        matrix = new Matrix();      //初始化缩放矩阵
    }

    /**
     * 测量控件的宽高，并获取其内切圆的半径
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;

        Log.d(TAG,"onMeasure,width:" + width + " height:" + height + " radius:" + radius);
        //for example onMeasure,width:1024.0 height:1080.0 radius:512.0
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG,"onLayout,left:" + left + " top:" + top + " right:" + right + " bottom:" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG,"onDraw call.");

        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            paint.setShader(initBitmapShader((BitmapDrawable) drawable));//将着色器设置给画笔
            canvas.drawCircle(width / 2, height / 2, radius, paint);//使用画笔在画布上画圆。使用控件宽高一半计算的来的半径。
            ////一下代码，效果是半圆，如何解释？
//            radius = Math.min(mBitmapWidth*mScale, mBitmapHeight*mScale) / 2;
//            canvas.drawCircle(mBitmapWidth*mScale / 2, mBitmapHeight*mScale / 2, radius, paint);//使用画笔在画布上画圆
            ////
            return;
        }
        super.onDraw(canvas);
    }

    /**
     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
     */
    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());// use max
//        float scale = Math.min(width / bitmap.getWidth(), height / bitmap.getHeight());
        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();
        mScale = scale;
        Log.d(TAG,"initBitmapShader,bitmap.getWidth():" + bitmap.getWidth() + " bitmap.getHeight():" + bitmap.getHeight() + " scale:" + scale);
        //for example initBitmapShader,bitmap.getWidth():1920 bitmap.getHeight():1080 scale:1.0
        return bitmapShader;
    }
}
