package com.hatcher.app.ui.fragment;


import android.app.Activity;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.HomeItemInfoBean;
import com.hatcher.app.service.bean.ImItemInfoBean;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.ItemPhotoActivity;
import com.hatcher.app.ui.SearchActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.MyGridView;
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
public class HomeFragment extends BaseFragment implements OnRefreshListener {

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
    private RefreshListView list_view;

    private Context mContext;
    private Activity activity;

    private HomeListInfoAdapter mHomeListInfoAdapter;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    private List<HomeItemInfoBean> homeItemInfoBeanList = new ArrayList<HomeItemInfoBean>();

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
        view = inflater.inflate(R.layout.activity_home, null);
        mContext = getContext();
        activity = getActivity();
        loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, HomeFragment.class, getActivity());

        initView();
        homeItemInfoBeanList.clear();
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

    }

    private void refreshData() {
        if (homeItemInfoBeanList.size() > 0)
        {
            try {
                if (mHomeListInfoAdapter != null)
                {
                    mHomeListInfoAdapter.notifyDataSetChanged();
                }
                else
                {
                    mHomeListInfoAdapter = new HomeListInfoAdapter(homeItemInfoBeanList);
                    list_view.setAdapter(mHomeListInfoAdapter);
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

            }
                break;
            case R.id.search_layout:
            {
                startActivity(new Intent(mContext, SearchActivity.class));
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
            homeItemInfoBeanList.clear();
        }

        setListData(curPage);
        curPage++;
        page = curPage;
        refreshData();
    }

    private void setListData(int page)
    {
        for (int i = (1 + page * 5); i <= (5 * (page + 1)); i++) {
            homeItemInfoBeanList.add(new HomeItemInfoBean(i));
        }
    }
    public class HomeListInfoAdapter extends BaseAdapter {

        List<HomeItemInfoBean> infoList;
        Activity activity1;
        LayoutInflater inflater = null;
        int num = 0;

        public HomeListInfoAdapter(List<HomeItemInfoBean> infoBeanList) {
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
                view = inflater.inflate(R.layout.item_home, null);
                mHolder = new ViewHolder();
                mHolder.item_layout = (FrameLayout) view.findViewById(R.id.item_layout);
                mHolder.item_info_layout = (RelativeLayout) view.findViewById(R.id.item_info_layout);
                mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
                mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
                mHolder.header = (RoundImageView) view.findViewById(R.id.header);
                mHolder.image_grid_view = (MyGridView) view.findViewById(R.id.image_grid_view);
                view.setTag(mHolder);

            } else {
                mHolder = (ViewHolder) view.getTag();
//                mHolder.ad_item_layout.invalidate();
//                mHolder.my_info_des.setText(null);
            }

            final HomeItemInfoBean infoBean = infoList.get(position);
            final int temp = position;
            imageLoader.displayImage(infoBean.getHeader(), mHolder.header, options);
            mHolder.my_info_text.setText(infoBean.getName());
            mHolder.my_info_msg.setText(infoBean.getMsg());
            mHolder.item_info_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + temp, Toast.LENGTH_SHORT).show();
                }
            });

            GridAdapter imageAdapter = new GridAdapter(mContext, infoBean.getImageURLlist());
            final ArrayList<String> urllist = infoBean.getImageURLlist();
            mHolder.image_grid_view.setAdapter(imageAdapter);
//			mHolder.image_grid_view.setLayoutParams(new RelativeLayout.LayoutParams(182*3, 182*3));// 设置ImageView对象布局
            mHolder.image_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(activity, ItemPhotoActivity.class);
                    intent.putStringArrayListExtra("urllist", urllist);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            });
            return view;
        }

        class ViewHolder {
            FrameLayout item_layout;
            RelativeLayout item_info_layout;
            TextView my_info_msg, my_info_text;
            RoundImageView header;
            MyGridView image_grid_view;
        }
    }


    public class GridAdapter extends BaseAdapter {

        private List<String> urlList;
        private Context context;
        LayoutInflater inflater = null;

        public GridAdapter(Context context, List<String> urlList) {
            this.context = context;
            this.urlList = urlList;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return urlList == null ? 0 : urlList.size();
        }

        @Override
        public Object getItem(int position) {
            if (urlList != null && urlList.size() != 0) {
                return urlList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewgroup) {
            ImageView imageView;
            if (view == null) {
                imageView = new ImageView(context);
                Display mDisplay = activity.getWindowManager().getDefaultDisplay();

                if (mDisplay.getWidth() == 720) {
                    imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
                } else {
                    imageView.setLayoutParams(new GridView.LayoutParams(180, 180));// 设置ImageView对象布局
                }
//				imageView.setAdjustViewBounds(false);// 设置边界对齐
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// 设置刻度的类型
                imageView.setPadding(2, 2, 2, 2);// 设置间距
                view = imageView;
            } else {
                imageView = (ImageView) view;
            }
            String imageUrl = urlList.get(position);
            imageLoader.displayImage(imageUrl, (ImageView) view, options);
            System.out.println("imageUrl " + imageUrl);
            return view;
        }

        class ViewHolder{
            ImageView a;
        }
    }

}
