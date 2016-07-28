package com.baseproject.util;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * Created by LiuHaoran on 18/1/2016.
 */
public class BaseProjectLog {
    
    public final static String LOG_TAG = BaseProjectLog.class.getSimpleName();
    private final static boolean LOG_DEBUG = false;
    
    private BaseProjectLog() {
    }
    
    public static void v(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.i(tag, msg);
        }
    }
    
    public static void v(String tag, String msg, Throwable tr) {
        if (LOG_DEBUG) {
            Log.v(tag, msg, tr);
        }
    }
    
    public static void d(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.d(tag, msg);
        }
    }
    
    public static void d(String tag, String msg, Throwable tr) {
        if (LOG_DEBUG) {
            Log.d(tag, msg, tr);
        }
    }
    
    public static void i(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.i(tag, msg);
        }
    }
    
    public static void i(String tag, String msg, Throwable tr) {
        if (LOG_DEBUG) {
            Log.i(tag, msg, tr);
        }
    }
    
    public static void w(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.w(tag, msg);
        }
    }
    
    public static void w(String tag, String msg, Throwable tr) {
        if (LOG_DEBUG) {
            Log.w(tag, msg, tr);
        }
    }
    
    public static void w(String tag, Throwable tr) {
        if (LOG_DEBUG) {
            Log.w(tag, tr);
        }
    }
    
    public static void e(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.e(tag, msg);
        }
    }
    
    public static void e(String tag, String msg, Throwable tr) {
        if (LOG_DEBUG) {
            Log.e(tag, msg, tr);
        }
    }
    
    public static void getStackTraceString(Throwable tr) {
        if (LOG_DEBUG) {
            Log.getStackTraceString(tr);
        }
    }
    
    public static void println(int priority, String tag, String msg) {
        if (LOG_DEBUG) {
            Log.println(priority, tag, msg);
        }
    }
    
    public static void printlnNetworkResponse(final NetworkResponse response) {
        printlnNetworkResponse(response, "");
    }
    
    public static void printlnNetworkResponse(final NetworkResponse response, final String url) {
        if (LOG_DEBUG) {
            d("NetworkResponseInfo",
                    "Request Response Time : " + response.networkTimeMs + "ms   RequestUrl : " +
                            url);
        }
    }
    
    public static void printlnNetworkResponseError(final VolleyError error) {
        printlnNetworkResponseError(error, "");
    }
    
    public static void printlnNetworkResponseError(final VolleyError error, final String url) {
        if (LOG_DEBUG) {
            if (error.networkResponse != null) {
                d("NetworkResponseInfo",
                        "Request Response Time : " + error.getNetworkTimeMs() + "ms   RequestUrl " +
                                ": " + url + "  StatusCode : " + (error.networkResponse
                                .statusCode));
            } else {
                d("NetworkResponseInfo",
                        "Request Response Time : " + error.getNetworkTimeMs() + "ms   RequestUrl " +
                                ": " + url + "  error : " + error.toString());
            }
        }
    }
    
}
