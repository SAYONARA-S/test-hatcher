package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.FriendItemInfoBean;
import com.hatcher.app.service.bean.ImItemInfoBean;
import com.hatcher.app.ui.adapter.FriendGridViewAdapter;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private TextView title_text;
    @ViewInject
    private ListView list_view;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;

    private List<FriendItemInfoBean> friendItemInfoBeanList = new ArrayList<FriendItemInfoBean>();
    private FriendListInfoAdapter friendListInfoAdapter;
    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;

    public FriendListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_friendlist);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, FriendListActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        sendGetNewFriendListRequest();
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

        friendItemInfoBeanList.clear();
    }

    @Override
    protected void initData() {

        friendListInfoAdapter = new FriendListInfoAdapter(friendItemInfoBeanList);
        list_view.setAdapter(friendListInfoAdapter);

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view4 = (View) mInflater.inflate(R.layout.item_search_list_title, null);
        TextView productTitle = (TextView) view4.findViewById(R.id.title);
        productTitle.setText("新的朋友");
        list_view.addHeaderView(view4);
    }

    private void sendGetNewFriendListRequest() {
        setListData(25);

        initData();
    }

    private void setListData(int page) {
        for (int i = 0; i < page; i++) {
            friendItemInfoBeanList.add(new FriendItemInfoBean(i));
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

    public class FriendListInfoAdapter extends BaseAdapter {

        List<FriendItemInfoBean> infoList;
        Activity activity1;
        LayoutInflater inflater = null;
        int num = 0;

        public FriendListInfoAdapter(List<FriendItemInfoBean> infoBeanList) {
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
                view = inflater.inflate(R.layout.item_friendinfo_list, null);
                mHolder = new ViewHolder();
                mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
                mHolder.item_info_layout = (RelativeLayout) view.findViewById(R.id.item_info_layout);
                mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
                mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
                mHolder.add = (TextView) view.findViewById(R.id.add);
                mHolder.header = (RoundImageView) view.findViewById(R.id.header);

                view.setTag(mHolder);

            } else {
                mHolder = (ViewHolder) view.getTag();
//                mHolder.ad_item_layout.invalidate();
//                mHolder.my_info_des.setText(null);
            }

            final FriendItemInfoBean infoBean = infoList.get(position);
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
            mHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "add " + temp, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        class ViewHolder {
            RelativeLayout item_layout, item_info_layout;
            TextView my_info_msg, my_info_text, add;
            RoundImageView header;
        }
    }

}
