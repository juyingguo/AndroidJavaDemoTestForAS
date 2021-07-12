package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sp.spmultipleapp.R;

/**
 * 音频模块调试。
 */
public class AudioModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_module);

    }

    public void clickToRecordWav(View view) {
        startActivity(new Intent(this,AudioRecordWavActivity.class));
    }
}
