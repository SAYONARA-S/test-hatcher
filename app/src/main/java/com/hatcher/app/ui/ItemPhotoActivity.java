package com.hatcher.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.hatcher.app.R;
import com.hatcher.app.util.Options;
import com.hatcher.app.view.TouchImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;


public class ItemPhotoActivity extends Activity {

	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();
	
	PointF moveP = new PointF();
	private static ItemPhotoActivity mInstance;
	
	private ArrayList<View> listViews = null;
	private ViewPager pager;
	private MyPageAdapter adapter;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	public static List<Bitmap> bitmap = new ArrayList<Bitmap>();
	public ArrayList<String> urllist = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_item);
		options = Options.getListOptions(2);
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		mInstance = this;
		pager = (ViewPager) findViewById(R.id.viewpager);
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID", 0);
		urllist = intent.getStringArrayListExtra("urllist");
		for (int i = 0; i < urllist.size(); i++) {
			initListViews(urllist.get(i));//
		}
		pager.setOnPageChangeListener(pageChangeListener);
		adapter = new MyPageAdapter(listViews);// 构造adapter
		pager.setAdapter(adapter);// 设置适配器
		pager.setCurrentItem(id);
	}
	
	public static ItemPhotoActivity Instance()
	{
		return mInstance;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		for (int i = 0; i < bitmap.size(); i++) {
//			bitmap.get(i).recycle();
//			bitmap.remove(i);
//		}
		super.onDestroy();
	}

	private void initListViews(String bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		TouchImageView img = new TouchImageView(this);// 构造textView对象
		imageLoader.displayImage(bm, img, options);
		img.setBackgroundColor(0xff000000);
		img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		
		img.setMaxZoom(4f);
		listViews.add(img);// 添加view
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
		// count = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

		}
	};

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter(ArrayList<View> listViews) {// 构造函数
															// 初始化viewpager的时候给的一个页面
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {// 返回数量
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
