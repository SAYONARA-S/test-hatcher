package com.hatcher.app.service.http.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016-5-5.
 */
public class HttpUtil {

    public  static  String APP_HOST = "http://";
    public  static  String APP_PORT = "8080";

    public  static String APP_TEST_HOST = "http://";
    public  static  String APP_TEST_PORT = "8080";

    public final static String APP_IMAGE_PATH = "/";

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static ExecutorService getExecutor(){
        return executor;
    }


    public static String getAPPRequestUrl(String reqPath){
        StringBuffer urlBuffer = new StringBuffer();
//        urlBuffer.append(APP_HOST);
        urlBuffer.append(APP_TEST_HOST);
        urlBuffer.append(":");
//        urlBuffer.append(APP_PORT);
        urlBuffer.append(APP_TEST_PORT);
        urlBuffer.append(reqPath);
        return urlBuffer.toString();
    }

    public static String getAPPImagePath(String checkSerialNum){
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(APP_HOST);
        urlBuffer.append(APP_IMAGE_PATH);
        urlBuffer.append(checkSerialNum);
        return urlBuffer.toString();
    }




}
