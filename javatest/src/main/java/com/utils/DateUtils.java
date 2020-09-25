package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jy on 2017/2/6 ;15:02.<br/>
 *
 * @description:
 */
public class DateUtils {
    private final static String TAG = "DateUtils";
    /**
     *
     * @param date Date
     * @param formatType  if formatType is 1 ："yyyyMMddHHmmss" ;2: "MM-dd HH:mm:ss";3:"yyyyMMdd";4:yyyyMMddHHmmssSSS
     * @return
     */
    public static String formatDate(Date date,int formatType){
        Date date1 = new Date();
        SimpleDateFormat dateFormat = null;
        if (formatType == 1)
        {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        }else if (formatType == 2)
        {
            dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        }else if (formatType == 3)
        {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        }else if (formatType == 4)
        {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        }else if (formatType == 5)
        {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSZ");
        }
        if (dateFormat == null)
        {
            return null;
        }
        return  dateFormat.format(date);
    }
    /**
     *
     * @param dateStr dateStr yyyyMMdd 如:20191010
     * @param formatType  if formatType is 1 ："yyyyMMddHHmmss" ;2: "MM-dd HH:mm:ss";3:"yyyyMMdd"
     * @return
     */
    public static String formatDate(String dateStr,int formatType){
        SimpleDateFormat dateFormat = null;
        Date date= null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            date = sdf.parse(dateStr);
            if (formatType == 1)
            {
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            }else if (formatType == 2)
            {
                dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            }else if (formatType == 3)
            {
                dateFormat = new SimpleDateFormat("yyyyMMdd");
            }else if (formatType == 4)
            {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }

            if (dateFormat == null)
            {
                return null;
            }
            return  dateFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 包含8位日期。
     * @param time
     */
    private static void formatCustomDate(String time) {
        /*//20191218
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        *//**
         * {@link GregorianCalendar#GregorianCalendar(int, int, int)}
         *
         *//*
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(2019,12,18);

        Date date = DateFormat.parse(time);
        date.getMonth()
        String format1 = format.format(date);

        System.out.println(format1);*/


    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smallDate 较小的时间,日期格式为"yyyyMMdd"
     * @param bigDate  较大的时间,日期格式为"yyyyMMdd"
     * @return 相差天数
     */
    public static int daysBetween(String smallDate,String bigDate){
        long betweenDays = -1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smallDate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bigDate));
            long time2 = cal.getTimeInMillis();
            betweenDays =(time2-time1)/(1000*3600*24);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        return Integer.parseInt(String.valueOf(betweenDays));
    }
    /**
     * 该方式计算完全有误。
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     */
    public static int getMonthSpace(String date1, String date2){

        int result = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

//            return result == 0 ? 1 : Math.abs(result);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }
    public static class DayCompare{
        private int year;
        private int month;
        private int day;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "DayCompare{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    '}';
        }
    }
    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare dayComparePrecise(Date fromDate,Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        DayCompare dayCompare = new DayCompare();
        dayCompare.setYear(year);
        dayCompare.setMonth(month);
        dayCompare.setDay(day);
        return dayCompare;
    }
    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     * @param dateStr1 日期格式为"yyyyMMdd"
     * @param dateStr2 日期格式为"yyyyMMdd"
     * @return DayCompare DayCompare
     */
    public static DayCompare dayComparePrecise2(String dateStr1,String dateStr2){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date d1= null;
                d1 = sdf.parse(dateStr1);
            Date d2=sdf.parse(dateStr2);
            Calendar  from  =  Calendar.getInstance();
            from.setTime(d1);
            Calendar  to  =  Calendar.getInstance();
            to.setTime(d2);
            int fromYear = from.get(Calendar.YEAR);
            int fromMonth = from.get(Calendar.MONTH);
            int fromDay = from.get(Calendar.DAY_OF_MONTH);
            int toYear = to.get(Calendar.YEAR);
            int toMonth = to.get(Calendar.MONTH);
            int toDay = to.get(Calendar.DAY_OF_MONTH);
            int year = toYear  -  fromYear;
            int month = toMonth  - fromMonth;
            int day = toDay  - fromDay;
            DayCompare dayCompare = new DayCompare();
            dayCompare.setYear(year);
            dayCompare.setMonth(month);
            dayCompare.setDay(day);
            return dayCompare;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        /*// TODO Auto-generated method stub
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=sdf.parse("2012-09-08 10:10:10");
        Date d2=sdf.parse("2012-09-15 00:00:00");
        System.out.println(daysBetween(d1,d2));*/

        Date date = new Date();
        String s = formatDate(date, 3);
        System.out.println("s:" + s);

        System.out.println(daysBetween("20191218","20191210"));
        System.out.println(getMonthSpace("20191010","20201010"));
        DayCompare dayCompare = dayComparePrecise2("20191010", "20201210");
        System.out.println(dayCompare);
        if (dayCompare != null){
            int totalMonth = dayCompare.year * 12 + dayCompare.month;
            System.out.println(totalMonth);
        }

        System.out.println(formatDate("20191010",4));

        System.out.println("*********1**********");
        Date date2 = new Date();
        long time = date2.getTime();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        System.out.println("time:" + time + ",timeInMillis:" + timeInMillis);

        System.out.println("*********2**********");

        System.out.println("time,1576806637926L:" + formatDate(new Date(1576806637926L),1));
        System.out.println("time,553640294L:" + formatDate(new Date(553640294L),1));
        System.out.println("time,1577237600074L:" + formatDate(new Date(1577237600074L),1));
        System.out.println("time,15772376000740001L:" + formatDate(new Date(15772376000740001L),4));
        System.out.println("time,new Date():" + formatDate(new Date(),4));
        System.out.println("time,new Date():" + formatDate(new Date(),5));

    }

}
