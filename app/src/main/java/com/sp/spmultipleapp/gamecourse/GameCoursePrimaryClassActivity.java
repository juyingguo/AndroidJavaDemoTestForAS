package com.sp.spmultipleapp.gamecourse;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.spmultipleapp.R;


/**
 * @date 2019/8/13
 * @author jy
 * @description  游戏课程之小班
 * @Version
 */
public class GameCoursePrimaryClassActivity extends Activity {


    private TextView tv_result_asr;
    private TextView tv_result_touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_course_primary_class);
        tv_result_asr = (TextView) findViewById(R.id.tv_result_asr);
        tv_result_touch = (TextView) findViewById(R.id.tv_result_touch);

        IntentFilter filter = new IntentFilter(GameCourseConfig.ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE);
        filter.addAction(GameCourseConfig.ACTION_TOUCH_PAD_PRESSED_FOR_GAME_COURSE_MODE);
        registerReceiver(receiver,filter);
    }
    String stringExtra = null;
    String regionExtra = null;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (GameCourseConfig.ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE.equals(intent.getAction())){
                    stringExtra = intent.getStringExtra(GameCourseConfig.EXTRA_ASR_RESULT);
                    tv_result_asr.setText(stringExtra);
                }else if (GameCourseConfig.ACTION_TOUCH_PAD_PRESSED_FOR_GAME_COURSE_MODE.equals(intent.getAction())){
                    regionExtra = intent.getStringExtra(GameCourseConfig.EXTRA_TOUCH_PAD_DETECTED_REGION);
                    tv_result_touch.setText(regionExtra);
                }
            }
        }
    };
    public void startGameCourseMode(View view) {
        sendBroadcast(new Intent(GameCourseConfig.ACTION_ENTER_GAME_COURSE_MODE));
    }

    public void exitGameCourseMode(View view) {
        sendBroadcast(new Intent(GameCourseConfig.ACTION_EXIT_GAME_COURSE_MODE));

    }

    @Override
    protected void onPause() {
        super.onPause();
        exitGameCourseMode(null);
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
