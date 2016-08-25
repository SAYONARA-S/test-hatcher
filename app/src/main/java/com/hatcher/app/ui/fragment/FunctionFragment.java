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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.http.util.HttpUtil;
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
public class FunctionFragment extends BaseFragment {

    private View view;
    @ViewInject
    private RelativeLayout function_main_layout;
    @ViewInject
    private RelativeLayout title_layout;
    @ViewInject
    private RelativeLayout function_data_layout;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private ImageView title_image;  // ?? 暂无数据
    @ViewInject
    private ImageView back;  // ?? 暂无数据
    @ViewInject
    private ImageView search;  // ?? 暂无数据
    @ViewInject
    private GridView gview;

    private int[] icon = {R.drawable.selector_near, R.drawable.selector_rank,
            R.drawable.selector_startup, R.drawable.selector_company};
    private String[] iconName = {"附件", "排行", "创业", "企业"};
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Context mContext;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_function, null);
        mContext = getContext();
        activity = getActivity();
        CommonUtil.initViewInject(this, FunctionFragment.class, getActivity());

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
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
    }

    @Override
    protected void initData() {
        //获取数据
        getData();
        GridViewAdapter adapter = new GridViewAdapter(mContext,data_list);
        //配置适配器
        gview.setAdapter(adapter);
        getCompleteHelpCount();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case 1:
                break;
        }
    }

    @Override
    public View findViewByID(int id) {
        return view.findViewById(id);
    }


    private void getCompleteHelpCount() {
//        showProgressDialog(null, getString(R.string.progress_dialog_request_prompt));
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


    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }


}
