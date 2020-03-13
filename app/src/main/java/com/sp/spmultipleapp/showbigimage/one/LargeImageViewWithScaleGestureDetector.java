package com.sp.spmultipleapp.showbigimage.one;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eink on 15/5/16.
 */
public class LargeImageViewWithScaleGestureDetector extends View
{
    private String TAG = LargeImageViewWithScaleGestureDetector.class.getSimpleName();
    private BitmapRegionDecoder mDecoder;
    /**
     * 图片的宽度和高度
     */
    private int mImageWidth, mImageHeight;
    /**
     * 绘制的区域
     */
    private volatile Rect mRect = new Rect();

    private ScaleGestureDetector mScaleGestureDetector;


    private static final BitmapFactory.Options options = new BitmapFactory.Options();

    static
    {
        options.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    public void setInputStream(InputStream is)
    {
        try
        {
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            // Grab the bounds for the scene dimensions
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, tmpOptions);
            mImageWidth = tmpOptions.outWidth;
            mImageHeight = tmpOptions.outHeight;
            Log.d(TAG,"setInputStream,mImageWidth:" + mImageWidth);
            Log.d(TAG,"setInputStream,mImageHeight:" + mImageHeight);
            requestLayout();
            invalidate();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {

            try
            {
                if (is != null) is.close();
            } catch (Exception e)
            {
            }
        }
    }
    public void init()
    {
        mScaleGestureDetector = new ScaleGestureDetector(getContext(),new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                Log.d(TAG,"onScaleBegin");
                return super.onScaleBegin(detector);
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                Log.d(TAG,"onScale,detector.getFocusX():" + detector.getFocusX());
                Log.d(TAG,"onScale,detector.getFocusY():" + detector.getFocusY());
                Log.d(TAG,"onScale,detector.getPreviousSpanX():" + detector.getPreviousSpanX());
                Log.d(TAG,"onScale,detector.getPreviousSpanY():" + detector.getPreviousSpanY());
                Log.d(TAG,"onScale,detector.getCurrentSpanX():" + detector.getCurrentSpanX());
                Log.d(TAG,"onScale,detector.getCurrentSpanY():" + detector.getCurrentSpanY());
                int moveX = (int) (detector.getCurrentSpanX() - detector.getPreviousSpanX());//
                int moveY = (int) (detector.getCurrentSpanY() - detector.getPreviousSpanY());
                Log.d(TAG,"onScale,moveX:" + moveX);
                Log.d(TAG,"onScale,moveY:" + moveY);
                Log.d(TAG,"onScale,getWidth():" + getWidth());
                Log.d(TAG,"onScale,getHeight():" + getHeight());
                if (mImageWidth > getWidth())
                {
                    mRect.offset(-moveX, 0);
                    checkWidth();
                    controlExecuteInvalidate();
                }
                if (mImageHeight > getHeight())
                {
                    mRect.offset(0, -moveY);
                    checkHeight();
                    controlExecuteInvalidate();
                }
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                Log.d(TAG,"onScaleEnd");
                super.onScaleEnd(detector);
            }
        });
    }
    long mCurrentTime = 0L;
    long mLastTime = 0L;
    private void controlExecuteInvalidate(){
        mCurrentTime = SystemClock.elapsedRealtime();
        if ((mCurrentTime - mLastTime) < 500){
            return;
        }
        Log.d(TAG,"controlExecuteInvalidate,time interval >=500 continue invoke invalidate().");
        invalidate();
        mLastTime = mCurrentTime;
    }
    private void checkWidth()
    {
        Rect rect = mRect;

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;
        Log.d(TAG,"checkWidth,rect:" + rect);
        if (rect.right > imageWidth)
        {
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }

        if (rect.left < 0)
        {
            rect.left = 0;
            rect.right = getWidth();
        }
    }

    private void checkHeight()
    {

        Rect rect = mRect;
        Log.d(TAG,"checkHeight,rect:" + rect);
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        if (rect.bottom > imageHeight)
        {
            rect.bottom = imageHeight;
            rect.top = imageHeight - getHeight();
        }

        if (rect.top < 0)
        {
            rect.top = 0;
            rect.bottom = getHeight();
        }
    }


    public LargeImageViewWithScaleGestureDetector(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Bitmap bm = mDecoder.decodeRegion(mRect, options);
        canvas.drawBitmap(bm, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + 500;
        mRect.bottom = mRect.top + 300;

    }


}
