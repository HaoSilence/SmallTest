package com.trioly.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by LiuHaoran on 18/1/2016.
 */
public class BuildConfigHelper {
    
    public final static String BUILDCONFIG_FILD_APPLICATION_ID = "APPLICATION_ID";
    public final static String BUILDCONFIG_FILD_FLAVOR         = "FLAVOR";
    public final static String BUILDCONFIG_FILD_VERSION_CODE   = "VERSION_CODE";
    public final static String BUILDCONFIG_FILD_VERSION_NAME   = "VERSION_NAME";
    public final static String BUILDCONFIG_FILD_BASE_URL       = "BASE_URL";
    public final static String BUILDCONFIG_FILD_API_VERSION    = "API_VERSION";
    public final static String BUILDCONFIG_FILD_ANDROID_PLATFORM    = "ANDROID_PLATFORM";
    public final static String BUILDCONFIG_FILD_STATION    = "STATION";

    public static Object getBuildConfigValue(Context context, String fieldName) {
        try {
            // TODO: 18/5/2016 替换${applicationId}为该有APP的程序名 
            Class<?> clazz = Class.forName("${applicationId}.BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
