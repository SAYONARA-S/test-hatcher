package com.hatcher.app.IM;

import android.os.Bundle;
import android.widget.Button;

import com.hatcher.app.R;
import com.hatcher.app.ui.BaseActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

/**
 * Created by wesley_s on 16/8/15.
 */
public class CallActivity extends BaseActivity {

    @ViewInject
    private Button call_voice_btn;
    @ViewInject
    private Button call_video_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.call_video_layout);  //接听页面
        CommonUtil.initViewInject(this,CallActivity.class,this);
        super.onCreate(savedInstanceState);

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
        call_voice_btn.setOnClickListener(this);
        call_video_btn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClick(int viewId) {
        switch (viewId){
          /*  case R.id.call_voice_btn://语音通话
                break;
            case R.id.call_video_btn: //视频通话
                break;*/
        }
    }
}
