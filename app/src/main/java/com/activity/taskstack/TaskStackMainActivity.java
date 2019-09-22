package com.activity.taskstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sp.spmultipleapp.R;

public class TaskStackMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_stack_main);
    }

    public void onClickToActivityA(View view) {
        Intent intent = new Intent(this,TaskStackActivityA.class);
        startActivity(intent);
    }
}
