package com.net;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import okhttp3.Call;
import okhttp3.Request;

import java.io.File;

public class OkHttpDownloadTest {
    public static void main(String[] args) {
//        String url = "http://xunleib.zuida360.com/1805/娘亲舅大44.mp4";
        String url = "http://dl108.80s.com.co:999/1804/[%C3%A5%C2%A8%C2%98%C3%A4%C2%BA%C2%B2%C3%A8%C2%88%C2%85%C3%A5%C2%A4%C2%A7]%C3%A7%C2%AC%C2%AC07%C3%A9%C2%9B%C2%86/[%C3%A5%C2%A8%C2%98%C3%A4%C2%BA%C2%B2%C3%A8%C2%88%C2%85%C3%A5%C2%A4%C2%A7]%C3%A7%C2%AC%C2%AC07%C3%A9%C2%9B%C2%86_bd.mp4";
        String fileName = "娘亲舅大第7集-zhuan.mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack("E:\\迅雷下载\\娘亲舅大", fileName)//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        System.out.println("onBefore");
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        System.out.println("inProgress:" + (progress/total)*100);
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        System.out.println("onError:" + e);
                    }

                    @Override
                    public void onResponse(File file, int i) {
                        System.out.println(file.getAbsolutePath());
                    }
                });
    }
}
