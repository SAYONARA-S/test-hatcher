package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;

public class AddFriendActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView send;
    @ViewInject
    private ImageButton search_clear;
    @ViewInject
    private EditText search_edit;
    @ViewInject
    private RelativeLayout back_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();

    public AddFriendActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addfriend);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, AddFriendActivity.class, this);
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

        send.setOnClickListener(this);
        search_clear.setOnClickListener(this);
        back_layout.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    private void sendAddFriendRequest(String edittext) {

    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.send:
                sendAddFriendRequest(search_edit.getText().toString().trim());
                break;
            case R.id.search_clear:
                search_edit.setText("");
                break;
            default:
                break;
        }
    }

}
