package com.hatcher.app.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.service.bean.LoginRspDTO;
import com.hatcher.app.service.http.LoginService;
import com.hatcher.app.service.http.util.HttpUtil;
import com.hatcher.app.util.AnimationUtil;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wesley_s on 16/5/12.
 */
public class LoginActivity1 extends BaseActivity {


    @ViewInject
    private ImageView login_splash_img;
    @ViewInject
    private EditText login_username_edt;
    @ViewInject
    private EditText login_pwd_edt;
    @ViewInject
    private Button login_submit_btn;
    @ViewInject
    private TextView hide_server_settings;
    /*@ViewInject
    private TextView login_forget_pwd_txt;*/
    @ViewInject
    private LinearLayout login_main_layout;

    private AlertDialog dialog;
//    @ViewInject
//    private ViewPager login_view_pager;
//    @ViewInject
//    private LinearLayout login_pager_layout;
//    @ViewInject
//    private FrameLayout login_wizard_layout;

//    private List<View> viewContainer;

    private Button mPreSelectedBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        CommonUtil.initViewInject(this, LoginActivity1.class, this);
//        viewContainer = new ArrayList<>();
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
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void initView() {
        login_submit_btn.setOnClickListener(this);
        //login_forget_pwd_txt.setOnClickListener(this);
        //loadViewPager();
        hide_server_settings.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        showSplashImg();

    }

    private void showSplashImg() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startSplashHideAnim();
                    }
                });
            }
        }, 2000);
    }


//    private void startViewPagerHdieAnim(){
//        AlphaAnimation anim = new AlphaAnimation(1f,0.0f);
//        anim.setDuration(800);
//        login_wizard_layout.startAnimation(anim);
//
//        anim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                login_main_layout.setBackgroundColor(Color.parseColor("#f2f2f2"));
//                login_wizard_layout.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }

    private void startSplashHideAnim() {

        AnimationUtil.startSplashHideAnim(login_splash_img, 800, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 判断是否已登录
                 */
                if (!TextUtils.isEmpty(CommonUtil.getPreferences(CommonUtil.APP_SESSION_ID))) {
                    Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                login_splash_img.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    protected void onClick(int viewId) {

        switch (viewId) {
            case R.id.login_submit_btn:

                userLogin();
                break;
            /*case R.id.login_forget_pwd_txt:
                Intent intent = new Intent(this, ForgetPwdActivity.class);
                startActivity(intent);
                break;*/
            case R.id.hide_server_settings:
                countClickHideSettings();
                break;
        }
    }

    int currentClickCount;
    long startTime;
    long endTime;

    private void countClickHideSettings() {
        if (currentClickCount == 0) {
            startTime = System.currentTimeMillis();
        }
        currentClickCount++;
        if (currentClickCount == 3) {
            endTime = System.currentTimeMillis();
            if ((endTime - startTime) <= 2000) {
                showServerIPDialog();

            }
            currentClickCount = 0;
        }
    }

    private void showServerIPDialog() {
    /*    final View view = LayoutInflater.from(this).inflate(R.layout.server_ip_dialog, null);
        dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt = (EditText) view.findViewById(R.id.server_ip_edt);
                        String _ip = edt.getText().toString();
                        if (!TextUtils.isEmpty(_ip) && CommonUtil.isboolIP(_ip)) {
                            HttpUtil.APP_TEST_HOST = _ip;
                            Toast.makeText(LoginActivity.this, "设置成功,请重新登录", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "请正确输入IP地址", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();*/

    }

    private void userLogin() {
        final String userName = login_username_edt.getText().toString();
        final String pwd = login_pwd_edt.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getString(R.string.login_error_username_pwd)
                    , Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getString(R.string.login_error_username_pwd)
                    , Toast.LENGTH_LONG).show();
            return;
        }

        //------------test--------
        if(true) {//测试
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return;
        }


        showProgressDialog(null,
                getString(R.string.login_progress_dialog_content));
        HttpUtil.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final LoginRspDTO rspDto = LoginService.userLogin(LoginActivity1.this, userName, pwd);
//                final LoginRspDTO rspDto = null;
                //------------------------------


                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();

                        //登录
                        /*EMClient.getInstance().login("wesley_s12345", "123456", new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                //加载群组和本地会话
                                EMClient.getInstance().groupManager().loadAllGroups();
                                EMClient.getInstance().chatManager().loadAllConversations();
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(true){  //测试
                                            Intent intent = new Intent(LoginActivity.this,ChatSingleActivity.class);

                                            startActivity(intent);
                                            return;
                                        }
                                    }
                                });

                            }

                            @Override
                            public void onError(int i, String s) {

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });*/


                        if (rspDto == null) {
                            Toast.makeText(LoginActivity1.this, getString(R.string.login_request_fail), Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if ("2000".equals(rspDto.getCode())) {


                            Toast.makeText(LoginActivity1.this,
                                    getString(R.string.login_success), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);

                            startActivity(intent);
                            //go mainActivity  add by sunyu
                        } else {
                            Toast.makeText(LoginActivity1.this, getString(R.string.login_request_fail), Toast.LENGTH_LONG).show();
                        }


                    }
                });


            }
        });


    }

}
