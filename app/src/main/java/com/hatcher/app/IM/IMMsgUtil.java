package com.hatcher.app.IM;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import com.hatcher.app.ui.BaseApplication;
import com.hatcher.app.util.CommonUtil;

/**
 * Created by wesley_s on 16/7/21.
 */
public class IMMsgUtil {


    /**
     *判断是否已注册环信
     * @returnr
     */
    public static int imRegister(String userName){
        int ret = -1;
        //IM注册
        try {
            EMClient.getInstance().createAccount(userName,userName);  //提供用户名，密码
            ret = 1;
        } catch (HyphenateException e) {
            ret = e.getErrorCode();
            e.printStackTrace();
            return ret;
        }
        return ret;
    }

    public static void loginIM(){


        EMClient.getInstance().login(CommonUtil.getPreferences(CommonUtil.APP_USER_NAME),
                CommonUtil.getPreferences(CommonUtil.APP_USER_NAME), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //加载群组和本地会话
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();

                        BaseApplication.getInstance().setIsLoginIM(true);


                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
    }
}
