package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class RecommentActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private EditText edit_text;


    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public RecommentActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recomment);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, RecommentActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        search_layout.setOnClickListener(this);
        back_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    private void sendModifyPasswordRequest(String content) {
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.search_layout:
                sendModifyPasswordRequest(edit_text.getText().toString());
                break;
            default:
                break;
        }
    }

}
