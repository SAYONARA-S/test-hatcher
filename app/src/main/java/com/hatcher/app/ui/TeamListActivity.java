package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.SearchItemInfoBean;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.MyListView;
import com.hatcher.app.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TeamListActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView apply;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private LinearLayout list_view;
    @ViewInject
    private ScrollView scroll_view;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    private SearchListInfoAdapter getTeamListInfoAdapter;
    private SearchListInfoAdapter myTeamAdapter;
    private SearchListInfoAdapter likeTeamAdapter;
    private List<SearchItemInfoBean> getTeamList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> myTeamList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> likeTeamList = new ArrayList<SearchItemInfoBean>();
    private MyListView myTeamListView,likeTeamListView;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public TeamListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_team_list);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, TeamListActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        getTeamList.clear();
        myTeamList.clear();
        likeTeamList.clear();
        sendSearchRequest();
        initData();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {
        apply.setOnClickListener(this);
        back_layout.setOnClickListener(this);
        search_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void setListData(int page) {
        for (int i = 0; i <= page; i++) {
            if ((int) (Math.floor((double) (i / 2))) == 0)
                getTeamList.add(new SearchItemInfoBean(0));
            if ((int) (Math.floor((double) (i / 2))) == 1)
                getTeamList.add(new SearchItemInfoBean(1));
        }

        for (int i = 0; i < getTeamList.size(); i++) {
            if (getTeamList.get(i).getType() == 0)
                myTeamList.add(getTeamList.get(i));
            if (getTeamList.get(i).getType() == 1)
                likeTeamList.add(getTeamList.get(i));
        }
    }

    private void freshData() {
        if (getTeamList.size() > 0) {
            try {
                if (getTeamListInfoAdapter != null) {
                    getTeamListInfoAdapter.notifyDataSetChanged();
                } else {
                    MyListView searchListView = new MyListView(mContext);
                    searchListView.setDividerHeight(0);
                    getTeamListInfoAdapter = new SearchListInfoAdapter(getTeamList);
                    searchListView.setAdapter(getTeamListInfoAdapter);
                    list_view.addView(searchListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void refreshData() {
        if (myTeamList.size() > 0) {
            try {
                if (myTeamAdapter != null) {
                    myTeamAdapter.notifyDataSetChanged();
                } else {
                    myTeamListView = new MyListView(mContext);
                    myTeamListView.setDividerHeight(0);
                    myTeamAdapter = new SearchListInfoAdapter(myTeamList);
                    myTeamListView.setAdapter(myTeamAdapter);
                    LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view1 = (View) mInflater.inflate(R.layout.item_search_list_title, null);
                    TextView personTitle = (TextView) view1.findViewById(R.id.title);
                    personTitle.setText("我的团队");
                    myTeamListView.addHeaderView(view1);
                    list_view.addView(myTeamListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if (likeTeamList.size() > 0) {
            try {
                if (likeTeamAdapter != null) {

                    likeTeamAdapter.notifyDataSetChanged();
                } else {
                    likeTeamListView = new MyListView(mContext);
                    likeTeamListView.setDividerHeight(0);
                    likeTeamAdapter = new SearchListInfoAdapter(likeTeamList);
                    likeTeamListView.setAdapter(likeTeamAdapter);
                    LayoutInflater mInflater2 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view2 = (View) mInflater2.inflate(R.layout.item_search_list_title, null);
                    TextView teamTitle = (TextView) view2.findViewById(R.id.title);
                    teamTitle.setText("待审核团队");
                    likeTeamListView.addHeaderView(view2);
                    list_view.addView(likeTeamListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void sendSearchRequest() {
        int size = 8;
        setListData(size);
        refreshData();
//        freshData();
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
            case R.id.apply:
                Intent intent = new Intent(mContext, ApplyTeamActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public class SearchListInfoAdapter extends BaseAdapter {

        List<SearchItemInfoBean> infoList;
        Activity activity1;
        LayoutInflater inflater = null;
        int num = 0;

        public SearchListInfoAdapter(List<SearchItemInfoBean> infoBeanList) {
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
                view = inflater.inflate(R.layout.item_search_list, null);
                mHolder = new ViewHolder();
                mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
                mHolder.item_info_layout = (RelativeLayout) view.findViewById(R.id.item_info_layout);
                mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
                mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
                mHolder.header = (RoundImageView) view.findViewById(R.id.header);

                view.setTag(mHolder);

            } else {
                mHolder = (ViewHolder) view.getTag();
//                mHolder.ad_item_layout.invalidate();
//                mHolder.my_info_des.setText(null);
            }

            final SearchItemInfoBean infoBean = infoList.get(position);
            final int temp = position;
            imageLoader.displayImage(infoBean.getHeader(), mHolder.header, options);
            mHolder.my_info_text.setText(infoBean.getName());
            mHolder.my_info_msg.setText(infoBean.getMsg());
            if ((position % 2) == 0) {
                mHolder.item_info_layout.setBackgroundColor(getResources().getColor(R.color.nav_im_other_bg));
            } else {
                mHolder.item_info_layout.setBackgroundColor(getResources().getColor(R.color.white));
            }
            mHolder.item_info_layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + temp, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, TeamFormActivity.class));
                }
            });
            return view;
        }

        class ViewHolder {
            RelativeLayout item_layout, item_info_layout;
            TextView my_info_msg, my_info_text;
            RoundImageView header;
        }
    }

}
