package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;

public class TeamInfoActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView title_image;
    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private RoundImageView header;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_des_text;
    @ViewInject
    private TextView person_location_msg;
    @ViewInject
    private TextView send_msg_btn;
    @ViewInject
    private TextView add_friend_btn;
    @ViewInject
    private GridView gview;
    @ViewInject
    private RelativeLayout back_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();

    public TeamInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_teaminfo);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, TeamInfoActivity.class, this);
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

        send_msg_btn.setOnClickListener(this);
        add_friend_btn.setOnClickListener(this);
        back_layout.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    private void sendRegsiterRequest(String userName, String passWord, String fullName, String city, int gender) {

    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.send_msg_btn:
                break;
            case R.id.add_friend_btn:
                break;
            default:
                break;
        }
    }

}
