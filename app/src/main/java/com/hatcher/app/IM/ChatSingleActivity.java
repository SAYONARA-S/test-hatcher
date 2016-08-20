package com.hatcher.app.IM;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import com.hatcher.app.R;
import com.hatcher.app.ui.BaseActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

/**
 * Created by wesley_s on 16/7/13.
 */
public class ChatSingleActivity extends BaseActivity {

    @ViewInject
    private FrameLayout chat_container;

//    private String toUserId;  //单聊 用户id
    private EaseChatFragment chatFragment = new EaseChatFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_easeui_layout);
        CommonUtil.initViewInject(this,ChatSingleActivity.class,this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        goChat();
    }

    @Override
    protected void onClick(int viewId) {

    }

    private void goChat() {


        Bundle args = new Bundle();
//        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//        args.putString(EaseConstant.EXTRA_USER_ID, dto.getUsername());
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.chat_container, chatFragment).commitAllowingStateLoss();


    }

    public void sendMessage(String type,String content,String filePath,String toUserId){
        String [] pathSplit = filePath.split("/");
        String fileName =null;
        if(pathSplit.length > 0){
            fileName = pathSplit[pathSplit.length-1];
        }
    }





}
