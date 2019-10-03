package com.photowall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.activity.MoveViewTestActivity;
import com.activity.ViewEventDispatchTestActivity;
import com.sp.spmultipleapp.R;
import com.viewtest.verifyviewmethod.one.InvalidateTestActivity;

public class PhotoWallTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall_test);

    }
}
