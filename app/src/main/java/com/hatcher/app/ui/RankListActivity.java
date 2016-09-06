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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.FriendItemInfoBean;
import com.hatcher.app.ui.adapter.FriendGridViewAdapter;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RefreshListView;
import com.hatcher.app.view.RoundImageView;
import com.hatcher.app.view.XCRoundRectImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RankListActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private TextView title_text;
    @ViewInject
    private ListView list_view;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private XCRoundRectImageView top1_icon;
    @ViewInject
    private XCRoundRectImageView top2_icon;
    @ViewInject
    private XCRoundRectImageView top3_icon;
    @ViewInject
    private TextView top1_text;
    @ViewInject
    private TextView top2_text;
    @ViewInject
    private TextView top3_text;
    @ViewInject
    private TextView top1_btn;
    @ViewInject
    private TextView top2_btn;
    @ViewInject
    private TextView top3_btn;

    private List<FriendItemInfoBean> rankItemInfoBeanList = new ArrayList<FriendItemInfoBean>();
    private RankListInfoAdapter rankListInfoAdapter;
    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;

    public RankListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ranklist);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, RankListActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        sendGetRankListRequest();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        back_layout.setOnClickListener(this);
        top1_btn.setOnClickListener(this);
        top2_btn.setOnClickListener(this);
        top3_btn.setOnClickListener(this);
        top1_icon.setOnClickListener(this);
        top2_icon.setOnClickListener(this);
        top3_icon.setOnClickListener(this);

        rankItemInfoBeanList.clear();
    }

    @Override
    protected void initData() {
        FriendItemInfoBean top1Info = new FriendItemInfoBean(0);
        FriendItemInfoBean top2Info = new FriendItemInfoBean(1);
        FriendItemInfoBean top3Info = new FriendItemInfoBean(2);

        imageLoader.displayImage(top1Info.getHeader(), top1_icon, options);
        imageLoader.displayImage(top2Info.getHeader(), top2_icon, options);
        imageLoader.displayImage(top3Info.getHeader(), top3_icon, options);

        top1_text.setText(top1Info.getName());
        top2_text.setText(top2Info.getName());
        top3_text.setText(top3Info.getName());


        rankListInfoAdapter = new RankListInfoAdapter(rankItemInfoBeanList);
        list_view.setAdapter(rankListInfoAdapter);
    }

    private void sendGetRankListRequest() {
        setListData(25);

        initData();
    }

    private void setListData(int page) {

        for (int i = 3; i < page; i++) {
            rankItemInfoBeanList.add(new FriendItemInfoBean(i));
        }
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.top1_btn:
                break;
            case R.id.top2_btn:
                break;
            case R.id.top3_btn:
                break;
            case R.id.top1_icon:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.top2_icon:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.top3_icon:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            default:
                break;
        }
    }

    public class RankListInfoAdapter extends BaseAdapter {

        List<FriendItemInfoBean> infoList;
        Activity activity1;
        LayoutInflater inflater = null;
        int num = 0;

        public RankListInfoAdapter(List<FriendItemInfoBean> infoBeanList) {
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
                view = inflater.inflate(R.layout.item_rankinfo_list, null);
                mHolder = new ViewHolder();
                mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
                mHolder.item_info_layout = (RelativeLayout) view.findViewById(R.id.item_info_layout);
                mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
                mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
                mHolder.rank_num = (TextView) view.findViewById(R.id.rank_num);
                mHolder.download = (TextView) view.findViewById(R.id.download);
                mHolder.header = (XCRoundRectImageView) view.findViewById(R.id.header);

                view.setTag(mHolder);

            } else {
                mHolder = (ViewHolder) view.getTag();
//                mHolder.ad_item_layout.invalidate();
//                mHolder.my_info_des.setText(null);
            }

            final FriendItemInfoBean infoBean = infoList.get(position);
            final int temp = position + 4;
            imageLoader.displayImage(infoBean.getHeader(), mHolder.header, options);
            mHolder.my_info_text.setText(infoBean.getName());
            mHolder.my_info_msg.setText(infoBean.getMsg());
            mHolder.rank_num.setText("" + temp);

            mHolder.item_info_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + temp, Toast.LENGTH_SHORT).show();
                }
            });
            mHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "add " + temp, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        class ViewHolder {
            RelativeLayout item_layout, item_info_layout;
            TextView my_info_msg, my_info_text, rank_num, download;
            XCRoundRectImageView header;
        }
    }

}
