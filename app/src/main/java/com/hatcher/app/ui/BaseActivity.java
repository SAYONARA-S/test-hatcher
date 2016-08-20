package com.hatcher.app.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hatcher.app.util.ViewFind;

/**
 * Created by wesley_s on 16/5/12.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener
        ,ViewFind {


    protected ProgressDialog mProgressDialog;
    protected boolean isStopRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


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

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void onClick(int viewId);
    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }

    protected void showProgressDialog(String title,String msg){
        isStopRequest = false;
//        mProgressDialog = ProgressDialog.show(this,title,msg,true,false);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
//        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);

        mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    isStopRequest = true;
                    if (mProgressDialog.isShowing()) {
                        dismissProgressDialog();
                    }
                }
                return false;
            }
        });
        mProgressDialog.show();
    }

    protected void dismissProgressDialog(){

        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }



    protected Handler mHandler = new Handler();

    @Override
    public View findViewByID(int id) {
        return findViewById(id);
    }


}
