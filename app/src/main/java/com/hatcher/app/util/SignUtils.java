package com.hatcher.app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;


public class SignUtils {

	/**
	 * @Description: 取得签名
	 * @param params
	 * @return
	 * @Author：tm
	 * @CreateDate：2015年8月18日 下午3:24:20
	 * @Update:
	 * @UpdateDate:
	 * @UpdateDescription:
	 */
	public static String getSign(String[] params, String token) {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> map = new TreeMap<String, String>();
		for (String param : params) {
			if (!param.startsWith("sign=")) {
				String[] arr = param.split("=");
				map.put(arr[0], arr[1]);
			}
		}

		StringBuilder basestring = new StringBuilder();
		Set<Entry<String, String>> entrys = map.entrySet();
		for (Entry<String, String> param : entrys) {
			basestring.append(param.getKey()).append("=")
					.append(param.getValue());
		}
		basestring.append(token);



		return md5(basestring.toString());

	}

	private static  String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 签名后的请求URL
	 * 
	 * @param url
	 * @return
	 */
	public static String getSignUrl(String url, String clientId, String token) {
		long time = System.currentTimeMillis();
		// phr code
		// String clientId = "M55B721B1E4B0BF224DBDA5FB";
		// String token =
		// "TGT-3149-X01r7EsZnDMvRfs969W2aNwmysgucfNId39g5Sf4devks2rio7-cas";
		String psign = "clientId=" + clientId + "&time=" + time;

		if (url.indexOf("?") > 0) {
			url = url + "&" + psign;
		} else {
			url = url + "?" + psign;
		}
		psign = url.substring(url.indexOf("?") + 1);
		String[] params = psign.split("&");
		String sign = getSign(params, token);

		url = url + "&sign=" + sign;
		System.out.println(url);
		return url;
	}
}
