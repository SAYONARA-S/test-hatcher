package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.FriendItemInfoBean;
import com.hatcher.app.ui.adapter.FriendGridViewAdapter;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class CompanyListActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private TextView title_text;
    @ViewInject
    private GridView gview;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;

    private List<FriendItemInfoBean> companyItemInfoBeanList = new ArrayList<FriendItemInfoBean>();
    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();

    public CompanyListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_companylist);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, CompanyListActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        sendGetCompanyListRequest();
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

        companyItemInfoBeanList.clear();
    }

    @Override
    protected void initData() {

        FriendGridViewAdapter gridViewAdapter = new FriendGridViewAdapter(mContext, companyItemInfoBeanList);
        gview.setAdapter(gridViewAdapter);

    }

    private void sendGetCompanyListRequest() {
        setListData(25);

        initData();
    }

    private void setListData(int page) {
        for (int i = 0; i < page; i++) {
            companyItemInfoBeanList.add(new FriendItemInfoBean(i));
        }
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.search_layout:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            default:
                break;
        }
    }

}
