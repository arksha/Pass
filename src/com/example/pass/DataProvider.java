package com.example.pass;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataProvider {
	public static final String GAMEDATA = "playdata";
	public static final String STORE_KEY_UPDATEDATE = "update_date";

	public static boolean getBoolean(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}

	public static void saveBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putBoolean(key, value);
		ed.commit();
	}

	public static String getString(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}
	
	public static void saveString(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putString(key, value);
		ed.commit();
	}

	public static int getInt(Context context, String key) {
		int date = 0;
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
//		if(key.equals("wifiLinkedCode")){
//			date = -100;
//		}
		return sp.getInt(key,date );
	}

	public static void saveInt(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putInt(key, value);
		ed.commit();
	}

	public static long getLong(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		return sp.getLong(key, 0);
	}

	public static void saveLong(Context context, String key, long value) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putLong(key, value);
		ed.commit();
	}

	public static void saveGamaData(Context context) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.commit();
	}

	public static void getGamaData(Context context) {
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);		
	}
	
	public static String getStoreUpdateDate(Context context){
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		return sp.getString(STORE_KEY_UPDATEDATE, "");
	}
	
	public static void saveStoreUpdateDate(Context context){
		SharedPreferences sp = context.getSharedPreferences(GAMEDATA,
				Context.MODE_PRIVATE);
		Editor ed = sp.edit();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		ed.putString(STORE_KEY_UPDATEDATE, dateStr);
		ed.commit();
	}
	
	public static boolean isTodayUpdate(Context context){
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(today.equals(getStoreUpdateDate(context))){
			return true;
		}
		return false;
	}

}
