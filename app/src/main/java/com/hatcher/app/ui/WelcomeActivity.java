package com.hatcher.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity {
	private AlphaAnimation start_anima;
//	private LoginConfig loginConfig = LoginConfig.getInstance();
	
	private ViewPager mViewPager;
	private LinearLayout iconPager;
	private ArrayList<View> mViewList = new ArrayList<View>();
	ImageView textView;
	ImageView[] textViews;
	View view;
	private TextView button;
	private Context mContext;
	private LoginConfig loginConfig = LoginConfig.getInstance();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
		view = View.inflate(this, R.layout.welcome, null);
		
		setContentView(view);
		initView();
		initData();
	}

	protected void initView()
	{
		
	}
	
	PagerAdapter mPagerAdapter = new PagerAdapter() {
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViewList.size();
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position,
								Object object) {
			container.removeView(mViewList.get(position));
		}

		@Override
		public int getItemPosition(Object object) {

			return super.getItemPosition(object);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mViewList.get(position));
			return mViewList.get(position);
		}
	};

	protected void initData() {
		start_anima = new AlphaAnimation(1.0f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if (loginConfig.getAccessToken() != null && !loginConfig.getAccessToken().equals("")) {
					startActivity(new Intent(mContext, MainActivity.class));
				}
				else {
//					if (loginConfig.getWelcomeToGuide())
//					{
//						loginConfig.setWelcomeToGuide(false);
//						Intent intent = new Intent(mContext,GuidePagesActivity.class);
//						intent.putExtra("wheresfrom","welcome");
//						startActivity(intent);
//					}
				}
				redirectTo();
			}
		});
	}

	@Override
	protected void onClick(int viewId) {

	}

	private void redirectTo() {
		finish();
	}
	
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出",
						Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				
				finish();
			}
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
