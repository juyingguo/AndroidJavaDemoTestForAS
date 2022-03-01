package com.sp.spmultipleapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;

import com.sp.spmultipleapp.fragment.PreferenceSetTestFragment;

public class PreferenceTestActivity extends AppCompatActivity {
    public static Preference mUptime;
    private Handler mHandler;
    private static final int EVENT_UPDATE_STATS = 500;
    private  class MyHandler extends Handler {

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
        PreferenceSetTestFragment fragment = new PreferenceSetTestFragment();
        getFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        mUptime = fragment.findPreference("up_time");
        mHandler.sendEmptyMessageDelayed(EVENT_UPDATE_STATS, 1000);
    }
    private  void updateTimes() {
        long at = SystemClock.uptimeMillis() / 1000;
        long ut = SystemClock.elapsedRealtime() / 1000;

        if (ut == 0) {
            ut = 1;
        }

        mUptime.setSummary(convert(ut));
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
