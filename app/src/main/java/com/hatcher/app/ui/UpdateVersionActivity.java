package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UpdateVersionActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView apply;
    @ViewInject
    private RelativeLayout back_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    public UpdateVersionActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_updatemain);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, UpdateVersionActivity.class, this);
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

        apply.setOnClickListener(this);
        back_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    private void sendModifyPasswordRequest() {
        Toast.makeText(mContext,"发送成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.apply:
                sendModifyPasswordRequest();
                break;
            default:
                break;
        }
    }

}
