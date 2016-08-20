package com.hatcher.app.ui;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

import com.hatcher.app.IM.CallReceiver;

/**
 * Created by Administrator on 2016-5-5.
 */
public class BaseApplication extends Application {

    private  SharedPreferences preferences;

//    private NMDbOpenHelper dbOpenHelper;

    private static BaseApplication self;

    public static final String ACCOUT_ID = "aaf98f8954939ed50154b73fceff23b0" ;
    public static final String AUTH_TOKEN = "0d9b516d1c574e6981d93d777477dae3";
    public static final String APP_ID = "aaf98f8954939ed50154b73fceff23b0";
    public static final String APP_TOKEN =  "81dba3cba20b05d5869e551e46814a76";

    public boolean isLoginIM;  //是否注册环信用户

//    public UserInfoDTO userInfoDTO;



    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences("app_shared", Context.MODE_PRIVATE);
//        dbOpenHelper = new NMDbOpenHelper(this, NMDbOpenHelper.DATABASE_NAME,null, NMDbOpenHelper.DATABASE_VERSION);
        self = this;

        initIMSDK();
    }

    /**
     * 环信IM 初始化
     */
    private void initIMSDK(){

        EMOptions options = new EMOptions();
        //初始化
        EMClient.getInstance().init(this, options);
        //打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        EaseUI.getInstance().init(this, null);// UI 框架初始化

        //注册通话监听
        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        registerReceiver(new CallReceiver(),callFilter);

        EMClient.getInstance().callManager().addCallStateChangeListener(new EMCallStateChangeListener() {
            @Override
            public void onCallStateChanged(CallState callState, CallError callError) {
                switch (callState) {
                    case CONNECTING:
                        break;
                    case CONNECTED: //连接已经建立
                        break;
                    case ACCEPTED://电话接通成功
                        break;
                    case DISCONNNECTED: //电话断了
                        break;
                    case NETWORK_UNSTABLE: //网络不稳定
                        if ( callError == CallError.ERROR_NO_DATA){
                            //无通话数据
                        }else{

                        }

                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
//        dbOpenHelper.close();
    }

    /**
     * @return
     */
    public static BaseApplication getInstance(){
        return self;
    }


    public SharedPreferences getPreferences() {
        return preferences;
    }

    public boolean isLoginIM() {
        return isLoginIM;
    }

    public void setIsLoginIM(boolean isLoginIM) {
        this.isLoginIM = isLoginIM;
    }
}
