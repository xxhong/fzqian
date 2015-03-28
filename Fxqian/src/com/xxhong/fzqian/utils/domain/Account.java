package com.xxhong.fzqian.utils.domain;

import android.text.TextUtils;

import com.xxhong.lib.uitl.PersistTool;
/**
 * 用户信息
 * @author xuxihong
 *
 */
public class Account {
	
	
	
	public static Account mAccount = new Account();
	public boolean isLogin(){
		if(!TextUtils.isEmpty(getToken())){
			return true;
		}else{
			return false;
		}
	}
	public static Account getInstance(){
		return mAccount;
	}
	public static void saveUserName(String userName){
		PersistTool.saveString("userName", userName);
	}
	public static void getUserName(){
		PersistTool.getString("userName", "");
	}
	public static void savePassWord(String pwd){
		PersistTool.saveString("password", pwd);
	}
	public static String getPassWord(){
		return PersistTool.getString("password", "");
	}
	
	public static void saveUserType(String userType){
		PersistTool.saveString("userType", userType);
	}
	public static String getuserType(){
		return PersistTool.getString("userType", "");
	}
	
	
	public static void saveToken(String token){
		PersistTool.saveString("token", token);
	}
	public static String getToken(){
		return PersistTool.getString("token", "");
	}
}
