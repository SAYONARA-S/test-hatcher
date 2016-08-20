package com.hatcher.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jauker.widget.BadgeView;

import com.hatcher.app.R;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.LoginActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

/**
 * Created by wesley_s on 16/5/16.
 */
public class MineFragment extends BaseFragment {

    private View view;



    @ViewInject
    private RelativeLayout mine_person_info_layout;
    @ViewInject
    private ImageView mine_head_img;  // ?? 暂无数据
    @ViewInject
    private TextView mine_main_name;
    @ViewInject
    private TextView mine_main_job_name; // ？？ 暂无数据
    @ViewInject
    private RelativeLayout mine_help_layout;
    @ViewInject
    private RelativeLayout mine_settings_layout;
    @ViewInject
    private TextView logout_txt;









    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.mine_main_layout,null);

        CommonUtil.initViewInject(this,MineFragment.class,getActivity());



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

        mine_help_layout.setOnClickListener(this);
        logout_txt.setOnClickListener(this);

    }

    @Override
    protected void initData() {


        mine_main_name.setText(CommonUtil.getPreferences(CommonUtil.APP_USER_NAME));

        getCompleteHelpCount();


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case 1:
                break;
        }
    }

    @Override
    public View findViewByID(int id) {
        return view.findViewById(id);
    }



    private void getCompleteHelpCount(){
        showProgressDialog(null,getString(R.string.progress_dialog_request_prompt));
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
    public void updateCompleteCount(int count){
        BadgeView badgeView = new BadgeView(getActivity());
        badgeView.setTargetView(mine_head_img);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
        badgeView.setBadgeCount(count);
    }






}
