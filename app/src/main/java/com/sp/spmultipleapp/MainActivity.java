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

import com.activity.CameraPictureVideoTestActivity;
import com.activity.ImageTestActivity;
import com.activity.KeepAliveServiceActivity;
import com.activity.LinearLayoutTestActivity;
import com.activity.UpgradeInstallTestActivity;
import com.activity.ViewTestActivity;
import com.activity.nettest.NetTestActivity;
import com.activity.taskstack.TaskStackMainActivity;
import com.handler.HandlerTestActivity;
import com.rxjava2test.DoOnSubscribeTest;
import com.sp.spmultipleapp.bean.MessageEvent;
import com.sp.spmultipleapp.gamecourse.GameCourseActivity;
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
import com.utils.DevicePath;
import com.utils.FileUtils;
import com.utils.ThreadUtils;

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
        String externalStorageState = Environment.getExternalStorageState();
        Log.w(TAG,"checkStorage>>externalStorageState:" + externalStorageState);
        if (Environment.MEDIA_MOUNTED.equals(externalStorageState)){
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null){
                String absolutePath = externalStorageDirectory.getAbsolutePath();
                Log.w(TAG,"checkStorage>>absolutePath:" + absolutePath);
            }
        }
        String absolutePath = getCacheDir().getAbsolutePath();
        String absolutePath1 = getExternalCacheDir().getAbsolutePath();
        String absolutePath2 = getFilesDir().getAbsolutePath();
        /////05-11 09:28:17.030 15256-15256/? W/MainActivity: checkStorage>>absolutePath:/data/data/com.sp.spmultipleapp/cache,absolutePath1:/storage/sdcard/Android/data/com.sp.spmultipleapp/cache
        Log.w(TAG,"checkStorage>>absolutePath:" + absolutePath + ",absolutePath1:" + absolutePath1 + ",absolutePath2:" + absolutePath2);
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
            ,R.id.btn_linearlayout_test,R.id.btn_view_test,R.id.btn_install_upgrade_imitate
            ,R.id.btn_keep_alive_service_test
            ,R.id.btn_activity_task_stack
            ,R.id.btn_activity_image_test
            ,R.id.btn_activity_net_test
            ,R.id.btn_activity_camera_test
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
            startActivity(new Intent(mContext, KeepAliveServiceActivity.class));
        }else if (view.getId() == R.id.btn_activity_task_stack){
            startActivity(new Intent(mContext, TaskStackMainActivity.class));
        }else if (view.getId() == R.id.btn_activity_image_test){
            startActivity(new Intent(mContext, ImageTestActivity.class));
        }else if (view.getId() == R.id.btn_activity_net_test){
            startActivity(new Intent(mContext, NetTestActivity.class));
        }else if (view.getId() == R.id.btn_activity_camera_test){
            startActivity(new Intent(mContext, CameraPictureVideoTestActivity.class));
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
    }
}
