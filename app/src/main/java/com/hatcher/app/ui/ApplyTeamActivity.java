package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;

public class ApplyTeamActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout my_team_layout;
    @ViewInject
    private EditText email_edit;
    @ViewInject
    private EditText introduce_edit;
    @ViewInject
    private RoundImageView my_team_icon;
    @ViewInject
    private EditText type_edit;
    @ViewInject
    private ImageView apply;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();

    public ApplyTeamActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_team_apply);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, ApplyTeamActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        sendGetTeamMainInfoRequest("token");

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        back_layout.setOnClickListener(this);
        my_team_layout.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void sendGetTeamMainInfoRequest(String token) {

        initData();
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.my_team_layout:
                startActivity(new Intent(mContext, TeamInfoActivity.class));
                break;
            case R.id.apply:
                break;

            default:
                break;
        }
    }

}
