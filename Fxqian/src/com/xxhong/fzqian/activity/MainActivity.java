package com.xxhong.fzqian.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.R.dimen;
import com.xxhong.fzqian.R.drawable;
import com.xxhong.fzqian.R.id;
import com.xxhong.fzqian.R.layout;
import com.xxhong.fzqian.R.menu;
import com.xxhong.fzqian.fragment.InComeFragment;
import com.xxhong.fzqian.fragment.MenuFragment;
import com.xxhong.fzqian.fragment.MenuFragment.OnItemClickListener;
import com.xxhong.fzqian.fragment.PayOutFragment;
import com.xxhong.fzqian.fragment.SettingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener {
	private SlidingMenu mSlidingMenu;
	private Menu mMenu;
	private FragmentManager mFragmentManager;
	private ActionBar mActionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, new InComeFragment()).commit();
		init();
		initSlidingMenu();
		initActionBarAndListener();
	}

	private void init() {
		mFragmentManager = getSupportFragmentManager();
		mActionBar = this.getSupportActionBar();
	}

	private void initSlidingMenu() {
		mSlidingMenu = new SlidingMenu(this);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.content_frame);
		MenuFragment menuFragment = new MenuFragment(mSlidingMenu);
		menuFragment.setOnItemClickLister(new OnItemClickListener() {

			@Override
			public void onClick(int position) {
				if (position == 0) {
					FragmentTransaction transaction = mFragmentManager
							.beginTransaction();
					transaction.replace(R.id.container, new InComeFragment());
					transaction.commit();
				} else if (position == 1) {
					FragmentTransaction transaction = mFragmentManager
							.beginTransaction();
					transaction.replace(R.id.container, new PayOutFragment());
					transaction.commit();
				} else {
					FragmentTransaction transaction = mFragmentManager
							.beginTransaction();
					transaction.replace(R.id.container, new SettingFragment());
					transaction.commit();
				}
			}
		});
		mFragmentManager.beginTransaction()
				.replace(R.id.content_frame, menuFragment).commit();

	}

	private void initActionBarAndListener() {
		mActionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			mSlidingMenu.toggle();
			break;
		case R.id.action_add:
		    Intent intent = new Intent(this, AddFzqActivity.class);
		    startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

	}
}
