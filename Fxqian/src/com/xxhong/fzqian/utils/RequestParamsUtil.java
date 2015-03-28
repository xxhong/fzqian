package com.xxhong.fzqian.utils;

import com.lidroid.xutils.http.RequestParams;
import com.xxhong.fzqian.utils.domain.Account;
import com.xxhong.fzqian.utils.domain.FzqData;
import com.xxhong.fzqian.utils.domain.UserInfo;

public class RequestParamsUtil {

	
	public static RequestParams userInfo2params(UserInfo info) throws DesException {
		RequestParams params = FzqData.getDefaultParams();
		params.addBodyParameter("token", Account.getToken());
		params.addBodyParameter("uid", info.getUuid());
		try {
			params.addBodyParameter("uid_des", Des.decode(info.getUuid()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DesException();
		}
		params.addBodyParameter("name", info.getUserName());
		params.addBodyParameter("money", info.getMoney());
		params.addBodyParameter("user_type", info.getUserType()+"");
		params.addBodyParameter("cause", info.getCause());
		params.addBodyParameter("time", info.getTime());
		params.addBodyParameter("phone", info.getPhone());
		params.addBodyParameter("notes", info.getDesc());
		return params;
	}
}
