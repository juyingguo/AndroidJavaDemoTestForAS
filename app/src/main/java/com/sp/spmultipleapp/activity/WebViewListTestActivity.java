package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.viewtest.verifyviewmethod.one.InvalidateTestActivity;

public class WebViewListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_list_test);

    }

    public void webViewTest(View view) {
        startActivity(new Intent(this, WebViewTestActivity.class));
    }

    public void jQueryGameEliminate(View view) {
        startActivity(new Intent(this, WebViewEliminateTestActivity.class));
    }

    public void html5spacebattleship(View view) {
        startActivity(new Intent(this, WebViewSpaceBattleShipTestActivity.class));
    }

    public void javaJsCallEachOther(View view) {
        startActivity(new Intent(this, WebViewJsJavaCallEachOtherActivity.class));
    }
}
