package com.xiaofuquan.android.toc.app.shop.activity;

import android.net.Uri;
import android.os.Bundle;

import com.xiaofuquan.android.toc.app.shop.R;
import com.xiaofuquan.toc.lib.base.BaseActivity;
import com.xiaofuquan.toc.lib.base.utils.XiaofuquanLog;

import net.wequick.small.Small;

public class ProdDetailActivity extends BaseActivity {

    private final static String TAG = ProdDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_base);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            String from = uri.getQueryParameter("id");
            if (from != null) {
                XiaofuquanLog.d(TAG, " Small   : " + from);
                setResult(RESULT_CANCELED);
            }
        }
        Uri schemeUri = getIntent().getData();
        if (schemeUri != null) {
            String from = schemeUri.getQueryParameter("id");
            if (from != null) {
                XiaofuquanLog.d(TAG, " scheme id  : " + from);
                setResult(RESULT_OK);
            }
        }
        finish();
    }
}
