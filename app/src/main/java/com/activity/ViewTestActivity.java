package com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.viewtest.verifyviewmethod.one.InvalidateTestActivity;

public class ViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

    }

    public void invalidateTestActivity(View view) {
        startActivity(new Intent(this, InvalidateTestActivity.class));
    }

    public void moveViewTestActivity(View view) {
        startActivity(new Intent(this, MoveViewTestActivity.class));
    }

    public void viewEventDispatchTestActivity(View view) {
        startActivity(new Intent(this, ViewEventDispatchTestActivity.class));
    }

    public void recyclerViewTest(View view) {
        startActivity(new Intent(this, RecyclerViewTestActivity.class));

    }

    public void onSwitchTest(View view) {
        startActivity(new Intent(this, SwitchTestActivity.class));
    }
}
