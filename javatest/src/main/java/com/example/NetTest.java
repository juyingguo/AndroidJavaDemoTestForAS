package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by Administrator on 2018/7/9.
 */

public class NetTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    checkNetworkByAccess();
//                    checkUnusefulNetworkByInetAddress();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    checkUnusefulNetworkByInetAddress();


                }
            }
        }).start();

    }
    public synchronized  static boolean checkUnusefulNetworkByInetAddress() {
        //LogUtil.e(TAG, "Network is off");
        boolean result = false;
        long beginTime  = System.currentTimeMillis();
        final String SERVER_ADDRESS_1 = "www.iflytek.com";
        final String SERVER_ADDRESS_2 = "www.ibotn.com";
        final String SERVER_ADDRESS_3 = "www.baidu.com";
        final String[] SERVER_ADDRESSES = {SERVER_ADDRESS_1, SERVER_ADDRESS_2,SERVER_ADDRESS_3};
        for (String serverAddress : SERVER_ADDRESSES){
            System.out.println(">>>>serverAddress:" + serverAddress);
            try {
                result = InetAddress.getByName(serverAddress).isReachable(5*1000);
            } catch (IOException e) {
                System.out.println(">>>>checkUnusefulNetworkByInetAddress>>>IOException:" + e.getMessage());
                result = false;
            }
           /* if (result){
                break;
            }*/
        }
        System.out.println(">>>>checkUnusefulNetworkByInetAddress>>>consume time:" + (System.currentTimeMillis() - beginTime) + ",result:" + result);

        return result;
    }

    /**
     * 通过web,http/https 方式访问地址，有一个可以访问，就认为网络可用。如所配的地址，都改变，需要重新设置新的地址。<br/>
     * @return true success
     */
    private static boolean checkNetworkByAccess() {
        boolean result = false;
        long beginTime  = System.currentTimeMillis();
        final String SERVER_ADDRESS_1 = "http://www.iflytek.com";
        final String SERVER_ADDRESS_2 = "http://www.ibotn.com";
        final String SERVER_ADDRESS_3 = "https://www.baidu.com";
        //20180712 网站地址
        final String[] SERVER_ADDRESSES = {
                                            "http://www.ibotn.com/",
                                            /*"https://www.xfyun.cn/",
                                            "https://www.baidu.com/",
                                            "https://mail.sina.com.cn/",
                                            "http://www.tsinghua.edu.cn",
                                            "http://www.pku.edu.cn/",
                                            "http://tv.cctv.com/",
                                            "http://www.qq.com/",*/
                                            "https://www.alibaba.com/"

        };
        for (String serverAddress : SERVER_ADDRESSES){
            System.out.println(">>>>serverAddress:" + serverAddress);
            try {
                result = isAddressCanConnected(serverAddress);
            } catch (Exception e) {
//                System.out.println(">>>>checkNetworkByAccess>>>IOException:" + e.getMessage());
                result = false;
            }
            /*if (result){
                break;
            }*/
        }
//        System.out.println(">>>>checkNetworkByAccess>>>consume time:" + (System.currentTimeMillis() - beginTime) + ",result:" + result);

        return result;
    }

    private static boolean isAddressCanConnected(String urlStr) {
        boolean result = false;
        long beginTime  = System.currentTimeMillis();
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10 * 1000);
            conn.setConnectTimeout(10 * 1000);
            int connectTimeout = conn.getConnectTimeout();
            System.out.println(">>>>isAddressCanConnected>>>connectTimeout:" + connectTimeout);
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            System.out.println(">>>>isAddressCanConnected>>>responseCodee:" + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
               result = true;
            } else {
                result = false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            if (conn != null){
                conn.disconnect();
            }
        }
        System.out.println(">>>>isAddressCanConnected>>>consume time:" + (System.currentTimeMillis() - beginTime) + ",result:" + result);
        return result;
    }
}
