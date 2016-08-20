package com.hatcher.app.IM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 语音、视频通话 广播接收
 * Created by wesley_s on 16/8/15.
 */
public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //拨打方username
        String from = intent.getStringExtra("from");
        //call type
        String type = intent.getStringExtra("type");
        //跳转到通话页面
    }
}
