package com.hatcher.app.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hatcher.app.service.http.HttpRequest;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.ui.BaseApplication;

public class CommonUtil {

    public static final String APP_SESSION_ID = "session_id";
    public static final String APP_USER_NAME = "user_name";
    public static final String APP_USER_ID = "user_id";

    public static final String REQUEST_COOKIE = "Cookie";


    public static final String CHAT_TYPE_TXT = "text";
    public static final String CHAT_TYPE_VOICE = "voice";
    public static final String CHAT_TYPE_IMAGE = "image";

    public static final String VOICE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            +"/amrcache" + File.separator;


    public  enum TAB_TYPE{ ALL_WORK_TAB,UNDO_WORK_TAB};

    public static void savePreferences(String key,String value){
        BaseApplication.getInstance().getPreferences().edit().putString(key,value).commit();
    }

    public static String getPreferences(String key){
        return BaseApplication.getInstance().getPreferences().getString(key,"");
    }

    public static void initViewInject(ViewFind find,Class<?> clazz,Context context){
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length != 0){
            Field[] _arr = fields;
            int _size = _arr.length;
            for(Field f : _arr){
                Annotation[] _anno = f.getAnnotations();
                for(Annotation a : _anno){
                    if(a instanceof ViewInject)
                        viewInject(find,f,(ViewInject)a,context);
                }
            }
        }
    }


    private static  void viewInject(ViewFind find,Field field,ViewInject viewInject,Context context){
        int viewId = viewInject.value();
        if(viewId == -1){
            viewId = context.getResources().getIdentifier(field.getName(),"id",context.getPackageName());
        }

        field.setAccessible(true);
        try {
            field.set(find,find.findViewByID(viewId));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static SimpleDateFormat format = new SimpleDateFormat();

    public static String  getCurrentTimeWithMD(){
        format.applyPattern("MM月dd日 HH时mm分");
        return format.format(new Date());
    }

    public static String getCurrentTimewithMD(long sendTime){
        format.applyPattern("MM月dd日 HH时mm分");
        return format.format(new Date(sendTime));
    }


    //隐藏虚拟键盘
    public static void HideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }
    }

    //显示虚拟键盘
    public static void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );

        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

    }

    //强制显示或者关闭系统键盘
    public static void KeyBoard(final EditText txtSearchKey,final String status)
    {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager)
                        txtSearchKey.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (status.equals("open")) {
                    m.showSoftInput(txtSearchKey, InputMethodManager.SHOW_FORCED);
                } else {
                    m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(), 0);
                }
            }
        }, 300);
    }

    //通过定时器强制隐藏虚拟键盘
    public static void TimerHideKeyboard(final View v)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run()
            {
                InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
                if ( imm.isActive( ) )
                {
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
                }
            }
        }, 10);
    }
    //输入法是否显示着
    public static boolean KeyBoard(EditText edittext)
    {
        boolean bool = false;
        InputMethodManager imm = ( InputMethodManager ) edittext.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) )
        {
            bool = true;
        }
        return bool;

    }

    /**
     * 下载文件
     * @param uri
     * @return
     */
    public static void downloadFile(final String uri){
        if(TextUtils.isEmpty(uri)) return;
        HttpUtil.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String [] paths = uri.split("/");
                    String fileName = null;
                    if(paths.length > 0){
                        fileName = paths[paths.length -1];
                        fileName = fileName.split("\\.")[0].toUpperCase() + ".mp3";
                    }
                    String reqUrl   = "";
                    for(int i = 0 ; i <= paths.length -2; i++){
                        reqUrl += paths[i];
                        reqUrl += "/";
                    }
                    reqUrl += fileName;
                    String localFileUrl = VOICE_SAVE_PATH + fileName;
                    HttpRequest.get(HttpUtil.getAPPRequestUrl(reqUrl)).header(REQUEST_COOKIE, getPreferences(APP_SESSION_ID)).
                            receive(new File(localFileUrl));
                } catch (HttpRequest.HttpRequestException e) {
                    e.printStackTrace();
                }

                //"http://10.11.16.75:8180/micia/music/0FB8F31313C040BEBD81FA7D46E7D59B.mp3"
            }
        });

        return ;
    }


    /**
     * 验证IPv4
     * @param ipAddress
     * @return
     */
    public static boolean isboolIP(String ipAddress){
        String ip ="(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }




}


