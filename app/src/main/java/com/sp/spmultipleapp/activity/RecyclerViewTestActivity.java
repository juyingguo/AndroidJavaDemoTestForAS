package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sp.spmultipleapp.R;

public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test);

    }

    public void smartRefreshLayout(View view) {
        startActivity(new Intent(this, RecyclerViewWithSmartRefreshLayoutTestActivity.class));
    }
}
