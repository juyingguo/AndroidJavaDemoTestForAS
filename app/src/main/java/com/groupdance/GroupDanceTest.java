package com.groupdance;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.utils.LogUtil;

public class GroupDanceTest {
    static  String TAG = GroupDanceTest.class.getSimpleName();

    //////////////////
    public static final String DEMO_BASE_URL = "/storage/sd-ext/DEMO/";
    public static final String DEMO2_DANCE_NAME = "If you are happy";
    public static final String DEMO1_DANCE_NAME = "music1";
    public static final String DEMO2_DANCE_NAME_SCRIPT = DEMO2_DANCE_NAME + ".xml";
    public static final String DEMO1_DANCE_NAME_SCRIPT = DEMO1_DANCE_NAME + ".xml";
    public static final String DEMO2_DANCE_FILE_NAME = DEMO2_DANCE_NAME + ".mp3";
    public static final String DEMO1_DANCE_FILE_NAME = DEMO1_DANCE_NAME + ".mp3";
    public static final String DEMO2_URL = DEMO_BASE_URL + DEMO2_DANCE_NAME;
    public static final String DEMO1_URL = DEMO_BASE_URL + DEMO1_DANCE_NAME;
    //////////////////


    static Vector<ActionBean> vector = new Vector<>();
    public static void testDanceFileAndVerifyTimePoint(){
        LogUtil.w(TAG,">>testDanceFileAndVerifyTimePoint>>");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String filePath = DEMO2_URL + ".xml";
                File file = new File(filePath);
                if (!file.exists()) {
                    LogUtil.e(TAG,filePath + ">> not exists");
                }
                vector = parseActionXml(file);
                LogUtil.w(TAG,">>vector:" + vector);
                if (vector != null){
                    LogUtil.w(TAG,">>vector:" + vector.size());
                    loopThread();
                }
            }
        }).start();


    }

    private static boolean isPlaying = true;
    private static int count = 0;
    public static  void  loopThread(){
        LogUtil.d(TAG,"loopThread>>isPlaying:" + isPlaying + ",count:" + count);
        new Thread(new Runnable() {
            @Override
            public void run() {

                isPlaying = true;
                count = 0;
                LogUtil.d(TAG,this + ">>isPlaying:" + isPlaying + ",count:" + count);
                while (isPlaying) {

                    actionProcess(count);
                    try {
                        Thread.sleep(100);
                        count += 100;
                    } catch (Exception e) {

                    }

                }
            }
        }).start();
    }


    private static Vector<ActionBean> parseActionXml(File file) {
        Vector<ActionBean> mVector = new Vector<>();
        ActionBean actionBean = new ActionBean();

        InputStream is = null;
        try {
            is = new FileInputStream(file);

            XmlPullParser xpp = Xml.newPullParser();
            // 设置输入流 并指明编码方式
            xpp.setInput(is, "UTF-8");
            // 产生第一个事件
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xpp.getName();
                switch (eventType) {
                    // 判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        if (mVector == null)
                            mVector = new Vector<>(); // 初始化ActionBean集合
                        break;
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        if (nodeName.equals("dance")) { // 判断开始标签元素是否是dance
                            if (actionBean == null)
                                actionBean = new ActionBean();
                        } else if (nodeName.equals("name")) {
                            // 得到name标签的属性值，并设置actionBean的name
                            actionBean.setName(xpp.nextText());
                        } else if (nodeName.equals("time")) { // 判断开始标签元素是否是time
                            // 得到time标签的属性值，并设置actionBean的time
                            actionBean.setTimeStamp(Long.parseLong(xpp.nextText()));
                        } else if (nodeName.equals("action")) { // 判断开始标签元素是否是action
                            // 得到action标签的属性值，并设置actionBean的action
                            actionBean.setAction(Integer.parseInt(xpp.nextText()));
                        }
                        break;

                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:
                        if (nodeName.equals("dance")) { // 判断结束标签元素是否是dance
                            mVector.add(actionBean); // 将actionBean添加到actionBean集合
                            actionBean = null;
                        }
                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xpp.next();
            }
            if (is != null) {
                is.close();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mVector;

    }
    private static void actionProcess(int progress) {

        if (vector == null){
            return;
        }

        for (int i = 0; i < vector.size(); i++) {
            if (progress == vector.get(i).getTimeStamp()) {
                LogUtil.w(TAG,"actionProcces>>progress = "+progress + ",position:" +(i + 1 ));
//                doAction(i);
                break;
            }
        }
    }
}
