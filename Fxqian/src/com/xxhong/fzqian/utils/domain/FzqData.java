package com.xxhong.fzqian.utils.domain;

import net.tsz.afinal.http.AjaxParams;

import com.xxhong.fzqian.FzqApp;
import com.xxhong.lib.uitl.AppInfo;

public class FzqData {

	
	
	public static AjaxParams getDefaultParams(){
		AjaxParams params =new AjaxParams();
		params.put("app_type", "android");
		try {
			params.put("client_version", AppInfo.getInstance(FzqApp.mContext).getClientVersionName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
}
