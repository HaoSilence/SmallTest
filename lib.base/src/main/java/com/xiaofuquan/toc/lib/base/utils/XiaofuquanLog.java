package com.xiaofuquan.toc.lib.base.utils;

import android.util.Log;

import com.xiaofuquan.toc.lib.base.BuildConfig;


/**
 * Created by HaoSilence on 6/17/15.
 */
public class XiaofuquanLog {

    public final static String LOG_TAG = XiaofuquanLog.class.getSimpleName();

    private XiaofuquanLog() {
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(tag, msg, tr);
        }
    }

    public static void getStackTraceString(Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            Log.getStackTraceString(tr);
        }
    }

    public static void println(int priority, String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.println(priority, tag, msg);
        }
    }

}
