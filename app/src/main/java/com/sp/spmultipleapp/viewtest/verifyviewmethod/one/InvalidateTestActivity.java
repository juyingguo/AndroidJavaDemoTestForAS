package com.sp.spmultipleapp.viewtest.verifyviewmethod.one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//————————————————
//
//简单例子解释invalidate()， requestLayout() (常用还是需要知道的)
//    版权声明：本文为CSDN博主「王亟亟」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/ddwhan0123/article/details/50601782
public class InvalidateTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_invalidate_test);

        setContentView(new DemoView(this));
    }
    public class DemoView extends View {
        Paint paint;
        int paintSize=10;
        boolean flag=true;


        public DemoView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(500, 500);
            Log.d("DemoView","--->onMeasure");
        }


        //存在canvas对象，即存在默认的显示区域
        @Override
        public void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            paint = new Paint();
            paint.setTextSize(paintSize);


            if(flag){
                drawView(canvas, Color.CYAN);
                paintSize++;

                if (paintSize < 80) {
                    flag=true;
                }else{
                    flag=false;
                }
            } else{
                drawView(canvas,Color.GREEN);
                paintSize--;

                if(paintSize<30){
                    flag=true;
                }else{
                    flag=false;
                }
            }
            invalidate(); // 方法1
//            requestLayout();//方法2
        }

        private void drawView(Canvas canvas, int col) {
            Log.i("DemoView", "DemoView is onDraw   paintSize= "+paintSize);
            //加粗
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            paint.setColor(Color.RED);
            canvas.drawColor(Color.BLUE);
            canvas.drawRect(0, 0, 300, 300, paint);
            paint.setColor(col);
            canvas.drawText("DemoView", 100, 240, paint);
        }
    }

}
