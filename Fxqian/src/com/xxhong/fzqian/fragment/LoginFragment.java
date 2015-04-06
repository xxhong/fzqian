package com.xxhong.fzqian.fragment;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xxhong.fzqian.R;
import com.xxhong.fzqian.activity.MainActivity;
import com.xxhong.fzqian.net.RequestServerData;
import com.xxhong.fzqian.utils.Des;
import com.xxhong.fzqian.utils.UiUtil;
import com.xxhong.fzqian.utils.domain.Account;
import com.xxhong.fzqian.utils.domain.FzqData;

public class LoginFragment extends BaseFragment implements OnClickListener {

	private EditText etUserName,etPassword;
	private Button btLogin,btReg,btSkip;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_view, null);
		initView(view);
		initListeners();
		return view;
	}
	private void initView(View view) {
		etUserName = (EditText) view.findViewById(R.id.et_username);
		etPassword = (EditText) view.findViewById(R.id.et_password);
		btLogin =  (Button) view.findViewById(R.id.bt_login);
		btReg =  (Button) view.findViewById(R.id.bt_reg);
		btSkip =  (Button)view.findViewById(R.id.bt_skip);
	}
	private void initListeners() {
		btLogin.setOnClickListener(this);
		btReg.setOnClickListener(this);
		btSkip.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			String userName = etUserName.getText().toString().trim();
			String Password = etPassword.getText().toString().trim();
			AjaxParams params = FzqData.getDefaultParams();
			params.put("login_type", "1");
			params.put("user_name", userName);
			params.put("password", Password);
			try {
				params.put("client_key", Des.encode(userName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestServerData.userLogin(params, new AjaxCallBack<String>() {

				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					super.onStart();
				}

				@Override
				public void onSuccess(String t) {
					super.onSuccess(t);
					try {
						JSONObject jobj = new JSONObject(t);
						String result_code = jobj.getString("result_code");
						String desc = jobj.getString("result_desc");
						if("0".equals(result_code)){
							JSONObject resultData = jobj.getJSONObject("result");
							String token = resultData.getString("token");
							Account.saveToken(token);
							//进入主界面 
						}else{
							UiUtil.showToast("登录失败"+desc);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);
				}
				
			});
			
			
			
			
			break;
		case R.id.bt_reg:
			FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
			beginTransaction.replace(android.R.id.content, new RegFragment());
			beginTransaction.addToBackStack("RegFragment");
			beginTransaction.commit();
			break;
		case R.id.bt_skip:
			goToMainUI();
			break;

		default:
			break;
		}
	}
	
	public void goToMainUI(){
		
		Intent intent = new Intent(getActivity(),MainActivity.class);
		
		getActivity().startActivity(intent);
		
		getActivity().finish();
		
	}

	
	
	
}

