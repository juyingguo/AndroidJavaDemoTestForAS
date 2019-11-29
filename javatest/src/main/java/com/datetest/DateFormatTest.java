package com.datetest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2018/6/21.
 */

public class DateFormatTest {
    static String TAG = "DateFormatTest";
    public static void main(String[] args) {
        long time = 1572568309740042L;
//        formatTime(1570840772487L);
        formatTime(time);
    }

    private static void formatTime(Long time) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(time);

        String format1 = format.format(date);

        System.out.println(format1);

    }


}
