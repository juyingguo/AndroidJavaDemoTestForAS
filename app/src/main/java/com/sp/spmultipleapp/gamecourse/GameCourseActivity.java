package com.sp.spmultipleapp.gamecourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sp.spmultipleapp.R;


/**
 * @date 2019/8/13
 * @author jy
 * @description
 * @Version
 */
public class GameCourseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_course);
    }

    public void clickPrimaryClass(View view) {
        startActivity(new Intent(this,GameCoursePrimaryClassActivity.class));
    }
}
