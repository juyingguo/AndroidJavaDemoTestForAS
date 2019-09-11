package com.utils;

import java.lang.reflect.Field;

/**
 * Date:2019/9/11,15:43
 * author:jy
 */
public class FieldUtils {
    public static void setField (Class clazz , Object target , String name , Object value )
            throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

    public static Object getField (Class clazz , Object target , String name)
            throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }
}
