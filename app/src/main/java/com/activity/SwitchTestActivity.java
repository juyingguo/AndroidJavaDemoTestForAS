package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sp.spmultipleapp.R;

public class SwitchTestActivity extends AppCompatActivity {
    String TAG = "SwitchTestActivity";
    Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_test);
        mSwitch = findViewById(R.id.id_switch);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG,"onCheckedChanged,isChecked:" + isChecked);
            }
        });
    }

    public void onClickBtn(View view) {
        mSwitch.setChecked(false);
    }
}
