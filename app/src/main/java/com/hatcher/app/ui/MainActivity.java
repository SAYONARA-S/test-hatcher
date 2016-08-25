package com.hatcher.app.ui;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.hatcher.app.ui.fragment.NewsListFragment;
import com.jauker.widget.BadgeView;

import com.hatcher.app.IM.IMMsgUtil;
import com.hatcher.app.R;
import com.hatcher.app.ui.fragment.MineFragment;
import com.hatcher.app.ui.fragment.WorldMsgFragment;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

public class MainActivity extends BaseActivity {


    @ViewInject
    private FrameLayout main_container;
    @ViewInject
    private RadioButton nav_world_btn;
    @ViewInject
    private RadioButton nav_chat_btn;
    @ViewInject
    private RadioButton nav_mine_btn;
    @ViewInject
    private RadioButton nav_func_btn;

    private FragmentManager fragmentManager;
    private WorldMsgFragment worldMsgFragment;
    private MineFragment mineFragment;

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtil.initViewInject(this, MainActivity.class, this);
        //测试


        initView();
        initData();
    }

    @Override
    protected void initView() {
        nav_world_btn.setOnClickListener(this);
        nav_chat_btn.setOnClickListener(this);
        nav_func_btn.setOnClickListener(this);
        nav_mine_btn.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        worldMsgFragment = new WorldMsgFragment();
        fragmentManager = getFragmentManager();

//        fragmentManager.beginTransaction().replace(R.id.main_container,new NewsListFragment()).commit();
        /**
         * IM判断注册
         */
        isRegiterIM();
    }
    @Override
    protected void onClick(int viewId) {
        switch (viewId){
            case R.id.nav_world_btn:

                nav_world_btn.setTextColor(Color.parseColor("#1AACAA"));
                nav_world_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.nav_mine_default)
                        , null, null);

                nav_chat_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_chat_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_mine_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_mine_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_func_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_func_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);


                worldMsgFragment = new WorldMsgFragment();
//                fragmentManager.beginTransaction().replace(R.id.main_container,new NewsListFragment()).commit();


                break;
            case R.id.nav_chat_btn:

                nav_world_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_world_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_chat_btn.setTextColor(Color.parseColor("#1AACAA"));
                nav_chat_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_func_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_func_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_mine_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_mine_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

//                chatModuleFragment = new ChatModuleFragment();
//                fragmentManager.beginTransaction().replace(R.id.main_container, chatModuleFragment).commit();



                break;
            case R.id.nav_mine_btn:
                nav_world_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_world_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_chat_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_chat_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_mine_btn.setTextColor(Color.parseColor("#1AACAA"));
                nav_mine_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_func_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_func_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                mineFragment  = new MineFragment();
//                fragmentManager.beginTransaction().replace(R.id.main_container,new NewsListFragment()).commit();

                break;
            case R.id.nav_func_btn:
                nav_world_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_world_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_chat_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_chat_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_mine_btn.setTextColor(Color.parseColor("#8C8B90"));
                nav_mine_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);

                nav_func_btn.setTextColor(Color.parseColor("#1AACAA"));
                nav_func_btn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this,
                        R.mipmap.nav_mine_default), null, null);
                break;
        }

    }







    /**
     * 下导航角标更新
     * @param count
     */
    public void updateUndoCount(int count){
        BadgeView badgeView = new BadgeView(this);
        badgeView.setTargetView(nav_world_btn);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
        badgeView.setBadgeCount(count);
    }

    private void isRegiterIM(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int regCode = IMMsgUtil.imRegister(CommonUtil.getPreferences(CommonUtil.APP_USER_NAME));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(regCode == 1 || regCode == 203){
                            loginIMByUserid();
                        }else{
//                            Toast.makeText(MainActivity.this, "注册IM失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }).start();
    }

    private void loginIMByUserid(){

        IMMsgUtil.loginIM();

    }

}
