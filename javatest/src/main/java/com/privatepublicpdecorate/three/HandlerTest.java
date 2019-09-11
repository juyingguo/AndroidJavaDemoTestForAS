package com.privatepublicpdecorate.three;

import com.privatepublicpdecorate.two.Handler;
import com.utils.FieldUtils;

import java.lang.reflect.Field;

/**
 * Date:2019/9/11,15:32
 * author:jy
 */
public class HandlerTest {
    private String TAG = HandlerTest.class.getSimpleName();
    public static void main(String[] args) {

        try {

            Object object = FieldUtils.getField(Handler.class,mH,"mCallback");
            System.out.println("TAG" + ",object:" + object);
            FieldUtils.setField(Handler.class,mH,"mCallback",new HCallback() );

            object = FieldUtils.getField(Handler.class,mH,"mCallback");

            System.out.println("TAG" + ",object:" + object);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static class H extends Handler {

    }
    static H mH = new H();

    public static class HCallback implements Handler.Callback{

        @Override
        public boolean handleMessage() {
            return false;
        }
    }


}
