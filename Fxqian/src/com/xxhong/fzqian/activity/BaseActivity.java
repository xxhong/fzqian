package com.xxhong.fzqian.activity;

import android.os.Bundle;
import static com.xxhong.fzqian.utils.UiUtil.showToast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BaseActivity extends SherlockFragmentActivity {
	protected ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mActionBar = getSupportActionBar();
	}
	private void  setTitle(String title) {
		mActionBar.setTitle(title);
	}
}
