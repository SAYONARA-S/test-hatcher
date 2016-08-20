package com.hatcher.app.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hatcher.app.util.ViewFind;

/**
 * Created by Administrator on 2016-5-5.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener,ViewFind {

    protected FragmentManager fragmentManager;
    protected ProgressDialog mProgressDialog;
    protected boolean isStopRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onResume() {
        fragmentManager = getFragmentManager();
        super.onResume();
    }

    protected void showProgressDialog(String title,String msg){
        isStopRequest = false;
//        mProgressDialog = ProgressDialog.show(this,title,msg,true,false);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
//        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);

        mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if (mProgressDialog.isShowing()) {
                        isStopRequest = true;
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

    abstract protected void initView();
    abstract protected void initData();
}
