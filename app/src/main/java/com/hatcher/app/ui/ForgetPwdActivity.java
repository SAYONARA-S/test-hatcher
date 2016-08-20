package com.hatcher.app.ui;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hatcher.app.R;

/**
 * Created by wesley_s on 16/5/12.
 */
public class ForgetPwdActivity extends BaseActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_forget_pwd);

        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
//        mWebView = (WebView)findViewById(R.id.forget_pwd_webview);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onClick(int viewId) {

    }
}
