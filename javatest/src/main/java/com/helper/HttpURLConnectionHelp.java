package com.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Date:2021/7/6,14:25
 * author:jy
 * <p>HttpURLConnection helper class</p>
 */
public class HttpURLConnectionHelp {
    private static String TAG = "HttpURLConnectionHelp";
    public static String encodeStr(String msg) {
        try {
            return URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get 请求
     * @param urlStr
     * @return 获取到结果
     */
    public static String doGet(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(3 * 1000);
            conn.setConnectTimeout(3 * 1000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];
                while ((len = is.read(buf)) != -1)
                {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else {
                System.out.println(TAG + ",doGet responseCode: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try {
                if (baos != null)  baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            if (conn != null)  conn.disconnect();
        }

    }
}
