package com.sp.spmultipleapp.activity.taskstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sp.spmultipleapp.R;

public class TaskStackActivityA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_stack_a);
    }

    public void onClickToActivityB(View view) {
        Intent intent = new Intent(this,TaskStackActivityB.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
