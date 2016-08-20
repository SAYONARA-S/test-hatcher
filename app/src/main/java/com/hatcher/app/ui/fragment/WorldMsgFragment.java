package com.hatcher.app.ui.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.List;

import com.hatcher.app.R;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

/**
 * Created by wesley_s on 16/5/16.
 */
public class WorldMsgFragment extends BaseFragment {


    @ViewInject
    private PullToRefreshListView works_list_view;


    private FrameLayout fragment_1_container;

    private List<View> pageList;

    private int currentPage =1;


    View view;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.news_main_layout,null);
        CommonUtil.initViewInject(this,WorldMsgFragment.class,getActivity());

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




    }

    @Override
    protected void initData() {




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                break;
        }
    }

    @Override
    public View findViewByID(int id) {
        return view.findViewById(id);
    }










}
