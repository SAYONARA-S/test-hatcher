package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
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

public class ModifyPasswordActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private RoundImageView header;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_location_text;
    @ViewInject
    private EditText oldpwd;
    @ViewInject
    private EditText newpassword;
    @ViewInject
    private EditText newpassword1;
    @ViewInject
    private ImageView modify_btn;
    @ViewInject
    private RelativeLayout back_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    public ModifyPasswordActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modifypassword);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, ModifyPasswordActivity.class, this);
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

        modify_btn.setOnClickListener(this);
        back_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        imageLoader.displayImage("http://avatar.csdn.net/9/7/0/1_mosibi.jpg",header,options);
        my_info_text.setText("我自己");
        my_location_text.setText("中国- -沈阳");
    }

    private void sendModifyPasswordRequest(String oldpwd, String newpwd) {
        Toast.makeText(mContext,"发送成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.modify_btn:
                if ("".equals(oldpwd.getText().toString()))
                {
                    Toast.makeText(mContext,"请输入旧密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(newpassword.getText().toString()))
                {
                    Toast.makeText(mContext,"请输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(newpassword1.getText().toString()))
                {
                    Toast.makeText(mContext,"请确认新密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newpassword1.getText().toString().equals(newpassword.getText().toString()))
                {
                    Toast.makeText(mContext,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                sendModifyPasswordRequest(oldpwd.getText().toString(),newpassword.getText().toString());
                break;
            default:
                break;
        }
    }

}
