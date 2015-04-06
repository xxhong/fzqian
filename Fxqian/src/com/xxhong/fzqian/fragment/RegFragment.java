package com.xxhong.fzqian.fragment;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.thinkland.sdk.sms.SMSCaptcha;
import com.thinkland.sdk.util.BaseData.ResultCallBack;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.activity.MainActivity;
import com.xxhong.fzqian.net.RequestServerData;
import com.xxhong.fzqian.utils.Des;
import com.xxhong.fzqian.utils.UiUtil;
import com.xxhong.fzqian.utils.domain.Account;
import com.xxhong.fzqian.utils.domain.FzqData;
import com.xxhong.lib.uitl.XXTextUtils;

public class RegFragment extends BaseFragment implements OnClickListener {

	private EditText etPhoneNum, etPassword, etVcode;
	private Button btGetVcode, btVerityVcode;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.reg_vcode_view, null);
		initView(view);
		initListeners(view);
		return view;
	}

	private void initListeners(View view) {
		btGetVcode.setOnClickListener(this);
		btVerityVcode.setOnClickListener(this);
	}

	private void initView(View view) {
		etPhoneNum = (EditText) view.findViewById(R.id.et_phone_num);
		etVcode = (EditText) view.findViewById(R.id.et_vcode);
		etPassword = (EditText) view.findViewById(R.id.et_password_reg);
		btGetVcode = (Button) view.findViewById(R.id.bt_get_vcode);
		btVerityVcode = (Button) view.findViewById(R.id.bt_verify_vcode);
	}

	String phoneNum;
	SMSCaptcha smsCaptcha;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_get_vcode:
			smsCaptcha = SMSCaptcha.getInstance();
			phoneNum = etPhoneNum.getText().toString().trim();
			if (!XXTextUtils.isPhoneNum(phoneNum)) {
				UiUtil.showToast("请输入正确手机号");
				return;
			}
			smsCaptcha.sendCaptcha(phoneNum, new ResultCallBack() {

				@Override
				public void onResult(int code, String reason, String result) {
					if(code==0){
						UiUtil.showToast("验证码已发送，请注意查收");
					}else{
						UiUtil.showToast(reason+result);
					}
				}
			});

			break;
		case R.id.bt_verify_vcode:
//			String vcode = etVcode.getText().toString().trim();
//			// 1、验证验证码
//			smsCaptcha = SMSCaptcha.getInstance();
//			smsCaptcha.commitCaptcha(phoneNum, vcode, new ResultCallBack() {
//
//				@Override
//				public void onResult(int code, String reason, String result) {
//					// TODO Auto-generated method stub
//					Log.i("xxhong", reason+result);
//					if (code == 0) {// 成功
//						UiUtil.showToast(reason);
//					}else{
//						UiUtil.showToast(reason);
//					}
//				}
//			});
			phoneNum = etPhoneNum.getText().toString().trim();
			String password = etPassword.getText().toString().trim();
			AjaxParams params = FzqData.getDefaultParams();
			params.put("user_name", phoneNum);
			try {
				params.put("client_key", Des.encode(phoneNum));
			} catch (Exception e) {
				e.printStackTrace();
			}
			params.put("user_type", "1");
			params.put("vcode", password);
			RequestServerData.userReg(params, new AjaxCallBack<String>() {

				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					super.onStart();
				}

				@Override
				public void onSuccess(String t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
					try {
						JSONObject jobj = new JSONObject(t);
						String code = jobj.getString("result_code");
						String desc = jobj.getString("result_desc");
						if ("0".equals(code)) {
							JSONObject jresult = jobj.getJSONObject("result");
							String token = jresult.getString("token");
							Account.saveToken(token);
							UiUtil.showToast("注册成功");
							goToMainUI();
						} else {
							UiUtil.showToast(desc);
						}

					} catch (Exception e) {
						UiUtil.showToast("注册失败" + e.toString());
					}
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);
				}
				
				
			});
			
			break;
		default:
			break;
		}
	}

	private void goToMainUI() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		getActivity().startActivity(intent);
	}

}
