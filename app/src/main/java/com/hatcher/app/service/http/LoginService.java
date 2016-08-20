package com.hatcher.app.service.http;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.LoginRspDTO;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.BaseApplication;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.LogUtil;

/**
 * Created by Administrator on 2016-5-5.
 */
public class LoginService extends BaseService {


//    private static IMSqliteSupport imSqliteSupport = new IMSqliteSupport();


    public static LoginRspDTO userLogin(Context context,String userName,String pwd){

//        imSqliteSupport.getIMChatMsgByUserId("id888888");
        LoginRspDTO bean = null;



        ObjectMapper mapper = new ObjectMapper();

        String reqURL = HttpUtil.getAPPRequestUrl(context.getString(R.string.app_login));


        Map<String,String> params = new HashMap<String,String>();
        params.put("username",userName);
        params.put("password", pwd);
        String response = null;
        try {
            String postEntity = mapper.writeValueAsString(params);


            //form 提交
            HttpRequest request = HttpRequest.post(reqURL).contentType(HttpRequest.CONTENT_TYPE_FORM,
                    HttpRequest.CHARSET_UTF8).accept(HttpRequest.CONTENT_TYPE_JSON);
            request = request.form(params);
            response  = request.body();

            bean = mapper.readValue(response, LoginRspDTO.class);

            //接口调用成功
            if("2000".equals(bean.getCode())){
                String cookie = request.header("Set-Cookie");
                String [] cookies = cookie.split(";");
                if(cookies.length > 0){
                    CommonUtil.savePreferences(CommonUtil.APP_SESSION_ID,cookies[0]);
                    loginCloud(context, cookies[0],bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return  bean;
    }

    private static LoginRspDTO loginCloud(Context context,String cookie,LoginRspDTO dto) throws Exception{

       /* ObjectMapper mapper = new ObjectMapper();
       String retJson = HttpRequest.get(HttpUtil.getAPPRequestUrl(context.getString(R.string.cloud_login)))
               .header("Cookie", cookie + "\r\n").body();*/
//        UserInfoDTO userInfoDTO = mapper.readValue(retJson.toLowerCase(), UserInfoDTO.class);
//        dto.setData(userInfoDTO);

     /*   if(userInfoDTO !=null){
            //本地缓存
            CommonUtil.savePreferences(CommonUtil.APP_CLOUD_USER_NAME,userInfoDTO.getCloudusername());
            CommonUtil.savePreferences(CommonUtil.APP_CLOUD_PASSWORD,userInfoDTO.getCloudpassword());
            CommonUtil.savePreferences(CommonUtil.APP_USER_NAME,userInfoDTO.getUsername());
            CommonUtil.savePreferences(CommonUtil.APP_USER_CODE,userInfoDTO.getUsercode());
            CommonUtil.savePreferences(CommonUtil.APP_USER_ID,userInfoDTO.getUserid());
            BaseApplication.getInstance().setUserInfoDTO(userInfoDTO);
        }*/

//        LogUtil.e(TAG,"login cloud result " + retJson);
        return dto;
    }
}
