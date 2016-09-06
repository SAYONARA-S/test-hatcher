package com.hatcher.app.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.hatcher.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Options {
	
	public static ArrayList<String> blackList = new ArrayList<String>();
	public static final int OPTION_HEAD_TYPE = 1;
	public static final int OPTION_HOME_AD_TYPE = 2;
	public static final int OPTION_HOME_LEFT_TYPE = 3;
	public static final int OPTION_HOME_RIGHT_TYPE = 4;
	public static final int OPTION_WHITE_BG_TYPE = 5;
	public static final int OPTION_MESSAGE_TRIP_STOP = 6;

	public static final String SHARE_SINGLE_TRIP = "SHARE_SINGLE_TRIP";
	public static final String SHARE_TRIPS = "SHARE_TRIPS";
	public static final String SHARE_INVITE = "SHARE_INVITE";
	public static final String SHARE_NPI = "SHARE_NPI";

	public static final int TYPE_AUTO = 1;
	public static final int TYPE_BRIGHT = 2;
	public static final int TYPE_NIGHT = 3;
	public static final int TYPE_SOUND = 4;

	public static final int SAFE_TYPE_1 = 1;
	public static final int SAFE_TYPE_2 = 2;
	public static final int SAFE_TYPE_3 = 3;
	public static final int SAFE_TYPE_4 = 4;
	public static final int SAFE_TYPE_5 = 5;
	public static final int SAFE_TYPE_6 = 6;
	public static final int SAFE_TYPE_7 = 7;
	public static final int SAFE_TYPE_0 = 0;

	public static final int MESSAGE_TRIP = 0;
	public static final int MESSAGE_ACTIVITY = 1;
	public static final int MESSAGE_READ = 0;
	public static final int MESSAGE_UNREAD = 1;

	/** 新闻列表中用到的图片加载配置 */
	public static DisplayImageOptions getListOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// // 设置图片在下载期间显示的图片
				.showImageOnLoading(R.drawable.login_logo)
				// // 设置图片Uri为空或是错误的时候显示的图片
				.showImageForEmptyUri(R.drawable.login_logo)
				// // 设置图片加载/解码过程中错误时候显示的图片
				.showImageOnFail(R.drawable.login_logo).cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				.considerExifParams(true)
				// 设置图片下载前的延迟
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// 。preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				 .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
				.displayer(new FadeInBitmapDisplayer(100))// 淡入
				.build();
		return options;
	}

	public static DisplayImageOptions getListOptions(int type) {
		int bg = 0;
		if (type == OPTION_HEAD_TYPE) {
			bg = R.drawable.login_logo;
		}
		else if (type == OPTION_HOME_AD_TYPE) {
			bg = R.drawable.login_logo;
		}
		else if (type == OPTION_HOME_LEFT_TYPE) {
			bg = R.drawable.login_logo;
		}
		else if (type == OPTION_HOME_RIGHT_TYPE) {
			bg = R.drawable.login_logo;
		}
		else if (type == OPTION_WHITE_BG_TYPE) {
			bg = R.drawable.login_logo;
		}
		else if (type == OPTION_MESSAGE_TRIP_STOP)
		{
			bg = R.drawable.login_logo;
		}

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// // 设置图片在下载期间显示的图片
				.showImageOnLoading(bg)
				// // 设置图片Uri为空或是错误的时候显示的图片
				.showImageForEmptyUri(bg)
				// // 设置图片加载/解码过程中错误时候显示的图片
				.showImageOnFail(bg).cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				
				
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_8888)// 设置图片的解码类型
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				.considerExifParams(true)
				// 设置图片下载前的延迟
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// 。preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//				 .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
				.displayer(new FadeInBitmapDisplayer(100))// 淡入
				.build();
		return options;
	}

	public static String getFloatToString(Float str)
	{
		try
		{
			DecimalFormat df1 = new DecimalFormat("0.0");
			String strFinal = df1.format(str);
			return strFinal;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "" + str;
		}
	}

	public static String getLongToDate(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static String getLongToDate2(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static String getLongToDate3(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static String getLongToDate4(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static String getLongToDateByCN(long date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			Date date1 = new Date(date);
			String time = sdf.format(date1).toString();
			return time;
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public static String getDateStr(Context mContext,long dateContent)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(dateContent);
//    	try {
//			date= sdf.parse(dateStr);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2=new SimpleDateFormat("M月d日");
		String strTime=sdf1.format(date);
		String strTime1=sdf2.format(date);
		Date curDate = new Date();
		String curDateStr = sdf.format(date);
		if (date.getDate() == curDate.getDate()) {
			curDateStr = "今天 " + strTime;
		}
		else if ((curDate.getDate() - date.getDate()) == 1) {
			curDateStr = "昨天 " + strTime;
		}
		else {
			curDateStr = strTime1 + "，" + strTime;
		}

		return curDateStr;
	}

	public static String getDateStrForMessageMain(Context mContext,long dateContent)
	{
		Date date = new Date(dateContent);
		SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd");
		String strTime=sdf1.format(date);
		String strTime1=sdf2.format(date);
		Date curDate = new Date();
		String curDateStr = "";
		if (date.getDate() == curDate.getDate()) {
			curDateStr = strTime;
		}
		else if ((curDate.getDate() - date.getDate()) == 1) {
			curDateStr = "昨天";
		}
		else {
			curDateStr = strTime1;
		}

		return curDateStr;
	}

	public static String getDateStrForMessageList(Context mContext,long dateContent)
	{
		Date date = new Date(dateContent);
		SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd");
		String strTime=sdf1.format(date);
		String strTime1=sdf2.format(date);
		Date curDate = new Date();
		String curDateStr = "";
		if (date.getDate() == curDate.getDate()) {
			curDateStr = strTime;
		}
		else if ((curDate.getDate() - date.getDate()) == 1) {
			curDateStr = "昨天  " + strTime;
		}
		else {
			curDateStr = strTime1 + "  " + strTime;
		}

		return curDateStr;
	}


	public static void setShareInfo(Context mContext)
	{
		LoginConfig loginConfig = LoginConfig.getInstance();
		loginConfig.loadConfig(mContext, Constants.LOGIN_CONFIG);

		Date curDate = new Date();
		Log.e("xxp","curDate "+getLongToDateByCN1(curDate.getTime()));
		Log.e("xxp","getLastShareDate "+getLongToDateByCN1(loginConfig.getLastShareDate()));

		if (!getLongToDateByCN1(curDate.getTime()).equals(getLongToDateByCN1(loginConfig.getLastShareDate())))
		{
			loginConfig.setCurShareNum(0);
			loginConfig.setLastShareDate(curDate.getTime());
		}
		int curNum = loginConfig.getCurShareNum();
		curNum++;
		loginConfig.setCurShareNum(curNum);
		loginConfig.setLastShareDate(curDate.getTime());
		Log.e("xxp","loginConfig.getLastShareDate "+loginConfig.getLastShareDate());
		Log.e("xxp","loginConfig.getCurShareNum "+loginConfig.getCurShareNum());
		Log.e("xxp","loginConfig.getMissionShareNum "+loginConfig.getMissionShareNum());
	}

	public static String getLongToDateByCN1(long date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
			Date date1 = new Date(date);
			String time = sdf.format(date1).toString();
			return time;
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	
	public static String getLongToDateForStyle(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static String getLongToDateForStyle1(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date1 = new Date(date);
		String time = sdf.format(date1).toString();
		return time;
	}

	public static int getSafeTypeToInt(int safeType) {
		int behavior = safeType;
		/**
		 * 返回急加速、急减速、急转弯、转弯急加速
		 * @return 00000000： 各位默认为0
		 *         右数第1位为1：急加速
		 *         右数第2位为1：急减速
		 *         右数第3位为1：急转弯
		 *         右数第4位为1：转弯急加速
		 *         右数第5位为1：好的转弯
		 *         右数第6位为1：平稳启动
		 *         右数第7位为1：平稳停车
		 */
		if ((behavior & 1) == 1) {
			return SAFE_TYPE_1;
		}
		if ((behavior & (1 << 1)) == (1 << 1)) {
			return SAFE_TYPE_2;
		}
		if ((behavior & (1 << 2)) == (1 << 2)) {
			return SAFE_TYPE_3;
		}
		if ((behavior & (1 << 3)) == (1 << 3)) {
			return SAFE_TYPE_4;
		}
		if ((behavior & (1 << 4)) == (1 << 4)) {
			return SAFE_TYPE_5;
		}
		if ((behavior & (1 << 5)) == (1 << 5)) {
			return SAFE_TYPE_6;
		}
		if ((behavior & (1 << 6)) == (1 << 6)) {
			return SAFE_TYPE_7;
		}
		return SAFE_TYPE_0;
	}


	public static long[] printDifference(long startDate, long endDate){

		//milliseconds
		long different = endDate - startDate;

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf(
				"%d days, %d hours, %d minutes, %d seconds%n",
				elapsedDays,
				elapsedHours, elapsedMinutes, elapsedSeconds);

		long[] times = new long[4];
		times[0] = elapsedDays;
		times[1] = elapsedHours;
		times[2] = elapsedMinutes;
		times[3] = elapsedSeconds;

		return times;

	}

	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛",
			"虎", "兔", "龙", "蛇", "马", "羊" };

	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座",
			"金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };

	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22,
			23, 23, 23, 23, 22, 22 };

	/**
	 * 根据日期获取生肖
	 * 
	 * @return
	 */
	public static String getZodica(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return zodiacArr[cal.get(Calendar.YEAR) % 12];
	}

	/**
	 * 根据日期获取星座
	 * 
	 * @return
	 */
	public static String getConstellation(Date date) {
		if (date == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < constellationEdgeDay[month]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		// default to return 魔羯
		return constellationArr[11];
	}

	public static String getAge(Date date) {
		Date curdate = new Date();
		long day = (curdate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000)
				+ 1;
		String year = new DecimalFormat("#").format(day / 365f);
		return year;
	}

	public static String getInterval(long ExceptFinishTime) {
		String interval = "";
		long time = ExceptFinishTime - new Date().getTime();

		if (time <= 0) {
			interval = "到" + getLongToDateByCN(ExceptFinishTime) + "，";
		} else if (time / 3600000 < 24 && time / 3600000 > 0) {
			interval = "在今天，";
		} else if (time / 3600000 / 24 >= 1 && time / 3600000 / 24 < 31) {
			int d = (int) (time / 3600000 / 24);
			interval = d + "天后，";
		} else if (time / 3600000 / 24 / 30 >= 1 && time / 3600000 / 24 / 30 < 12) {
			int m = (int) (time / 3600000 / 24 / 30);
			interval = m + "个月后，";
		} else {
			int d = (int) (time / 3600000 / 24 / 30 / 12);
			interval = d + "年后，";
		}

		return interval;
	}

	private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "个月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String getRelativeDateFormat(long ExceptFinishTime) {
        long delta = new Date().getTime() - ExceptFinishTime;
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

	public static String timeStr = "";


	public static String getTimeStr() {
		return timeStr;
	}

	public static void setTimeStr(String timeStr) {
		Options.timeStr = timeStr;
	}

	public static int imgMaxNum = 9;


	public static int getImgMaxNum() {
		return imgMaxNum;
	}

	public static void setImgMaxNum(int imgMaxNum) {
		Options.imgMaxNum = imgMaxNum;
	}

	/**
	 * 以最省内存的方式读取本地资源的图片
	 */
	public static Bitmap readBitMap(Context context, int resId){
	   BitmapFactory.Options opt = new BitmapFactory.Options();
	   opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
	   opt.inPurgeable = true;
	   opt.inInputShareable = true;
	   // 获取资源图片
	   InputStream is = context.getResources().openRawResource(resId);
	   return BitmapFactory.decodeStream(is,null,opt);
	}

	/* 头像名称 */
	private static final String IMAGE_FILE_NAME = "faceImage.jpg";

	//以下是关键，原本uri返回的是file:///...来着的，android4.4返回的是content:///...
	@SuppressLint("NewApi")
	public static String getPath(final Context context, final Uri uri) {

	    final boolean isKitKat = Build.VERSION.SDK_INT >= 19;

	    // DocumentProvider
	    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
	        // ExternalStorageProvider
	        if (isExternalStorageDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            System.out.println("isExternalStorageDocument split" + split[1]);
	            System.out.println("isExternalStorageDocument uri" + Environment.getExternalStorageDirectory());

	            if ("primary".equalsIgnoreCase(type)) {
	                return Environment.getExternalStorageDirectory() + "/" + split[1];
	            }

	        }
	        // DownloadsProvider
	        else if (isDownloadsDocument(uri)) {

	            final String id = DocumentsContract.getDocumentId(uri);
	            System.out.println("isExternalStorageDocument11 split" + id);
	            final Uri contentUri = ContentUris.withAppendedId(
	                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

	            return getDataColumn(context, contentUri, null, null);
	        }
	        // MediaProvider
	        else if (isMediaDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            Uri contentUri = null;
	            if ("image".equals(type)) {
	                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	            } else if ("video".equals(type)) {
	                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	            } else if ("audio".equals(type)) {
	                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	            }

	            final String selection = "_id=?";
	            final String[] selectionArgs = new String[] {
	                    split[1]
	            };

	            System.out.println("isExternalStorageDocument12 split" + contentUri);


	            return getDataColumn(context, contentUri, selection, selectionArgs);
	        }
	    }
	    // MediaStore (and general)
	    else if ("content".equalsIgnoreCase(uri.getScheme())) {
	        // Return the remote address
	        if (isGooglePhotosUri(uri))
	            return uri.getLastPathSegment();


	        System.out.println("isExternalStorageDocument3 content" + uri);
	        Uri uri1 = Uri.parse("content:///storage/emulated/0/faceImage.jpg");
	        return getDataColumn(context, uri, null, null);
	    }
	    // File
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {

	    	System.out.println("isExternalStorageDocument4 content" + uri.getPath());
	    	System.out.println("isExternalStorageDocument4 content uri " + uri);


	        return uri.getPath();
	    }

	    return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection,
									   String[] selectionArgs) {

	    Cursor cursor = null;
	    final String column = "_data";
	    final String[] projection = {
	            column
	    };

	    try {
	        cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
	                null);
	        if (cursor != null && cursor.moveToFirst()) {
	            final int index = cursor.getColumnIndexOrThrow(column);
	            return cursor.getString(index);
	        }
	    } finally {
	        if (cursor != null)
	            cursor.close();
	    }
	    return null;
	}


	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
	    return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
	    return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
	    return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}


	public static boolean isNetworkAvailable(Activity activity)
	{
		try {
			Context context = activity.getApplicationContext();
			// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivityManager == null) {
				return false;
			} else {
				// 获取NetworkInfo对象
				NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

				if (networkInfo != null && networkInfo.length > 0) {
					for (int i = 0; i < networkInfo.length; i++) {
						System.out.println(i + "===状态===" + networkInfo[i].getState());
						System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
						// 判断当前网络状态是否为连接状态
						if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static void copyDataBasefile() {

		File fromFile = new File("/data/data/com.sensteer.app/databases/sensteersdk.db");
		File toFile = new File("/sdcard/a.db");
		Boolean rewrite = true;
		if (!fromFile.exists()) {
			return;
		}
		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(fromFile);
			FileOutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); //将内容写到新文件当中
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception ex) {

		}
	}

	public static void copyNativeDataBasefile() {

		File fromFile = new File("/data/data/com.sensteer.app/databases/sensteer.db");
		File toFile = new File("/sdcard/b.db");
		Boolean rewrite = true;
		if (!fromFile.exists()) {
			return;
		}
		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(fromFile);
			FileOutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); //将内容写到新文件当中
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception ex) {

		}
	}
}
