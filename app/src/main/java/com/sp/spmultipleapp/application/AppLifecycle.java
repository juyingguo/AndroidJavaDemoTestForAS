package com.sp.spmultipleapp.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.utils.LogUtil;

import java.util.LinkedList;

/**
 * <p>1.</p>
 */
public class AppLifecycle implements Application.ActivityLifecycleCallbacks {

    private boolean isResume = false;
    private String topActName = "";
    private static final String TAG = AppLifecycle.class.getSimpleName();
    private LinkedList<Activity> stack = new LinkedList<Activity>();
    public String getTopActName() {
        return topActName;
    }

    public void setTopActName(String topActName) {
        this.topActName = topActName;
    }

    public boolean isResume() {
        return isResume;
    }

    public void setResume(boolean resume) {
        isResume = resume;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.i(TAG,"onActivityCreated.");
        if (!stack.contains(activity)) {
            stack.add(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtil.i(TAG,"onActivityResumed :"+activity.getClass().getSimpleName());
        isResume = true;
        topActName = activity.getClass().getName();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.i(TAG,"onActivityPaused :"+activity.getClass().getSimpleName());
        isResume = false;
        topActName = activity.getClass().getName();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (stack.contains(activity)) {
            stack.remove(activity);
        }
    }

    public void registerSelf(Context context) {
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(this);
    }
}
