package com.hatcher.app.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by wesley_s on 16/6/14.
 */
public class AnimationUtil {

    public static  void startSplashHideAnim(final View view,int duration,Animation.AnimationListener listener){
        AlphaAnimation anim = new AlphaAnimation(1f,0.0f);
        anim.setDuration(duration);
        anim.setAnimationListener(listener);
        view.startAnimation(anim);
    }

    public static  void startSplashShowAnim(final View view,int duration,Animation.AnimationListener listener){
        AlphaAnimation anim = new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(duration);
        anim.setAnimationListener(listener);
        view.startAnimation(anim);
    }

}
