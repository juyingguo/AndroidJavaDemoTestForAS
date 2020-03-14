package com.sp.spmultipleapp.showbigimage.one;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhy on 15/5/16.
 */
public class LargeImageView extends View
{
    private String TAG = "LargeImageView";
    private BitmapRegionDecoder mDecoder;
    /**
     * 图片的宽度和高度
     */
    private int mImageWidth, mImageHeight;
    /**
     * 绘制的区域
     */
    private volatile Rect mRect = new Rect();

    private MoveGestureDetector mDetector;


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
        mDetector = new MoveGestureDetector(getContext(), new MoveGestureDetector.SimpleMoveGestureDetector()
        {
            @Override
            public boolean onMove(MoveGestureDetector detector)
            {
                int moveX = (int) detector.getMoveX();
                int moveY = (int) detector.getMoveY();
                Log.d(TAG,"onMove,moveX:" + moveX);//
                Log.d(TAG,"onMove,moveY:" + moveY);
                Log.d(TAG,"onMove,getWidth():" + getWidth());
                Log.d(TAG,"onMove,getHeight():" + getHeight());
                if (mImageWidth > getWidth() && Math.abs(moveX)>=1)//Math.abs(moveX)>=5 在执行绘制操作，防止绘制过于频繁，显示卡顿
                {
                    mRect.offset(-moveX, 0);
                    checkWidth();
                }
                if (mImageHeight > getHeight() && Math.abs(moveY)>=1)//Math.abs(moveY)>=5 在执行绘制操作，防止绘制过于频繁，显示卡顿
                {
                    mRect.offset(0, -moveY);

                    checkHeight();
                }

                invalidate();//放到外面执行一次即可。防止多次调用卡顿

                return true;
            }
        });
    }


    private void checkWidth()
    {

        Log.d(TAG,"checkWidth,mRect:" + mRect);
        Rect rect = mRect;
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

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
        Log.d(TAG,"checkHeight,mRect:" + mRect);
        Rect rect = mRect;
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


    public LargeImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDetector.onToucEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Log.d(TAG,"onDraw,mRect:" + mRect);
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

        Log.d(TAG,"onMeasure,getMeasuredWidth():" + width);
        Log.d(TAG,"onMeasure,getMeasuredHeight():" + height);
        Log.d(TAG,"onMeasure,imageWidth:" + imageWidth);
        Log.d(TAG,"onMeasure,imageHeight:" + imageHeight);

        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;

        Log.d(TAG,"onMeasure,mRect:" + mRect);

    }


}
