package com.hatcher.app.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;
import com.hatcher.app.R;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.ModifyPasswordActivity;
import com.hatcher.app.ui.PersonInfoActivity;
import com.hatcher.app.ui.SettingMainActivity;
import com.hatcher.app.ui.adapter.GridViewAdapter;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;
import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wesley_s on 16/5/16.
 */
public class MyFragment extends BaseFragment {

    private View view;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_info_des;
    @ViewInject
    private TextView my_id_text;
    @ViewInject
    private RelativeLayout my_page_layout;
    @ViewInject
    private RelativeLayout my_photo_layout;
    @ViewInject
    private RelativeLayout my_settings_layout;

    private Context mContext;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_my, null);
        mContext = getContext();
        activity = getActivity();
        CommonUtil.initViewInject(this, MyFragment.class, getActivity());

        initView();
        initData();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        my_page_layout.setOnClickListener(this);
        my_photo_layout.setOnClickListener(this);
        my_settings_layout.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_page_layout:
                startActivity(new Intent(mContext, PersonInfoActivity.class));
                break;
            case R.id.my_photo_layout:
                break;
            case R.id.my_settings_layout:
                startActivity(new Intent(mContext, SettingMainActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public View findViewByID(int id) {
        return view.findViewById(id);
    }


    private void getCompleteHelpCount() {
        showProgressDialog(null, getString(R.string.progress_dialog_request_prompt));
        HttpUtil.getExecutor().execute(new Runnable() {
            @Override
            public void run() {


               /* final String countStr =MineService.getDoneAssistCnt(getActivity());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        if(countStr == null){
                            Toast.makeText(getActivity(), "数据获取错误！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        updateCompleteCount(Integer.valueOf(countStr));
                    }
                });*/
            }
        });
    }

    /**
     * @param count
     */
    public void updateCompleteCount(int count) {
        BadgeView badgeView = new BadgeView(getActivity());
//        badgeView.setTargetView(mine_head_img);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
        badgeView.setBadgeCount(count);
    }

}
