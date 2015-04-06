package com.xxhong.fzqian.utils;

import net.tsz.afinal.http.AjaxParams;

import com.xxhong.fzqian.utils.domain.Account;
import com.xxhong.fzqian.utils.domain.FzqData;
import com.xxhong.fzqian.utils.domain.UserInfo;

public class RequestParamsUtil {

	
	public static AjaxParams userInfo2params(UserInfo info) throws DesException {
		AjaxParams params = FzqData.getDefaultParams();
		params.put("token", Account.getToken());
		params.put("uid", info.getUuid());
		try {
			params.put("uid_des", Des.encode(info.getUuid()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DesException();
		}
		params.put("name", info.getUserName());
		params.put("money", info.getMoney());
		params.put("user_type", info.getUserType()+"");
		params.put("cause", info.getCause());
		params.put("time", info.getTime());
		params.put("phone", info.getPhone());
		params.put("notes", info.getDesc());
		return params;
	}
}
