package com.sp.spmultipleapp.activity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sp.spmultipleapp.R;

public class WebViewTestActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_test);

        progressBar= (ProgressBar)findViewById(R.id.progressbar);//进度条

        webView = (WebView) findViewById(R.id.webview);
//        webView.loadUrl("file:///android_asset/web/htmljs.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/pintugame/index.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/fruitNinja/index.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/html5-canvas.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/createjs_h5/src/index.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/html5GameLiveExample/Mario.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/pharse3/game.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/camera_mui_my/index.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/Barcode/index.html");//加载asset文件夹下html
//        webView.loadUrl("file:///android_asset/web/htmljs_new.html");//加载asset文件夹下html
        webView.loadUrl("file:///android_asset/web/phaser2/phaser-video/index-load-video.html");//phaser-video/index-load-video.html
//        webView.loadUrl("file:///android_asset/web/html5/01-html5-video.html");//web/html5/01-html5-video.html
//        webView.loadUrl("http://139.196.35.30:8080/OkHttpTest/apppackage/test.html");//加载url
//        webView.loadUrl(TulingK12Config.TULING_DEFAULT_URL);//加载url

        //使用webview显示html代码
//        webView.loadDataWithBaseURL(null,"<html><head><title> 欢迎您 </title></head>" +
//                "<body><h2>使用webview显示 html代码</h2></body></html>", "text/html" , "utf-8", null);

        webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);

        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

    }
    public static class TulingK12Config{
        private static String APIKEY="a1fa3d36494247aa85888771cc6a6f7c";
        private static String uid="xzx-001";
        public static String TULING_DEFAULT_URL="http://iot-ai.tuling123.com/jump/app/source"+
                "?apiKey="+APIKEY+
                "&uid="+uid+
                "&client=android";
        /**
         * 用于控制i学优（图灵k12）入口显示的隐藏文件.如果该文件存在就显示入口，否则不显示。<br/>
         * .开头的文件即为隐藏文件
         */
        public static String FILE_PATH_IBOTN_TULING_K12 = "/storage/sd-ext/.ibotn_tuling_k12.txt";
    }
    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen","拦截url:"+url);
            if(url.equals("http://www.google.com/")){
                Toast.makeText(WebViewTestActivity.this,"国内不能访问google,拦截该url",Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient=new WebChromeClient(){
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定",null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen","网页标题:"+title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("ansen","是否有上一个页面:"+webView.canGoBack());
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    /**
     * JS调用android的方法
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void  getClient(String str){
        Log.i("ansen","html调用客户端:"+str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源
        webView.destroy();
        webView=null;
    }
}