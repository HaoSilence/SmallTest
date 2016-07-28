package com.xiaofuquan.android.toc.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xiaofuquan.toc.lib.base.BaseActivity;
import com.xiaofuquan.toc.lib.base.schema.SchemeManager;
import com.xiaofuquan.toc.lib.base.utils.XiaofuquanLog;

import net.wequick.small.Small;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn_default)
    Button btnDefault;
    @BindView(R.id.framelayout_content)
    FrameLayout framelayoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_default1:
                Small.openUri("shop/goods?id=small", this);
                break;
            case R.id.btn_default:
                SchemeManager.getInstance().naviActivityForResult(this, "xfq://goods/details?id=scheme", null, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
                XiaofuquanLog.d(TAG, "onActivityResult FAIL !!!!!!!!");
            return;
        }
        switch (requestCode) {
            case 1:
                XiaofuquanLog.d(TAG, "onActivityResult Success !!!!!!!!");
                break;
        }
    }
}
