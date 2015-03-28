package com.xxhong.fzqian.utils.domain;

public class UserInfo {
	private String id;//主键
	private String uuid; //唯一标识
	private String userName;//人名
	private String money;//钱数
	private String cause;//原因 
	private String time;//时间 
	private String desc;//描述
	private String phone;//手机号
	private String abc;//姓名首字母缩写
	private String allName;//姓名全拼
	private int userType;//用户类型   1是送入  2是送出
	private String isSync; //0未同步  1已同步
	
	public UserInfo() {
	}
	public UserInfo(String uuid, String userName, String money,
			String cause, String time, String desc, String phone,int userType, String isSync) {
		super();
		this.uuid = uuid;
		this.userName = userName;
		this.money = money;
		this.cause = cause;
		this.time = time;
		this.desc = desc;
		this.phone = phone;
		this.userType = userType;
		this.isSync = isSync;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAbc() {
		return abc;
	}
	public void setAbc(String abc) {
		this.abc = abc;
	}
	public String getAllName() {
		return allName;
	}
	public void setAllName(String allName) {
		this.allName = allName;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getIsSync() {
		return isSync;
	}
	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}
	
	
	
	
}
