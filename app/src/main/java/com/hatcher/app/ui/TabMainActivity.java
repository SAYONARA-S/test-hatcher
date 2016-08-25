package com.hatcher.app.ui;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.ui.fragment.FunctionFragment;
import com.hatcher.app.ui.fragment.HomeFragment;
import com.hatcher.app.ui.fragment.IMFragment;
import com.hatcher.app.ui.fragment.MyFragment;
import com.hatcher.app.ui.fragment.NewsListFragment;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.view.indicator.Indicator;
import com.hatcher.app.view.indicator.IndicatorViewPager;
import com.hatcher.app.view.viewpager.SViewPager;


public class TabMainActivity extends FragmentActivity {
	private IndicatorViewPager indicatorViewPager;
	private static TabMainActivity mSelf;
	private Context mContext;

	private LoginConfig loginConfig = LoginConfig.getInstance();
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tabmain);
		mSelf = this;
		mContext = mSelf;
		loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);


		SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		// 禁止viewpager的滑动事件
		viewPager.setCanScroll(false);
		// 设置viewpager保留界面不重新加载的页面数量
		viewPager.setOffscreenPageLimit(4);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public static TabMainActivity getInstance ()
	{
//		if (mSelf == null)
//			mSelf = new TabMainActivity();
		return mSelf;
	}

	private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
		private String[] tabNames = { "首页", "聊天", "功能", "我的" };
		private int[] tabIcons = { R.drawable.selector_main1,
				R.drawable.selector_main2, R.drawable.selector_main3,
				R.drawable.selector_main4};
		private LayoutInflater inflater;

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			inflater = LayoutInflater.from(getApplicationContext());
		}

		@Override
		public int getCount() {
			return tabNames.length;
		}

		@Override
		public View getViewForTab(int position, View convertView,
								  ViewGroup container) {
			if (convertView == null) {
				convertView = (ImageView) inflater.inflate(R.layout.tab_main,
						container, false);
			}
			ImageView imageView = (ImageView) convertView;
			imageView.setImageResource(tabIcons[position]);
			return imageView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				HomeFragment mainFragment = new HomeFragment();
				Bundle bundle = new Bundle();
				mainFragment.setArguments(bundle);
				fragment = mainFragment;
				break;
			case 1:
				IMFragment mainFragment1 = new IMFragment();
				Bundle bundle1 = new Bundle();
				mainFragment1.setArguments(bundle1);
				fragment = mainFragment1;
				break;
			case 2:
				FunctionFragment mainFragment3 = new FunctionFragment();
				Bundle bundle3 = new Bundle();
				mainFragment3.setArguments(bundle3);
				fragment = mainFragment3;
				break;
			case 3:
				MyFragment mainFragment4 = new MyFragment();
				Bundle bundle4 = new Bundle();
				mainFragment4.setArguments(bundle4);
				fragment = mainFragment4;
				break;

			default:
				break;
			}
			return fragment;
		}
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
				mSelf = null;
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
