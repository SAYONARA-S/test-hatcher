package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.FriendItemInfoBean;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.hatcher.app.view.RoundImageView;
import com.hatcher.app.view.XCRoundRectImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView more;
    @ViewInject
    private ImageView check;
    @ViewInject
    private ImageView download_big;
    @ViewInject
    private RoundImageView header;
    @ViewInject
    private XCRoundRectImageView product_icon;
    @ViewInject
    private TextView my_info_text;
    @ViewInject
    private TextView my_info_msg;
    @ViewInject
    private TextView product_info_text;
    @ViewInject
    private TextView team_info_text;

    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;
    @ViewInject
    private RelativeLayout like_layout;
    @ViewInject
    private RelativeLayout share_layout;

    @ViewInject
    private LinearLayout id_gallery;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;

    private ArrayList<String> imageUrlList = new ArrayList<String>();

    public ProductInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rankinfo);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, ProductInfoActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        imageUrlList.clear();
        sendGetProductInfoRequest();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {

        more.setOnClickListener(this);
        check.setOnClickListener(this);
        back_layout.setOnClickListener(this);
        search_layout.setOnClickListener(this);
        like_layout.setOnClickListener(this);
        share_layout.setOnClickListener(this);
        id_gallery.setOnClickListener(this);
        download_big.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < imageUrlList.size(); i++) {
            ImageView a = new ImageView(mContext);
            LinearLayout.LayoutParams p =  new LinearLayout.LayoutParams(540,960);
            p.setMargins(5,0,5,0);
            a.setLayoutParams(p);
            a.setScaleType(ImageView.ScaleType.FIT_XY);
            imageLoader.displayImage(imageUrlList.get(i),a,options);
            id_gallery.addView(a);
        }
        imageLoader.displayImage("http://avatar.csdn.net/9/7/0/1_mosibi.jpg",header,options);
        imageLoader.displayImage("http://avatar.csdn.net/9/7/0/1_mosibi.jpg",product_icon,options);
        my_info_text.setText("hactcher");
        my_info_msg.setText("25.7M / 10万次下载");
        product_info_text.setText("阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬");
        team_info_text.setText("阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬");

    }

    private void sendGetProductInfoRequest() {

        for (int i = 0; i < 5; i++) {
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
            case R.id.search_layout:
                break;
            case R.id.like_layout:
                break;
            case R.id.share_layout:
                break;
            case R.id.more:
                break;
            case R.id.check:
                break;
            case R.id.id_gallery:
            {
                Intent intent = new Intent(activity, ItemPhotoActivity.class);
                intent.putStringArrayListExtra("urllist",imageUrlList);
                intent.putExtra("ID", 0);
                startActivity(intent);
            }
                break;
            case R.id.download_big:
                break;
            default:
                break;
        }
    }

}
