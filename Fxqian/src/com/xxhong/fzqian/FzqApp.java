package com.xxhong.fzqian;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.xxhong.lib.uitl.PersistTool;

import android.app.Application;
import android.content.Context;

public class FzqApp extends Application{
	public static Context mContext;
	public static DbUtils mDb;
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		
		//初始化讯飞
		SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5513d7d9");
		
		PersistTool.init(this);
		initDB();
	}
	private void initDB() {
		mDb = DbUtils.create(this, "fzqian.db", 1, new DbUpgradeListener() {
			
			@Override
			public void onUpgrade(DbUtils arg0, int arg1, int arg2) {
				
			}
		});
	}
}
