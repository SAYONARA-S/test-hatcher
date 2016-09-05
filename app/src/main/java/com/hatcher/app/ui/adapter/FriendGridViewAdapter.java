package com.hatcher.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.FriendItemInfoBean;
import com.hatcher.app.ui.PersonInfoActivity;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.view.MyGridView;
import com.hatcher.app.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
import java.util.Map;

public class FriendGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<FriendItemInfoBean> listitem;
    private LoginConfig loginConfig = LoginConfig.getInstance();
    protected ImageLoader imageLoader;
    DisplayImageOptions options;
    public FriendGridViewAdapter(Context context, List<FriendItemInfoBean> listitem) {
        this.context = context;
        this.listitem = listitem;
        loginConfig.loadConfig(context, Constants.LOGIN_CONFIG);
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited())
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = Options.getListOptions();
    }

    @Override
    public int getCount() {
        return listitem.size();
    }

    @Override
    public Object getItem(int position) {
        return listitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < 0 || listitem == null || position >= listitem.size()) {
            return convertView;
        }
        final ViewHolder mHolder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_friend_list, null);
            mHolder = new ViewHolder();
            mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
            mHolder.item_info_layout = (FrameLayout) view.findViewById(R.id.item_info_layout);
            mHolder.my_info_msg = (TextView) view.findViewById(R.id.my_info_msg);
            mHolder.my_info_text = (TextView) view.findViewById(R.id.my_info_text);
            mHolder.header = (RoundImageView) view.findViewById(R.id.header);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }

        final FriendItemInfoBean infoBean = listitem.get(position);
        final int temp = position;
        imageLoader.displayImage(infoBean.getHeader(), mHolder.header, options);
        mHolder.my_info_text.setText(infoBean.getName());
        mHolder.my_info_msg.setText(infoBean.getMsg());
        mHolder.item_info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp == 0)
                {
                    context.startActivity(new Intent(context, PersonInfoActivity.class));
                }
                Toast.makeText(context,temp+ "",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    class ViewHolder {
        RelativeLayout item_layout;
        FrameLayout item_info_layout;
        TextView my_info_msg, my_info_text;
        RoundImageView header;
    }
}
