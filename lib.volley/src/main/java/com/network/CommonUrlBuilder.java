package com.network;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class CommonUrlBuilder {
    public final static String TAG = CommonUrlBuilder.class.getSimpleName();

    private String              mBaseUrl;
    private StringBuilder       mStringBuilder;
    private Map<String, String> mParamsMap;
    private Map<String, String> mUrlKVMap;
    private boolean mIsFirstAdd = true;

    private static final String QUESTION_MARK = "?";
    private static final String EQUAL         = "=";
    private static final String AMPERSAND     = "&";
    private static final String UTF_8         = "UTF-8";

    public CommonUrlBuilder(String baseUrl) {
        mBaseUrl = baseUrl;
        mParamsMap = new LinkedHashMap<String, String>();
        mUrlKVMap = new TreeMap<String, String>();
    }

    public void put(String name, String value) {
        mParamsMap.put(name, value);
    }

    public void remove(String name) {
        mParamsMap.remove(name);
    }

    public String toUrl() {
        if (TextUtils.isEmpty(mBaseUrl) || mParamsMap.size() == 0) {
            return mBaseUrl;
        }
        this.mStringBuilder = new StringBuilder(mBaseUrl);
        mIsFirstAdd = true;

        Iterator<String> it = mParamsMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = mParamsMap.get(key);
            if (mIsFirstAdd) {
                mIsFirstAdd = false;
                if (mStringBuilder.indexOf(QUESTION_MARK) < 1) {
                    // does not contain "?"
                    mStringBuilder.append(QUESTION_MARK);
                    encode(key, value);
                } else {
                    // contains "?"
                    mStringBuilder.append(AMPERSAND);
                    encode(key, value);
                }
            } else {
                mStringBuilder.append(AMPERSAND);
                encode(key, value);
            }
        }

        return mStringBuilder.toString();
    }

    public String sortByKeyURL() {
        if (TextUtils.isEmpty(mBaseUrl)) {
            return "";
        }

        mStringBuilder = new StringBuilder(mBaseUrl);
        boolean isFirstSearch = true;

        if (mStringBuilder.indexOf(QUESTION_MARK) < 1) {
            // does not contain "?"
            return "";
        } else {
            // contains "?"
            while (1 < mStringBuilder.indexOf(EQUAL)) {
                if (isFirstSearch) {
                    isFirstSearch = false;
                    int question_mark_pos = mStringBuilder.indexOf(QUESTION_MARK);
                    mStringBuilder.delete(0, question_mark_pos + 1);
                } else {
                    int ampersand_pos = mStringBuilder.indexOf(AMPERSAND);
                    String item = "";
                    if (ampersand_pos > 1) {
                        item = mStringBuilder.substring(0, ampersand_pos);
                        mStringBuilder.delete(0, ampersand_pos + 1);
                    } else {
                        item = mStringBuilder.toString();
                        mStringBuilder.delete(0, mStringBuilder.length());
                    }

                    int equal_pos = item.indexOf(EQUAL);
                    String key = item.substring(0, equal_pos);
                    String value = item.substring(equal_pos + 1, item.length());
                    mUrlKVMap.put(key, value);
                }
            }
        }
        mapToStringWithoutEncode(mUrlKVMap);
        return mStringBuilder.toString();
    }

    private void encode(String name, String value) {
        try {
            mStringBuilder.append(URLEncoder.encode(name, UTF_8));
            mStringBuilder.append(EQUAL);
            mStringBuilder.append(URLEncoder.encode(value, UTF_8));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("VM does not support UTF-8 encoding");
        }
    }

    private void appendStr(String name, String value) {
        mStringBuilder.append(name);
        mStringBuilder.append(EQUAL);
        mStringBuilder.append(value);
    }

    public String mapToStringWithoutEncode(Map<String, String> map) {
        mStringBuilder = new StringBuilder();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.get(key);

            appendStr(key, value);
            if (it.hasNext()) {
                mStringBuilder.append(AMPERSAND);
            }
        }
        return mStringBuilder.toString();
    }

    public String mapToString(Map<String, String> map) {
        mStringBuilder = new StringBuilder();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.get(key);

            encode(key, value);
            if (it.hasNext()) {
                mStringBuilder.append(AMPERSAND);
            }
        }
        return mStringBuilder.toString();
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }
}
