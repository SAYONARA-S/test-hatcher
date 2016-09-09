package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
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

public class CompanyInfoActivity extends BaseActivity implements OnClickListener {

    @ViewInject
    private ImageView my_image_bg;
    @ViewInject
    private ImageView play;
    @ViewInject
    private RoundImageView header;
    @ViewInject
    private TextView team_info_text;
    @ViewInject
    private ImageView check;
    @ViewInject
    private ImageView more;
    @ViewInject
    private ImageView team_talk_btn;
    @ViewInject
    private ImageView team_join_btn;
    @ViewInject
    private GridView gview;
    @ViewInject
    private RelativeLayout back_layout;
    @ViewInject
    private RelativeLayout search_layout;

    private Context mContext;
    private Activity activity;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;
    private ArrayList<String> imageUrlList = new ArrayList<String>();

    public CompanyInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_companyinfo);
        mContext = this;
        activity = this;
        loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = Options.getListOptions();
        CommonUtil.initViewInject(this, CompanyInfoActivity.class, this);
//        viewContainer = new ArrayList<>();
        initView();
        imageUrlList.clear();
        sendGetTeamInfoRequest("");

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void initView() {
        back_layout.setOnClickListener(this);
        search_layout.setOnClickListener(this);
        play.setOnClickListener(this);
        check.setOnClickListener(this);
        more.setOnClickListener(this);
        team_talk_btn.setOnClickListener(this);
        team_join_btn.setOnClickListener(this);
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

        imageLoader.displayImage("http://avatar.csdn.net/9/7/0/1_mosibi.jpg",header,options);
        team_info_text.setText("啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀");


    }

    private void sendGetTeamInfoRequest(String teamid) {

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
            case R.id.search_layout:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.play:
                break;
            case R.id.check:
                break;
            case R.id.more:
                break;
            case R.id.team_talk_btn:
                break;
            case R.id.team_join_btn:
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
            RoundImageView imageView;
            if (view == null) {
                imageView = new RoundImageView(context);
                Display mDisplay = activity.getWindowManager().getDefaultDisplay();

                if (mDisplay.getWidth() == 720) {
                    imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
                } else {
                    imageView.setLayoutParams(new GridView.LayoutParams(160, 160));// 设置ImageView对象布局
                }
//				imageView.setAdjustViewBounds(false);// 设置边界对齐
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// 设置刻度的类型
                imageView.setPadding(2, 2, 2, 2);// 设置间距
                view = imageView;
            } else {
                imageView = (RoundImageView) view;
            }
            String imageUrl = urlList.get(position);
            imageLoader.displayImage(imageUrl, (RoundImageView) view, options);
            System.out.println("imageUrl " + imageUrl);
            return view;
        }

        class ViewHolder {
            ImageView a;
        }
    }

}
