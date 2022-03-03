package com.sp.spmultipleapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;

import com.sp.spmultipleapp.fragment.PreferenceSetTestFragment;
import com.utils.DateUtils;

import java.util.Date;

public class PreferenceTestActivity extends AppCompatActivity {
    private Date mDate;
    public static Preference mUptime;
    public static Preference mCurrentTime;
    private Handler mHandler = new MyHandler();
    private static final int EVENT_UPDATE_STATS = 500;
    PreferenceSetTestFragment fragment;
    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case EVENT_UPDATE_STATS:
                    updateTimes();
                    sendEmptyMessageDelayed(EVENT_UPDATE_STATS, 1000);
                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_test);
        fragment = new PreferenceSetTestFragment();
        getFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        mHandler.sendEmptyMessageDelayed(EVENT_UPDATE_STATS, 1000);
    }
    private  void updateTimes() {
        mDate = new Date();
        long at = mDate.getTime() / 1000;
        long ut = SystemClock.elapsedRealtime() / 1000;

        if (ut == 0) {
            ut = 1;
        }

        if (mUptime == null)
            mUptime = fragment.findPreference("up_time");//如果在创建对象PreferenceSetTestFragment()的时候获取是获取不到的，因为需要在生命周期函数中才填充数据，方可获取。
        mUptime.setSummary(convert(ut));
        if (mCurrentTime == null){
            mCurrentTime = fragment.findPreference("current_time");
        }
        mCurrentTime.setSummary(DateUtils.formatDate(mDate,1));
    }
    private String pad(int n) {
        if (n >= 10) {
            return String.valueOf(n);
        } else {
            return "0" + String.valueOf(n);
        }
    }
    private String convert(long t) {
        int s = (int)(t % 60);
        int m = (int)((t / 60) % 60);
        int h = (int)((t / 3600));

        return h + ":" + pad(m) + ":" + pad(s);
    }
}
