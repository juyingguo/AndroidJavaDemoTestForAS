package com.sp.spmultipleapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sp.spmultipleapp.activity.AnimationTestActivity;
import com.sp.spmultipleapp.activity.AppDetectTestActivity;
import com.sp.spmultipleapp.activity.AudioModuleActivity;
import com.sp.spmultipleapp.activity.CameraPictureVideoTestActivity;
import com.sp.spmultipleapp.activity.DialogCustomTestActivity;
import com.sp.spmultipleapp.activity.FileModuleActivity;
import com.sp.spmultipleapp.activity.ImageTestActivity;
import com.sp.spmultipleapp.activity.KeepAliveTestActivity;
import com.sp.spmultipleapp.activity.LinearLayoutTestActivity;
import com.sp.spmultipleapp.activity.ProximityScreenOffMockInCallTest;
import com.sp.spmultipleapp.activity.ScreenOffAdminActivity;
import com.sp.spmultipleapp.activity.ThreadTestActivity;
import com.sp.spmultipleapp.activity.UpgradeInstallTestActivity;
import com.sp.spmultipleapp.activity.ViewTestActivity;
import com.sp.spmultipleapp.activity.WebViewListTestActivity;
import com.sp.spmultipleapp.activity.WebViewTestActivity;
import com.sp.spmultipleapp.activity.WifiModuleTestActivity;
import com.sp.spmultipleapp.activity.aidltest.AidlTestActivity;
import com.sp.spmultipleapp.activity.nettest.NetTestActivity;
import com.sp.spmultipleapp.activity.taskstack.TaskStackMainActivity;
import com.sp.spmultipleapp.datastruct.SparseArrayActivityTest;
import com.sp.spmultipleapp.eventbustest.EventBusMainActivity;
import com.sp.spmultipleapp.handler.HandlerTestActivity;
import com.sp.spmultipleapp.rxjava2test.DoOnSubscribeTest;
import com.sp.spmultipleapp.bean.MessageEvent;
import com.sp.spmultipleapp.gamecourse.GameCourseActivity;
import com.sp.spmultipleapp.sensor.SensorTestActivity;
import com.sp.spmultipleapp.service.TestNewBuildService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.sp.spmultipleapp.showbigimage.ShowBigImageTestActivity;
import com.sp.spmultipleapp.showimage.ShowMultiImageActivity;
import com.utils.DevicePath;
import com.utils.FileUtils;
import com.utils.ThreadUtils;
import com.utils.WifiUtils;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private TextView tv_time;
    Unbinder butterKnifeBind = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate");

        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        butterKnifeBind = ButterKnife.bind(this);

        mContext = this;
        tv_time = (TextView) findViewById(R.id.tv_time);

        showTime();

//        testUri();

//        testYu();
//        testStringformat();

//        testLeftMove();
//        testPrintSlot();
//        useIbotnSp();
//        useIbotnlauncherSp2();

//        testRandom();
//
//        timerTest();

//        multipleThreadEditSp();

//        testBoolean();

//        testFile();
//        testFile2();
//        regTest01();


//        startService(new Intent(this, MyIntentService.class));
//        startService(new Intent(this, TestBackgroundService.class));


        DoOnSubscribeTest.doSomeWork();

//        FileTest.testFile();

        NetDetectHelper.detectNet(this);

//        GroupDanceTest.testDanceFileAndVerifyTimePoint();

//        dealSettingIme();

//        dealGramPath();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
//        startService(new Intent(this,SdcardReadWriteDealService.class));

//        startService(new Intent(this, TestServiceFromActivity.class));

        checkStorage();

        DevicePath.getStoragePaths(this);

        TestNewBuildService.getInstance();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    private void checkStorage(){
        Log.w(TAG,"checkStorage>>");
        try {
            String externalStorageState = Environment.getExternalStorageState();
            Log.w(TAG,"checkStorage>>externalStorageState:" + externalStorageState);
            if (Environment.MEDIA_MOUNTED.equals(externalStorageState)){
                File externalStorageDirectoryFile = Environment.getExternalStorageDirectory();
                if (externalStorageDirectoryFile != null){
                    String externalStorageDirectory = externalStorageDirectoryFile.getAbsolutePath();
                    Log.w(TAG,"checkStorage>>externalStorageDirectory:" + externalStorageDirectory);
                }
            }
            String getCacheDir = getCacheDir().getAbsolutePath();
            String getExternalCacheDir = getExternalCacheDir().getAbsolutePath();
            String getFilesDir = getFilesDir().getAbsolutePath();
            String getExternalFilesDirForTest = getExternalFilesDir("test").getAbsolutePath();
            String getExternalFilesDirForhMusic = getExternalFilesDir(android.os.Environment.DIRECTORY_MUSIC).getAbsolutePath();
            Log.d(TAG,"checkStorage>>getCacheDir:" + getCacheDir + "\n getExternalCacheDir:" + getExternalCacheDir
                    + "\n getFilesDir:" + getFilesDir + "\n getExternalFilesDirForTest:" + getExternalFilesDirForTest
                    + "\n getExternalFilesDirForhMusic:" + getExternalFilesDirForhMusic
            );
            //打印记录：：
            /*
            checkStorage>>externalStorageDirectory:/storage/sdcard
                #查看文件系统ls -l,/storage/sdcard与 sdcard建立了链接即： sdcard -> /storage/sdcard
            checkStorage>>getCacheDir:/data/data/com.sp.spmultipleapp/cache
            getExternalCacheDir:/storage/sdcard/Android/data/com.sp.spmultipleapp/cache
            getFilesDir:/data/data/com.sp.spmultipleapp/files
            getExternalFilesDirForTest:/storage/sdcard/Android/data/com.sp.spmultipleapp/files/test
            getExternalFilesDirForhMusic:/storage/sdcard/Android/data/com.sp.spmultipleapp/files/Music

             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String grmPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/grmPath";
    private String grmPathBak = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/grmPath_bak";

    Handler handlerBack = null;
    HandlerThread handlerThread = null;
    AtomicInteger atomicInteger = new AtomicInteger(0);

    private void dealGramPath() {

        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();

        handlerBack = new Handler(handlerThread.getLooper());
        handlerBack.post(new Runnable() {
            @Override
            public void run() {
                Log.w(TAG,"dealGramPath>>第:" + atomicInteger.incrementAndGet() + "次执行。" );

                boolean isDdir = FileUtils.isDir(grmPath);
               Log.w(TAG,"dealGramPath>>isDdir:" + isDdir);

               ///copy grmPath to grmPathBak
                if (isDdir){
                    boolean copyDir = FileUtils.copyDir(grmPath, grmPathBak);
                    Log.e(TAG,"dealGramPath>>copyDir to grmPathBak:" + copyDir);
                    if (copyDir){
                            boolean deleteDir = FileUtils.deleteDir(grmPath);
                            Log.w(TAG,"dealGramPath>>deleteDir grmPath :" + deleteDir);
                            if (deleteDir){
                                copyDir = FileUtils.copyDir(grmPathBak,grmPath);
                                Log.w(TAG,"dealGramPath>>grmPathBak to copyDir:" + copyDir);
                                if (copyDir){
//                                    SystemClock.sleep(3000);

                                    if (atomicInteger.get() <= 1000){
                                        handlerBack.post(this);

                                    }
                                }

                            }
                    }
                }

            }
        });

    }

    private void dealSettingIme() {
        String defaultIme = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);

        System.out.println(TAG + ">>dealSettingIme(),defaultIme:" + defaultIme);

        Settings.Secure.putString(getContentResolver(),Settings.Secure.DEFAULT_INPUT_METHOD,"com.google.android.inputmethod.pinyin/.PinyinIME");

        defaultIme = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);

        System.out.println(TAG + ">>dealSettingIme(),defaultIme:" + defaultIme);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){

//        Toast.makeText(mContext,event.getMessage(),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getBaseContext(), Environment.getExternalStorageDirectory().getAbsolutePath(),Toast.LENGTH_LONG).show();
    }


    @OnClick({R.id.tv_file_explore,R.id.tv_touch,R.id.tv_send_broadcast_default,
            R.id.tv_send_broadcast_pendingintent,R.id.tv_test_video,R.id.btn_game_course
            ,R.id.btn_linearlayout_test
            ,R.id.btn_view_test
            ,R.id.btn_handler_test
            ,R.id.btn_install_upgrade_imitate
            ,R.id.btn_keep_alive_service_test
            ,R.id.btn_activity_task_stack
            ,R.id.btn_activity_image_test
            ,R.id.btn_activity_net_test
            ,R.id.btn_activity_camera_test
            ,R.id.btn_app_detect_test
            ,R.id.btn_start_wifi_ap_test
            ,R.id.btn_big_image_test
            ,R.id.btn_show_multi_image_test
            ,R.id.btn_sensor_test
            ,R.id.btn_screen_off_admin
            ,R.id.btn_wifi_cast_display
            ,R.id.btn_wifi_cast_display_off
            ,R.id.btn_file_module
            ,R.id.btn_webview_test
            ,R.id.btn_dialog_popupwindow_test
            ,R.id.btn_aidl_test
            ,R.id.btn_audio_test
            ,R.id.btn_android_thread_test
            ,R.id.btn_event_bus_test
            ,R.id.btn_animation_test
            ,R.id.btn_preference_test
            ,R.id.btn_sparse_array_test
            ,R.id.btn_proximity_screen_fff_mock_incall_test
                })
    public void clickView(View view){
        if (view.getId() == R.id.tv_file_explore){
            startActivity(new Intent(mContext,FileExploreActivity.class));
        }else if (view.getId() == R.id.tv_touch){
            startActivity(new Intent(mContext,TouchViewTestActivity.class));
        }else if(view.getId() == R.id.tv_send_broadcast_default){
            sendBroadcast(new Intent(MyReceiver.ACTION_TEST_ABC));
        }else if(view.getId() == R.id.tv_send_broadcast_pendingintent){
            Intent intent = new Intent(this,MyReceiver.class);
            intent.setAction(MyReceiver.ACTION_TEST_ABC);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent , PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }


        }else if (view.getId() == R.id.tv_test_video){
            startActivity(new Intent(mContext,MoboPlayerTestActivity.class));
        }else if (view.getId() == R.id.btn_game_course){
            startActivity(new Intent(mContext, GameCourseActivity.class));
        }else if (view.getId() == R.id.btn_linearlayout_test){
            startActivity(new Intent(mContext, LinearLayoutTestActivity.class));
        }else if (view.getId() == R.id.btn_view_test){
            startActivity(new Intent(mContext, ViewTestActivity.class));
        }else if (view.getId() == R.id.btn_handler_test){
            startActivity(new Intent(mContext, HandlerTestActivity.class));
        }else if (view.getId() == R.id.btn_install_upgrade_imitate){
            startActivity(new Intent(mContext, UpgradeInstallTestActivity.class));
        }else if (view.getId() == R.id.btn_keep_alive_service_test){
            startActivity(new Intent(mContext, KeepAliveTestActivity.class));

        }else if (view.getId() == R.id.btn_activity_task_stack){
            startActivity(new Intent(mContext, TaskStackMainActivity.class));
        }else if (view.getId() == R.id.btn_activity_image_test){
            startActivity(new Intent(mContext, ImageTestActivity.class));
        }else if (view.getId() == R.id.btn_activity_net_test){
            startActivity(new Intent(mContext, NetTestActivity.class));
        }else if (view.getId() == R.id.btn_activity_camera_test){
            startActivity(new Intent(mContext, CameraPictureVideoTestActivity.class));
        }else if (view.getId() == R.id.btn_app_detect_test){
            startActivity(new Intent(mContext, AppDetectTestActivity.class));
        }else if (view.getId() == R.id.btn_start_wifi_ap_test){
            WifiUtils.createAp(true);
        }else if (view.getId() == R.id.btn_big_image_test){
            startActivity(new Intent(mContext, ShowBigImageTestActivity.class));
        }else if (view.getId() == R.id.btn_show_multi_image_test){
            startActivity(new Intent(mContext, ShowMultiImageActivity.class));
        }else if (view.getId() == R.id.btn_sensor_test){
            startActivity(new Intent(mContext, SensorTestActivity.class));
        }else if (view.getId() == R.id.btn_screen_off_admin){
            startActivity(new Intent(mContext, ScreenOffAdminActivity.class));
        }else if (view.getId() == R.id.btn_wifi_cast_display){
            /*try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings","com.android.settings.Settings$WifiDisplaySettingsActivity"));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            String EXTRA_IS_VOICE_START = "is_voice_start";
             try {
                Intent intent = new Intent("android.settings.WIFI_DISPLAY_SETTINGS");
                intent.putExtra(EXTRA_IS_VOICE_START,true);
//                intent.setComponent(new ComponentName("com.android.settings","com.android.settings.Settings$WifiDisplaySettingsActivity"));
                 intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (view.getId() == R.id.btn_wifi_cast_display_off){
            try {
                //need android.permission.WRITE_SECURE_SETTINGS;for system app
                boolean wifiDisplayOnResult = Settings.Global.putInt(getContentResolver(), "wifi_display_on"/*Settings.Global.WIFI_DISPLAY_ON*/, 0);/* Settings.Global.WIFI_DISPLAY_ON is hide*/
                Log.d(TAG,"wifiDisplayOnResult:" + wifiDisplayOnResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (view.getId() == R.id.btn_file_module){
            startActivity(new Intent(mContext, FileModuleActivity.class));
        }else if (view.getId() == R.id.btn_wifi_module_test){
            startActivity(new Intent(mContext, WifiModuleTestActivity.class));
        }else if (view.getId() == R.id.btn_webview_test){
            startActivity(new Intent(mContext, WebViewListTestActivity.class));
        }else if (view.getId() == R.id.btn_dialog_popupwindow_test){
            startActivity(new Intent(mContext, DialogCustomTestActivity.class));
        }else if (view.getId() == R.id.btn_aidl_test){
            startActivity(new Intent(mContext, AidlTestActivity.class));
        }else if (view.getId() == R.id.btn_audio_test){
            startActivity(new Intent(mContext, AudioModuleActivity.class));
        }else if (view.getId() == R.id.btn_android_thread_test){
            startActivity(new Intent(mContext, ThreadTestActivity.class));
        }else if (view.getId() == R.id.btn_event_bus_test){
            startActivity(new Intent(mContext, EventBusMainActivity.class));
        }else if (view.getId() == R.id.btn_animation_test){
            startActivity(new Intent(mContext, AnimationTestActivity.class));
        }else if (view.getId() == R.id.btn_preference_test){
            startActivity(new Intent(mContext, PreferenceTestActivity.class));
        }else if (view.getId() == R.id.btn_sparse_array_test){
            startActivity(new Intent(mContext, SparseArrayActivityTest.class));
        }else if (view.getId() == R.id.btn_proximity_screen_fff_mock_incall_test){
            startActivity(new Intent(mContext, ProximityScreenOffMockInCallTest.class));
        }
    }

    private void showTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    while (true){
                        SystemClock.sleep(100);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss.SSS z");
                                String time = format.format(new Date());
                                tv_time.setText(time);
                            }
                        });
                    }
            }
        }).start();
    }

    public void testUri(){
        System.out.println(">>testUri>>Uri.EMPTY:" + Uri.EMPTY);
        System.out.println(">>testUri>>Uri.EMPTY:" + Uri.EMPTY.equals(null));
        System.out.println(">>testUri>>Uri.EMPTY:" + Uri.EMPTY.equals(""));
    }
    private void testYu() {
        for (int i = 1;i <= 7;i ++){

            System.out.println("testYu:" + (i+ 5)%7);

        }
    }

    private void testStringformat() {
        long time = System.currentTimeMillis();
        System.out.println("testStringformat>>time:" + time);
        String test = String.format("REM_%s_%08X", "abcd", time);
        System.out.println("testStringformat>>test:" + test);
    }

    private void testLeftMove() {
        for (int i = 0;i < 7;i ++){

            System.out.println("testLeftMove:" + (1<<i));

        }
    }

    private void testPrintSlot() {
        for (int i = 0;i <= 100;i ++){

            System.out.println("|测试" + i + "(" + i + ")");

        }
    }

    private void regTest01() {

        String str01 = RegExpUtils.filterSpecicalChars(",.，。小蓓蕾组合 - 种太阳(1)");
        Log.w(TAG, "regTest01>>str01:" + str01 );
//        boolean res = RegExpUtils.isNumber("110.02");
//        Log.w(TAG, "regTest01>>res:" + res );
//        res = RegExpUtils.isNumber("-110");
//        Log.w(TAG, "regTest01>>res:" + res );

        str01 = RegExpUtils.filterSpace("10 a ");
        Log.w(TAG, "regTest01>>str01:" + str01  + ",length:" + str01.length());
        str01 = RegExpUtils.filterStartNumber("0010ha8test9");
        Log.w(TAG, "regTest01>>str01:" + str01  + ",length:" + str01.length());
        Log.w(TAG, "regTest01>>containsAllChars:"  + StringUtils.containsAllChars(" 天气abc","天气ab  dc "));
    }

    /**
     * 清除掉所有特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
// 清除掉所有特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    private void testFile() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                Log.w(TAG, "testFile>>start:" );

                String path  =  File.separator+"storage"+ File.separator+"sd-ext"+
                        File.separator+"STUDY"+ File.separator;
                File file = new File(path);
                if (file.isDirectory()){
                    File[] list = file.listFiles();
                    if (list != null){
                        Log.w(TAG, "testFile>>list:" + list.length);
                    }
                }

                Log.w(TAG, "testFile>>end:" );

            }
        });
    }
    private void testFile2() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                Log.w(TAG, "testFile2>>start:" );

                String path = File.separator+"storage"+File.separator+"sd-ext"+File.separator+"STUDY"+File.separator+"VIDEO";
                File file = new File(path);
                File[] list = file.listFiles();
                if (list != null){
                    Log.w(TAG, "testFile2>>list:" + list.length);

                }

                Log.w(TAG, "testFile2>>end:" );

            }
        });
    }

    private void testBoolean() {
        String s = String.valueOf(true);
        Log.w(TAG, "tempCount:" );
    }

    private void multipleThreadEditSp() {
        for (int i = 0;i <= 100;i ++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3);
//                    synchronized (MainActivity.class){
                       /* int count = SpUtils.getInt(mContext, "count");
                        Log.w(TAG, "count:" + count);
                        count ++;*/
                        SpUtils.putInt(mContext,"count",5);
                        int tempCount = SpUtils.getInt(mContext, "count");
                        Log.w(TAG, "tempCount:" + tempCount);

//                    }

                }
            }).start();
        }
        for (int i = 0;i <= 100;i ++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2);
//                    synchronized (MainActivity.class){
                       /* int count = SpUtils.getInt(mContext, "count");
                        Log.w(TAG, "count:" + count);
                        count ++;*/
                        SpUtils.putInt(mContext,"count",10);
                        int tempCount = SpUtils.getInt(mContext, "count");
                        Log.w(TAG, "tempCount2:" + tempCount);

//                    }

                }
            }).start();
        }
    }

    private void testRandom() {
        String tmp = "ramdom" + Math.abs(new Random().nextInt());
//        Log.e(TAG, "testRandom>>tmp:" + tmp + ",2<<30:" + (2<<15) );

        new Thread(new Runnable() {
            @Override
            public void run() {

                String tmp = "ramdom" + Math.abs(new Random().nextInt());
                for (int i = 0;i <= 100;i ++){

                    double random = Math.random() * 10;
                    int randomIndex = (int) random;
                    Log.w(TAG, ">>testRandom>>random>>>:" + random);
                    Log.w(TAG, ">>testRandom>>randomIndex:" + randomIndex);
                }
            }
        }).start();
    }

    /** sp key auto Recharge; 对应值，true on，false,off */
    public static final String SP_KEY_TOGGLE_AUTO_RECHARGE = "SP_KEY_TOGGLE_AUTO_RECHARGE";
    private void useIbotnSp() {
        Context con;
        try {
            con = createPackageContext("com.ibotn.ibotnlauncher", 0);
            if (con != null){
                //原本ibotn.xml模式MODE_WORLD_READABLE，-rw-rw-r--
                SharedPreferences pref=con.getSharedPreferences("ibotn", Context.MODE_WORLD_WRITEABLE);

                ////读取值
                boolean recharge = pref.getBoolean(SP_KEY_TOGGLE_AUTO_RECHARGE,false);
                Log.e(TAG, "useIbotnlauncherSp>>recharge:" + recharge);

                if (recharge){
                    //改变值;是无法实际写入的
                    pref.edit().putBoolean(SP_KEY_TOGGLE_AUTO_RECHARGE,false).commit();
                    recharge = pref.getBoolean(SP_KEY_TOGGLE_AUTO_RECHARGE,false);
                    Log.e(TAG, "useIbotnlauncherSp>>recharge:" + recharge);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.toString());
        }

    }
    private void useIbotnlauncherSp2() {
        Context con;
        try {
            con = createPackageContext("com.ibotn.ibotnlauncher", 0);
            if (con != null){
                SharedPreferences pref=con.getSharedPreferences("SerialPort_Pref", Context.MODE_PRIVATE);
                // //原本SerialPort_Pref.xml模式MODE_PRIVATE，-rw-rw----
                int BAUDRATE = pref.getInt("BAUDRATE",0);
                Log.e(TAG, "useIbotnlauncherSp>>BAUDRATE:" + BAUDRATE);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.toString());
        }
    }
    private void timerTest(){
        tempNetCheckTimer.schedule(tempNetCheckTask,1000);
    }
    /////////////
    Timer tempNetCheckTimer = new Timer();
    TimerTask tempNetCheckTask = new TimerTask() {

        @Override
        public void run() {
            SystemClock.sleep(1000);
            Log.w(TAG, "run>>:" + mContext);
            SystemClock.sleep(3000);
            Log.w(TAG, "run>>:" + mContext);
            SystemClock.sleep(3000);
            Log.w(TAG, "run>>:" + mContext);

        }
    };
    ///////////
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        if (tempNetCheckTimer != null){
            tempNetCheckTimer.cancel();
        }
        mContext = null;
        if (butterKnifeBind != null){
            butterKnifeBind.unbind();

        }
        EventBus.getDefault().unregister(this);
        Log.e(TAG, "onDestroy>>:" );

        if (handlerBack != null){
            handlerBack.removeCallbacksAndMessages(null);
        }
//        stopService(new Intent(this,SdcardReadWriteDealService.class) );

        Log.d(TAG, "onDestroy>>to start Foreground service : StepService" );
//        startService(new Intent(getApplicationContext(), StepService.class));
    }
}
