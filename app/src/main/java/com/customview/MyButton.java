package com.customview;
 
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
 
public class MyButton extends android.support.v7.widget.AppCompatButton
{
	private static final String TAG = MyButton.class.getSimpleName();
 
	public MyButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
 
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
 
		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onTouchEvent ACTION_UP");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event)
	{
//		getParent().requestDisallowInterceptTouchEvent(true);//

		int action = event.getAction();
 
		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "dispatchTouchEvent ACTION_UP");
			break;
 
		default:
			break;
		}
		return super.dispatchTouchEvent(event);
	}
 
	
}
