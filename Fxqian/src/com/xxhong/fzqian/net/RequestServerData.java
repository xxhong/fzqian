package com.xxhong.fzqian.net;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;


public class RequestServerData {
	private static FinalHttp http = new FinalHttp();
	
	/**
	 * 上传份子钱
	 * @param params
	 * @param callBack
	 */
	public static void addUserInfo(AjaxParams params,AjaxCallBack<String> callBack){
		http.post(UrlUtil.getAddUrl(), params, callBack);
	}
	/**
	 * 用户注册
	 * @param params
	 * @param callBack
	 */
	public static void userReg(AjaxParams params,AjaxCallBack<String> callBack){
		http.post(UrlUtil.getUserRegUrl(), params, callBack);
	}
	/**
	 * 用户注册
	 * @param params
	 * @param callBack
	 */
	public static void userLogin(AjaxParams params,AjaxCallBack<String> callBack){
		http.post(UrlUtil.getUserLoginUrl(), params, callBack);
	}
}
