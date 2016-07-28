package com.xiaofuquan.toc.lib.base.schema;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.xiaofuquan.toc.lib.base.utils.StringUtils;
import com.xiaofuquan.toc.lib.base.utils.XiaofuquanLog;

import java.io.File;

/**
 * Created by HaoSilence on 5/22/15.
 */
public class SchemeManager {

    private static SchemeManager mInstance;

    public static synchronized SchemeManager getInstance() {
        if (mInstance == null) {
            mInstance = new SchemeManager();
        }
        return mInstance;
    }

    public void naviActivity(Context context, String uri, Bundle bundle) {
        if (uri == null || uri.length() == 0 || uri.equals("")) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception ne) {
            ne.printStackTrace();
            XiaofuquanLog.d("URL", uri);
        }
    }

    public void naviActivityWithoutNewTask(Context context, String uri, Bundle bundle) {
        if (StringUtils.isEmpty(uri)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        } catch (Exception ne) {
            ne.printStackTrace();
        }
    }

    public void naviActivityForResult(Activity context, String uri, Bundle bundle,
                                      int requestCode) {
        if (StringUtils.isEmpty(uri)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivityForResult(intent, requestCode);
        } catch (Exception ne) {
            ne.printStackTrace();
        }
    }
}