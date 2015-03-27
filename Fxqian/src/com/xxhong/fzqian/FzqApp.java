package com.xxhong.fzqian;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import android.app.Application;
import android.content.Context;

public class FzqApp extends Application{
	public static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
	
		mContext = this;
		//初始化讯飞
		SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5513d7d9");
	}
}
