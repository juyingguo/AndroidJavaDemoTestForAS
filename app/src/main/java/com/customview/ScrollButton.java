package com.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class ScrollButton extends android.support.v7.widget.AppCompatButton {
    private String TAG = ScrollButton.class.getSimpleName();
    Scroller scroller;
    int direction = -1;

    public ScrollButton(Context context) {

        this(context,null);
    }

    public ScrollButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        if(scroller!=null){
            if(scroller.computeScrollOffset()){//判断scroll是否完成
                ((View) getParent()).scrollTo(
                        scroller.getCurrX(),scroller.getCurrY()
                );//执行本段位移

                invalidate();//进行下段位移
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"onTouchEvent(),getX():"  + getX() + ",getY():" + getY());
                scroller.startScroll(((int) getX()), 0/*((int) getY())*/, ((int) getX())*direction,
                        0/*((int) getY())*direction*/);//开始位移，真正开始是在下面的invalidate
                direction*=-1;//改变方向
                invalidate();//开始执行位移
                break;
        }
        return super.onTouchEvent(event);
    }
}