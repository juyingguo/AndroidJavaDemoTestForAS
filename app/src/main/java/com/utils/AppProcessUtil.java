package com.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * Date:2021/5/25,11:32
 * author:jy
 * <p>1.判断进程是否存活,传递进程名称，默认进程名称同包名；多进程的需要根据规则应用包名再加上新设定的名称。</p>
 * <p>2.获取最上面的activity。或者使用ActivityLifecycleCallbacks;</p>
 */
public class AppProcessUtil {
    public static String processIsActive(Context context, String strProcess) {
        if (context == null || TextUtils.isEmpty(strProcess)) {
            return null;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && TextUtils.equals(processInfo.processName, strProcess)) {
                return strProcess;
            }
        }
        return null;
    }
    public static String appIsActive(Context context, String[] PackageName) {
        if(context == null || PackageName.length == 0) {
            return null;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService( Context.ACTIVITY_SERVICE );

        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    for (String checkName: PackageName)
                    {
                        if (activeProcess.equals(checkName)) {
                            return checkName;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取最上面的activity。或者使用ActivityLifecycleCallbacks;
     * @param context Context
     * @return activity name
     */
    public static String getTopActivityName(Context context) {
        ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }
}
