package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity implements OnClickListener {


    private ImageView login_btn;
    private ImageView reg_btn;
    private TextView rem_btn;
    private EditText username;
    private EditText password;
    private RelativeLayout main;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    private Timer timer;
    private String userStr;
    private String pwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        if (!loginConfig.getLogout()) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        } else {
            loginConfig.setLogout(false);
        }

        login_btn = (ImageView) findViewById(R.id.login_btn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        reg_btn = (ImageView) findViewById(R.id.reg_btn);
        rem_btn = (TextView) findViewById(R.id.rem_btn);
        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userStr = s.toString();

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)) {
                    login_btn.setImageResource(R.drawable.login_enter_no);
                } else {
                    login_btn.setImageResource(R.drawable.selector_login);
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

                if (TextUtils.isEmpty(userStr) || TextUtils.isEmpty(pwdStr)) {
                    login_btn.setImageResource(R.drawable.login_enter_no);
                } else {
                    login_btn.setImageResource(R.drawable.selector_login);
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

        login_btn.setOnClickListener(this);
        reg_btn.setOnClickListener(this);
        rem_btn.setOnClickListener(this);

        if (loginConfig.getAccessToken() != null && !loginConfig.getAccessToken().equals("")) {
            finish();
        } else {

        }

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    private void sendLoginRequest(String userName, String passWord)
    {
        Intent intent = new Intent(mContext, TabMainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onClick(int viewId) {
        switch(viewId) {
            case R.id.login_btn: {
                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                if (userName == null || userName.trim().length() == 0) {
                    Toast.makeText(getBaseContext(), "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passWord == null || passWord.trim().length() == 0) {
                    Toast.makeText(getBaseContext(), "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendLoginRequest(userName.trim(),passWord.trim());
            }
            break;
            case R.id.reg_btn: {
            }
            break;
            case R.id.rem_btn: {
            }
            break;
            default:
                break;
        }
    }


    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出",
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
