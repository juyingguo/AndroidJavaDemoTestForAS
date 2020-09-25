package com.sp.spmultipleapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.utils.LogUtil;

public class MoboPlayerTestActivity extends Activity {
    private String TAG = MoboPlayerTestActivity.class.getSimpleName();
    Unbinder butterKnifeBind = null;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.w(TAG, "onCreate>>:");

        setContentView(R.layout.activity_moboplayer_test);

        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_kangxuan,R.id.tv_2,R.id.tv_3,R.id.tv_4,R.id.tv_5})
    public void clickView(View view){
        String url = "http://edu.ibotn.com/nkplay.mp4";
        if (view.getId() == R.id.tv_kangxuan){
            url = "http://edu.ibotn.com/nkplay.mp4";
        }else if (view.getId() == R.id.tv_2){
            url = "http://edu.ibotn.com/download/cgtx/ROUTINE_REMINDER_FILE/04教学活动/儿童歌曲 - 排队歌.mp4";
        }else if (view.getId() == R.id.tv_3){
            url = "http://edu.ibotn.com/download/cgtx/ROUTINE_REMINDER_FILE/12美味加餐-洗手歌/儿童歌曲 - 洗手歌.mp4";
        } else if (view.getId() == R.id.tv_4){
            url = "http://dodovideo.rsvptech.cn/rsvpvideo/glgldh/091hz.mp4";
        } else if (view.getId() == R.id.tv_5){
            url = "http://dodovideo.rsvptech.cn/rsvpvideo/glgldh/090xthk.mp4";
        }
        playVideoWeb(url);
    }


    public void playVideoWeb(final String url) {
        LogUtil.d(TAG, TAG + ">>>>playVideoWeb:" + url);
        if (TextUtils.isEmpty(url)){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri uri = Uri.parse(url);  //fromFile
        intent.setDataAndType(uri, "video/*");
        if (intent != null && intent.resolveActivity(getPackageManager()) != null){
            mContext.startActivity(intent);

        }else {
            Toast.makeText(mContext, "please install player", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.w(TAG, "onStart>>:");
    }

    @Override
    protected void onStop() {
        super.onStop();

        LogUtil.w(TAG, "onStop>>:");
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.w(TAG, "onDestroy>>:");
        butterKnifeBind.unbind();
    }
}
