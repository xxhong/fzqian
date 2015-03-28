package com.xxhong.fzqian.net;

public class UrlUtil {
	private static final String HOST="http://123.56.143.93";
	
	private static String getHost(){
		return HOST;
	}
	public static String getAddUrl(){
		return getHost()+"/fzq/userinfo_add.php";
	}
}
