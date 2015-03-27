package com.xxhong.fzqian.utils;

import com.xxhong.fzqian.FzqApp;

import android.widget.Toast;

public class UiUtil {

	
	public static void showToast(String text){
		Toast.makeText(FzqApp.mContext, text, 0).show();
	}
}
