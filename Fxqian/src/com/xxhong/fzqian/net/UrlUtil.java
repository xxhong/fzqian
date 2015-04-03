package com.xxhong.fzqian.net;

public class UrlUtil {
	private static final String HOST="http://123.56.143.93/fenZiQian/api/";
	
	private static String getHost(){
		return HOST;
	}
	public static String getAddUrl(){
		return getHost()+"userinfo_add.php";
	}
	public static String getUserRegUrl() {

		return getHost()+"user_reg.php";
	}
	public static String getUserLoginUrl() {
		return getHost()+"user_login.php";
	}
}
