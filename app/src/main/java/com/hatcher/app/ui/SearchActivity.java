package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.ImItemInfoBean;
import com.hatcher.app.service.bean.SearchItemInfoBean;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.MyListView;
import com.hatcher.app.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private EditText search_edit;
    @ViewInject
    private ImageButton search_clear;
    @ViewInject
    private RelativeLayout cancel_layout;
    @ViewInject
    private RelativeLayout search_info_layout;
    @ViewInject
    private TextView edit_text;
    @ViewInject
    private LinearLayout tab_layout;
    @ViewInject
    private LinearLayout list_view;
    @ViewInject
    private ScrollView scroll_view;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    private SearchListInfoAdapter personAdapter;
    private SearchListInfoAdapter teamAdapter;
    private SearchListInfoAdapter companyAdapter;
    private SearchListInfoAdapter productAdapter;
    private List<SearchItemInfoBean> searchItemInfoBeanList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> personList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> teamList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> companyList = new ArrayList<SearchItemInfoBean>();
    private List<SearchItemInfoBean> productList = new ArrayList<SearchItemInfoBean>();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public SearchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        CommonUtil.initViewInject(this, SearchActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();

        initData();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initData();
                edit_text.setText(search_edit.getText().toString());
                if (!"".equals(search_edit.getText().toString())) {
                    search_clear.setVisibility(View.VISIBLE);
                    search_info_layout.setVisibility(View.VISIBLE);
                    tab_layout.setVisibility(View.GONE);
                    scroll_view.setVisibility(View.GONE);
                } else {
                    search_clear.setVisibility(View.GONE);
                    search_info_layout.setVisibility(View.GONE);
                    tab_layout.setVisibility(View.VISIBLE);
                    scroll_view.setVisibility(View.GONE);
                }
            }
        });
        search_clear.setOnClickListener(this);
        cancel_layout.setOnClickListener(this);
        search_info_layout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        searchItemInfoBeanList.clear();
        personList.clear();
        teamList.clear();
        companyList.clear();
        productList.clear();
    }

    private void setListData(int page) {
        for (int i = 0; i <= page; i++) {
            if ((int) (Math.floor((double) (i / 2))) == 0)
                searchItemInfoBeanList.add(new SearchItemInfoBean(0));
            if ((int) (Math.floor((double) (i / 2))) == 1)
                searchItemInfoBeanList.add(new SearchItemInfoBean(1));
            if ((int) (Math.floor((double) (i / 2))) == 2)
                searchItemInfoBeanList.add(new SearchItemInfoBean(2));
            if ((int) (Math.floor((double) (i / 2))) == 3)
                searchItemInfoBeanList.add(new SearchItemInfoBean(3));
        }

        for (int i = 0; i < searchItemInfoBeanList.size(); i++) {
            if (searchItemInfoBeanList.get(i).getType() == 0)
                personList.add(searchItemInfoBeanList.get(i));
            if (searchItemInfoBeanList.get(i).getType() == 1)
                teamList.add(searchItemInfoBeanList.get(i));
            if (searchItemInfoBeanList.get(i).getType() == 2)
                companyList.add(searchItemInfoBeanList.get(i));
            if (searchItemInfoBeanList.get(i).getType() == 3)
                productList.add(searchItemInfoBeanList.get(i));
        }
    }

    private void refreshData() {
        if (personList.size() > 0) {
            try {
                if (personAdapter != null) {
                    personAdapter.notifyDataSetChanged();
                } else {
                    MyListView personListView = new MyListView(mContext);
                    personListView.setDividerHeight(0);
                    personAdapter = new SearchListInfoAdapter(personList);
                    personListView.setAdapter(personAdapter);
                    LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view1 = (View) mInflater.inflate(R.layout.item_search_list_title, null);
                    TextView personTitle = (TextView) view1.findViewById(R.id.title);
                    personTitle.setText("联系人");
                    personListView.addHeaderView(view1);
                    list_view.addView(personListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if (teamList.size() > 0) {
            try {
                if (teamAdapter != null) {

                    teamAdapter.notifyDataSetChanged();
                } else {
                    MyListView teamListView = new MyListView(mContext);
                    teamListView.setDividerHeight(0);
                    teamAdapter = new SearchListInfoAdapter(teamList);
                    teamListView.setAdapter(teamAdapter);
                    LayoutInflater mInflater2 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view2 = (View) mInflater2.inflate(R.layout.item_search_list_title, null);
                    TextView teamTitle = (TextView) view2.findViewById(R.id.title);
                    teamTitle.setText("团队");
                    teamListView.addHeaderView(view2);
                    list_view.addView(teamListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if (companyList.size() > 0) {
            try {
                if (companyAdapter != null) {
                    companyAdapter.notifyDataSetChanged();
                } else {
                    MyListView companyListView = new MyListView(mContext);
                    companyListView.setDividerHeight(0);
                    companyAdapter = new SearchListInfoAdapter(companyList);
                    companyListView.setAdapter(companyAdapter);
                    LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view3 = (View) mInflater.inflate(R.layout.item_search_list_title, null);
                    TextView companyTitle = (TextView) view3.findViewById(R.id.title);
                    companyTitle.setText("公司");
                    companyListView.addHeaderView(view3);
                    list_view.addView(companyListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if (productList.size() > 0) {
            try {
                if (productAdapter != null) {
                    productAdapter.notifyDataSetChanged();
                } else {
                    MyListView productListView = new MyListView(mContext);
                    productListView.setDividerHeight(0);
                    productAdapter = new SearchListInfoAdapter(productList);
                    productListView.setAdapter(productAdapter);
                    LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view4 = (View) mInflater.inflate(R.layout.item_search_list_title, null);
                    TextView productTitle = (TextView) view4.findViewById(R.id.title);
                    productTitle.setText("产品");
                    productListView.addHeaderView(view4);
                    list_view.addView(productListView);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void sendSearchRequest(String search_text) {
        int size = 8;
        try {
            size = Integer.parseInt(search_text);
        } catch (Exception e) {

        }
        setListData(size);

        refreshData();
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.cancel_layout:
                finish();
                break;
            case R.id.search_info_layout:
                sendSearchRequest(search_edit.getText().toString());
                search_info_layout.setVisibility(View.GONE);
                scroll_view.setVisibility(View.VISIBLE);
                break;
            case R.id.search_clear:
                search_edit.setText("");
                initData();
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
            mHolder.item_info_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + temp, Toast.LENGTH_SHORT).show();
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
