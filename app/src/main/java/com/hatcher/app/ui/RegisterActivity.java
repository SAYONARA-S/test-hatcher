package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;

public class RegisterActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView male;
    @ViewInject
    private ImageView female;
    @ViewInject
    private ImageView checkbox;
    @ViewInject
    private ImageView reg_btn;
    @ViewInject
    private EditText username;
    @ViewInject
    private EditText password;
    @ViewInject
    private EditText fullname;
    @ViewInject
    private EditText city;
    @ViewInject
    private TextView text2;
    @ViewInject
    private TextView text4;
    @ViewInject
    private RelativeLayout check_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    private String userStr;
    private String pwdStr;
    private String nameStr;
    private String cityStr;
    private boolean isChecked = false;
    private int gender = 0; // 0:未知;1:男;2:女

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, RegisterActivity.class, this);
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
        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userStr = s.toString();

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)
                        || TextUtils.isEmpty(nameStr)
                        || TextUtils.isEmpty(cityStr)
                        || !isChecked) {
                    reg_btn.setImageResource(R.drawable.reg_enter_no);
                } else {
                    reg_btn.setImageResource(R.drawable.selector_reg);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                pwdStr = s.toString();

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)
                        || TextUtils.isEmpty(nameStr)
                        || TextUtils.isEmpty(cityStr)
                        || !isChecked) {
                    reg_btn.setImageResource(R.drawable.reg_enter_no);
                } else {
                    reg_btn.setImageResource(R.drawable.selector_reg);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        fullname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameStr = s.toString();

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)
                        || TextUtils.isEmpty(nameStr)
                        || TextUtils.isEmpty(cityStr)
                        || !isChecked) {
                    reg_btn.setImageResource(R.drawable.reg_enter_no);
                } else {
                    reg_btn.setImageResource(R.drawable.selector_reg);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        city.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cityStr = s.toString();

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)
                        || TextUtils.isEmpty(nameStr)
                        || TextUtils.isEmpty(cityStr)
                        || !isChecked) {
                    reg_btn.setImageResource(R.drawable.reg_enter_no);
                } else {
                    reg_btn.setImageResource(R.drawable.selector_reg);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        checkbox.setImageResource(R.drawable.icon_uncheck);
        reg_btn.setOnClickListener(this);
        checkbox.setOnClickListener(this);
        check_layout.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        text2.setOnClickListener(this);
        text4.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void sendRegsiterRequest(String userName, String passWord, String fullName, String city, int gender) {

    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.male: {
                gender = 1;
                male.setImageResource(R.drawable.icon_male_select);
                female.setImageResource(R.drawable.icon_female_normal);
            }
            break;
            case R.id.female: {
                gender = 2;
                male.setImageResource(R.drawable.icon_male_normal);
                female.setImageResource(R.drawable.icon_female_select);
            }
            break;
            case R.id.text2: {

            }
            break;
            case R.id.text4: {

            }
            break;
            case R.id.reg_btn: {
                sendRegsiterRequest(userStr, pwdStr, nameStr, cityStr, gender);
            }
            break;
            case R.id.check_layout:
            case R.id.checkbox:
                isChecked = !isChecked;
                if (isChecked) {
                    checkbox.setImageResource(R.drawable.icon_checked);
                } else {
                    checkbox.setImageResource(R.drawable.icon_uncheck);
                }
                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)
                        || TextUtils.isEmpty(nameStr)
                        || TextUtils.isEmpty(cityStr)
                        || !isChecked) {
                    reg_btn.setImageResource(R.drawable.reg_enter_no);
                } else {
                    reg_btn.setImageResource(R.drawable.selector_reg);
                }
                break;
            default:
                break;
        }
    }

}
