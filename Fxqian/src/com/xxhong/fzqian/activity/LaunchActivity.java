package com.xxhong.fzqian.activity;

import com.xxhong.fzqian.R;
import com.xxhong.fzqian.utils.domain.Account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class LaunchActivity extends Activity {
	RelativeLayout rlLauncher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.launcher);
		rlLauncher = (RelativeLayout) this.findViewById(R.id.rl_launcher);
		AlphaAnimation alphaAnimation = new  AlphaAnimation(1.0f, 0.0f);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
			
				if(TextUtils.isEmpty(Account.getToken())){
					Intent intent = new Intent(LaunchActivity.this,UserLoginActivity.class);
					LaunchActivity.this.startActivity(intent);
					
				}else{
					Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
					LaunchActivity.this.startActivity(intent);
				}
				LaunchActivity.this.finish();
			}
		});
		rlLauncher.setAnimation(alphaAnimation);
	}
}
