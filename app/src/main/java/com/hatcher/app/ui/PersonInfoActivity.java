package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.HomeItemInfoBean;
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

public class PersonInfoActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView title_image;
    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private RoundImageView header;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_des_text;
    @ViewInject
    private TextView person_location_msg;
    @ViewInject
    private TextView send_msg_btn;
    @ViewInject
    private TextView add_friend_btn;
    @ViewInject
    private GridView gview;
    @ViewInject
    private RelativeLayout back_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;
    private ArrayList<String> imageUrlList = new ArrayList<String>();

    public PersonInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_personinfo);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, PersonInfoActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        imageUrlList.clear();
        sendGetPersonInfoRequest("");

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        send_msg_btn.setOnClickListener(this);
        add_friend_btn.setOnClickListener(this);
        back_layout.setOnClickListener(this);

        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(activity, ItemPhotoActivity.class);
                intent.putStringArrayListExtra("urllist", imageUrlList);
                intent.putExtra("ID", arg2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        GridAdapter imageAdapter = new GridAdapter(mContext, imageUrlList);
        gview.setAdapter(imageAdapter);
    }

    private void sendGetPersonInfoRequest(String personid) {

        for (int i = 0; i < 6; i++) {
            imageUrlList.add("http://avatar.csdn.net/9/7/0/1_mosibi.jpg");
        }
        initData();
    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.send_msg_btn:
                break;
            case R.id.add_friend_btn:
                startActivity(new Intent(mContext,AddFriendActivity.class));
                break;
            default:
                break;
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
            if (urlList.size() > 5)
                return 5;
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
