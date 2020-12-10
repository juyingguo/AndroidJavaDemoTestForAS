package com.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.sp.spmultipleapp.application.CoreApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WifiUtils {
    private static String TAG = "WifiUtils";
    public static boolean createAp(boolean isOpen) {
        WifiManager mWifiManager = null;
        StringBuffer sb = new StringBuffer();
        try {
            mWifiManager = (WifiManager) CoreApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(false);
            }
            sb.append(1);
            WifiConfiguration netConfig = new WifiConfiguration();
            netConfig.SSID = "xiaomeng";
            netConfig.preSharedKey = "11111111";
            Log.d(TAG, "WifiPresenter：createAp----->netConfig.SSID:"
                    + netConfig.SSID + ",netConfig.preSharedKey:" + netConfig.preSharedKey + ",isOpen=" + isOpen);
            netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            sb.append(2);
            if (isOpen) {
                netConfig.allowedKeyManagement.set(4);
                sb.append(3);
            } else {
                netConfig.allowedKeyManagement.set(4);
                sb.append(4);
            }
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            sb.append(5);

            Method method = mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            sb.append(9);
//            return (boolean) method.invoke(mWifiManager, netConfig, true);
            return (boolean) method.invoke(mWifiManager, null, true);


        } catch (NoSuchMethodException e) {
            sb.append(10 + (e.getMessage()));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            sb.append(11 + (e.getMessage()));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            sb.append(12 + (e.getMessage()));
            e.printStackTrace();
        }
        Log.d(TAG,"createAp,StringBuffer:" + sb.toString());

        return false;
    }
}
