package com.xxhong.fzqian.net;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class RequestServerData {
	private static HttpUtils http = new HttpUtils();
	
	/**
	 * 上传份子钱
	 * @param params
	 * @param callBack
	 */
	public static void addUserInfo(RequestParams params,RequestCallBack<String>callBack){
		http.send(HttpRequest.HttpMethod.POST, UrlUtil.getAddUrl(), params, callBack);
	}
	/**
	 * 用户注册
	 * @param params
	 * @param callBack
	 */
	public static void userReg(RequestParams params,RequestCallBack<String>callBack){
		http.send(HttpRequest.HttpMethod.POST, UrlUtil.getUserRegUrl(), params, callBack);
	}
}
