package com.hatcher.app.testpic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hatcher.app.R;
import com.hatcher.app.ui.BaseActivity;
import com.hatcher.app.ui.ModifyPasswordActivity;
import com.hatcher.app.ui.UpdateVersionActivity;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.Constants;
import com.hatcher.app.util.LoginConfig;
import com.hatcher.app.util.Options;
import com.hatcher.app.util.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;

public class PublishedActivity extends BaseActivity implements OnClickListener {

	@ViewInject
	private RelativeLayout back_layout;
	@ViewInject
	private RelativeLayout my_page_layout;
	@ViewInject
	private RelativeLayout my_photo_layout;
	@ViewInject
	private RelativeLayout search_layout;
	@ViewInject
	private EditText edit_view;
	@ViewInject
	private GridView noScrollgridview;
	@ViewInject
	private TextView my_page_text;
	@ViewInject
	private TextView my_photo_text;
	@ViewInject
	private ImageView my_page_right;
	@ViewInject
	private ImageView my_photo_right;
	private GridAdapter adapter;

	private Context mContext;
	private Activity activity;
	private LoginConfig loginConfig = LoginConfig.getInstance();
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private final static int SCANNIN_GREQUEST_CODE = 5;
	
	private PopupWindow popupWindow;
	private int type = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_selectimg);
		mContext = this;
		activity = this;
		loginConfig.loadConfig(this, Constants.LOGIN_CONFIG);
		imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
		options = Options.getListOptions();
		CommonUtil.initViewInject(this, PublishedActivity.class, this);
		
		Bimp.bmp.clear();
		Bimp.drr.clear();
		Bimp.max = 0;
		FileUtils.deleteDir();
		Options.setImgMaxNum(4);

		initView();
		initData();
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		Bimp.bmp.clear();
//		Bimp.drr.clear();
//		Bimp.max = 0;
//		FileUtils.deleteDir();
		super.onDestroy();
	}

	@Override
	protected void initView() {
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				if (arg2 == Bimp.bmp.size()) {
					popupWindow = new PopupWindows(PublishedActivity.this, noScrollgridview);
				} else {
					Intent intent = new Intent(PublishedActivity.this, PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});
		my_page_layout.setOnClickListener(this);
		my_photo_layout.setOnClickListener(this);
		back_layout.setOnClickListener(this);
		search_layout.setOnClickListener(this);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void onClick(int viewId) {
		switch (viewId) {

			case R.id.back_layout:
				finish();
				break;
			case R.id.search_layout:

				break;
			case R.id.my_page_layout:
				type = 0;
				my_page_text.setTextColor(mContext.getResources().getColor(R.color.black));
				my_photo_text.setTextColor(mContext.getResources().getColor(R.color.nav_text_gray));
				my_page_right.setImageResource(R.drawable.icon_release_select);
				my_photo_right.setImageResource(R.drawable.icon_release_normal);
				break;
			case R.id.my_photo_layout:
				type = 1;
				my_page_text.setTextColor(mContext.getResources().getColor(R.color.nav_text_gray));
				my_photo_text.setTextColor(mContext.getResources().getColor(R.color.black));
				my_page_right.setImageResource(R.drawable.icon_release_normal);
				my_photo_right.setImageResource(R.drawable.icon_release_select);
				break;

			default:
				break;
		}
	}


	public void Init() {
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				if (arg2 == Bimp.bmp.size()) {
					popupWindow = new PopupWindows(PublishedActivity.this, noScrollgridview);
				} else {
					Intent intent = new Intent(PublishedActivity.this, PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});
	}

	@SuppressLint("HandlerLeak")
	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater; // 视图容器
		private int selectedPosition = -1;// 选中的位置
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			if (Bimp.bmp.size() < 4) {
				return Bimp.bmp.size() + 1;
			} else {
				return Bimp.bmp.size();
			}
		}

		public Object getItem(int arg0) {

			return null;
		}

		public long getItemId(int arg0) {

			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		/**
		 * ListView Item设置
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int coord = position;
			ViewHolder holder = null;
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));
				if (position == 4) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.bmp.get(position));
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.drr.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max);
								Bitmap bm = Bimp.revitionImageSize(path);
								Bimp.bmp.add(bm);
								String newStr = path.substring(
										path.lastIndexOf("/") + 1,
										path.lastIndexOf("."));
								FileUtils.saveBitmap(bm, "" + newStr);
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}).start();
		}
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	protected void onRestart() {
		adapter.update();
		super.onRestart();
	}

	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View
					.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(false);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);

			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					photo();
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(PublishedActivity.this, TestPicActivity.class);
					startActivity(intent);
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});

		}
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (popupWindow != null && popupWindow.isShowing()) {

			popupWindow.dismiss();

			popupWindow = null;
		}

		return super.onTouchEvent(event);
	}


	private static final int TAKE_PICTURE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	private String path = "";
	private Cursor cursor;
	private String imageName = "";

	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		imageName = String.valueOf(System.currentTimeMillis());
		if (hasSdcard()) {
			openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
					.fromFile(new File(Environment.getExternalStorageDirectory(),
							imageName + ".jpg")));
		}

//		File file = new File(Environment.getExternalStorageDirectory()
//				+ "/myimage/", String.valueOf(System.currentTimeMillis())
//				+ ".jpg");
//		path = file.getPath();
//		Uri imageUri = Uri.fromFile(file);
//		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	private boolean hasSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}
	
	public void startPhotoZoom(Uri uri) {
		if (uri == null) {
			Log.i("tag", "The uri is not exist.");
		}
		Intent intent = new Intent("com.android.camera.action.CROP");
		
		if (android.os.Build.VERSION.SDK_INT >= 19) {
			String url=Options.getPath(activity,uri);
			intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
		}else{
			intent.setDataAndType(uri, "image/*");
		}
		
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_CANCELED) {
			switch (requestCode) {
			case TAKE_PICTURE:
				
				if (hasSdcard()) {
					File tempFile = new File(Environment.getExternalStorageDirectory(),
							imageName + ".jpg");
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					// showToast("未找到存储卡，无法存储照片！");
	
				}
				
				break;
				
			case RESULT_REQUEST_CODE:
				
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap photo = extras.getParcelable("data");
					String a = MediaStore.Images.Media.insertImage(getContentResolver(), photo, null, null);
					Uri uri = Uri.parse(a);
					
					String[] pojo = { MediaStore.Images.Media.DATA };
					cursor = managedQuery(uri, pojo, null, null, null);
					if (cursor != null) {
						int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
						cursor.moveToFirst();
						path = cursor.getString(columnIndex);
						// cursor.close();
					}
				}
				
				if (Bimp.drr.size() < 4 && resultCode == -1) {
					Bimp.drr.add(path);
				}
				break;
			}
		}
	}
}
