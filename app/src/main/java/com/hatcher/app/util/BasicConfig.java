package com.hatcher.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import biz.source_code.base64Coder.Base64Coder;

public abstract class BasicConfig {
	
	private boolean status = false;

	private SharedPreferences sp;

	public void loadConfig(Context ctx, String name) {
		this.sp = ctx.getSharedPreferences(name, Activity.MODE_PRIVATE);
		this.status = sp.getBoolean("status", false);
		init();
	}
	
	protected abstract void init();
	
	protected SharedPreferences.Editor prepare() {
		if (!status) {
			setStatus(true);
		}
		return this.sp.edit();
	}

	public SharedPreferences getSp() {
		return sp;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
		this.sp.edit().putBoolean("status", status).commit();
	}
	
	public void saveObject(String key, Object obj) {
		ByteArrayOutputStream toByte = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(toByte);
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String valueBase64 = new String(Base64Coder.encode(toByte.toByteArray()));
		this.prepare().putString(key, valueBase64).commit();
	}
	
	public Object getObject(String key) {
		String obj = this.sp.getString(key, null);
		if (obj != null) {
			byte[] base64Bytes = Base64Coder.decode(obj);
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public void clear() {
		this.sp.edit().clear().commit();
	}
}
