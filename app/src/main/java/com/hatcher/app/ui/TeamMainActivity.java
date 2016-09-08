package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;

public class TeamMainActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView apply;
    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout my_team_layout;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_info_des;
    @ViewInject
    private TextView my_id_text;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();

    public TeamMainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_team_main);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, TeamMainActivity.class, this);
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

        apply.setOnClickListener(this);
        search_layout.setOnClickListener(this);
        back_layout.setOnClickListener(this);
        my_team_layout.setOnClickListener(this);
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
            case R.id.apply:
                startActivity(new Intent(mContext, ApplyTeamActivity.class));
                break;
            case R.id.my_team_layout:
                startActivity(new Intent(mContext, TeamListActivity.class));
                break;
            case R.id.search_layout:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            default:
                break;
        }
    }

}
