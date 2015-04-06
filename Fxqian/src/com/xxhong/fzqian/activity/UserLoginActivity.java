package com.xxhong.fzqian.activity;

import com.xxhong.fzqian.R;
import com.xxhong.fzqian.fragment.LoginFragment;
import com.xxhong.fzqian.fragment.RegFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserLoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.login);
		if(arg0==null)
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new LoginFragment()).commit();
	}


}
