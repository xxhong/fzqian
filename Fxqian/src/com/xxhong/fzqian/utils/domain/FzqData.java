package com.xxhong.fzqian.utils.domain;

import com.lidroid.xutils.http.RequestParams;
import com.xxhong.fzqian.FzqApp;
import com.xxhong.lib.uitl.AppInfo;

public class FzqData {

	
	
	public static RequestParams getDefaultParams(){
		RequestParams params =new RequestParams();
		params.addBodyParameter("app_type", "android");
		try {
			params.addBodyParameter("client_version", AppInfo.getInstance(FzqApp.mContext).getClientVersionName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
}
