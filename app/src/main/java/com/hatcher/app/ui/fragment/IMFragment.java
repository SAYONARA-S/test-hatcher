package com.hatcher.app.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.ImItemInfoBean;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.FriendManageActivity;
import com.hatcher.app.ui.PersonInfoActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.OnRefreshListener;
import com.hatcher.app.view.RefreshListView;
import com.hatcher.app.view.RoundImageView;
import com.jauker.widget.BadgeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wesley_s on 16/5/16.
 */
public class IMFragment extends BaseFragment implements OnRefreshListener {

    private View view;
    @ViewInject
    private RelativeLayout contacts_layout;
    @ViewInject
    private RelativeLayout title_layout;
    @ViewInject
    private RelativeLayout im_main_layout;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private RoundImageView header;  // ?? 暂无数据
    @ViewInject
    private RefreshListView list_view;
    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_location_text;

    private Context mContext;
    private Activity activity;

    private IMListInfoAdapter mIMListInfoAdapter;
    private List<ImItemInfoBean> imItemInfoBeanList = new ArrayList<ImItemInfoBean>();

    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private int page = 1;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private static final int START = 0;
    private static final int END = 1;
    private static final int WAIT = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_im_main, null);
        mContext = getContext();
        activity = getActivity();
        loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        CommonUtil.initViewInject(this, IMFragment.class, getActivity());

        initView();
        imItemInfoBeanList.clear();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData("refresh",0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        list_view.setOnRefreshListener(this);
        list_view.setVerticalScrollBarEnabled(true);
        contacts_layout.setOnClickListener(this);
        search_layout.setOnClickListener(this);
        back_layout.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        imageLoader.displayImage("http://avatar.csdn.net/9/7/0/1_mosibi.jpg",header,options);
        my_info_text.setText("我自己");
        my_location_text.setText("中国- -沈阳");
    }

    private void refreshData() {
        if (imItemInfoBeanList.size() > 0)
        {
            try {
                if (mIMListInfoAdapter != null)
                {
                    mIMListInfoAdapter.notifyDataSetChanged();
                }
                else
                {
                    mIMListInfoAdapter = new IMListInfoAdapter(imItemInfoBeanList);
                    list_view.setAdapter(mIMListInfoAdapter);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.contacts_layout:
            {
                startActivity(new Intent(mContext, FriendManageActivity.class));
            }
                break;
            case R.id.search_layout:
            {

            }
            break;
            case R.id.back_layout:
            {

            }
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

    @Override
    public void onDownPullRefresh() {
        page = 0;
        setData("refresh",page);
    }

    @Override
    public void onLoadingMore() {
        setData("more",page);
    }

    public void setData(String moreOrFirst, int curPage)
    {
        if ("more".equals(moreOrFirst))
        {
            list_view.hideFooterView();
        }
        else if ("refresh".equals(moreOrFirst))
        {
            list_view.hideHeaderView();
            imItemInfoBeanList.clear();
        }

        setListData(curPage);
        curPage++;
        page = curPage;
        refreshData();
    }

    private void setListData(int page)
    {
        for (int i = (1 + page * 5); i <= (5 * (page + 1)); i++) {
            imItemInfoBeanList.add(new ImItemInfoBean(i));
        }
    }
    public class IMListInfoAdapter extends BaseAdapter {

        List<ImItemInfoBean> infoList;
        Activity activity1;
        LayoutInflater inflater = null;
        int num = 0;

        public IMListInfoAdapter(List<ImItemInfoBean> infoBeanList) {
            this.infoList = infoBeanList;
            inflater = LayoutInflater.from(mContext);
//			activity1 = (Activity) activity;
        }

        @Override
        public int getCount() {
            return infoList == null ? 0 : infoList.size();
        }

        @Override
        public Object getItem(int position) {
            if (infoList != null && infoList.size() != 0) {
                return infoList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder mHolder;
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.item_im_main_list, null);
                mHolder = new ViewHolder();
                mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
                mHolder.item_info_layout = (RelativeLayout) view.findViewById(R.id.item_info_layout);
                mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
                mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
                mHolder.time = (TextView) view.findViewById(R.id.time);
                mHolder.header = (RoundImageView) view.findViewById(R.id.header);

                view.setTag(mHolder);

            } else {
                mHolder = (ViewHolder) view.getTag();
//                mHolder.ad_item_layout.invalidate();
//                mHolder.my_info_des.setText(null);
            }

            final ImItemInfoBean infoBean = infoList.get(position);
            final int temp = position;
            imageLoader.displayImage(infoBean.getHeader(), mHolder.header, options);
            mHolder.my_info_text.setText(infoBean.getName());
            mHolder.my_info_msg.setText(infoBean.getMsg());
            mHolder.time.setText(Options.getLongToDate4(infoBean.getTime()));
            if ((position % 2) == 0)
            {
                mHolder.item_info_layout.setBackgroundColor(getResources().getColor(R.color.white));
            }
            else
            {
                mHolder.item_info_layout.setBackgroundColor(getResources().getColor(R.color.nav_im_other_bg));
            }
            mHolder.item_info_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + temp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, PersonInfoActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }

        class ViewHolder {
            RelativeLayout item_layout, item_info_layout;
            TextView my_info_msg, my_info_text, time;
            RoundImageView header;
        }
    }


}
