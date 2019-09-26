package com.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;

import com.sp.spmultipleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoveViewTestActivity extends AppCompatActivity {
    private String TAG = MoveViewTestActivity.class.getSimpleName();
    Button button;
    Unbinder butterKnifeBind = null;
    @BindView(R.id.button_move)
    Button button_move;
    @BindView(R.id.scrollButton)
    Button scrollButton;
    @BindView(R.id.button_move_by_scroller)
    Button button_move_by_scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_test);
        butterKnifeBind = ButterKnife.bind(this);
        button = findViewById(R.id.button);
        scroller = new Scroller(this);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"onTouch(),event.getX():"  + event.getX() + ",event.getY():" + event.getY());
                Log.d(TAG,"onTouch(),v.getX():"  + v.getX() + ",v.getY():" + v.getY());
                Log.d(TAG,"onTouch(),button.getX():"  + button.getX() + ",button.getY():" + button.getY());

                Log.d(TAG,"onTouch(),button.getLeft():"  + button.getLeft());
                Log.d(TAG,"onTouch(),button.getTranslationX():"  + button.getTranslationX());
                Log.d(TAG,"onTouch(),event.getRawX():"  + event.getRawX() + ",event.getRawY():" + event.getRawY());
                return false;
            }
        });

        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick(),button.getScrollX():"  + button.getScrollX() + ",button.getScrollY():" + button.getScrollY());
                button.scrollBy(20,0);
                Log.d(TAG,"onClick(),after scrollBy,button.getScrollX():"  + button.getScrollX() + ",button.getScrollY():" + button.getScrollY());
            }
        });

        button_move_by_scroller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick(),button.getScrollX():"  + button.getScrollX() + ",button.getScrollY():" + button.getScrollY());
                scroller.startScroll(0,0,50,0);
                scroller.extendDuration(2000);
                Log.d(TAG,"onClick(),after scrollBy,button.getScrollX():"  + button.getScrollX() + ",button.getScrollY():" + button.getScrollY());
                handler.sendEmptyMessage(0);
            }
        });
    }
    Scroller scroller = null;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(scroller.computeScrollOffset()){
                ((View) button).scrollTo(
                        scroller.getCurrX(),scroller.getCurrY()
                );
                sendEmptyMessage(0);
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (butterKnifeBind != null){
            butterKnifeBind.unbind();
        }
    }
}
