package com.xxhong.fxqian;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.xxhong.fxqian.fragment.AddFzqFragment;
import com.xxhong.fxqian.fragment.InComeFragment;
import com.xxhong.fxqian.fragment.MeunFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener {
	SlidingMenu mSlidingMenu;
	Menu mMenu;
	FragmentManager mFragmentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initSlidingMenu();
		initActionBarAndListener();
		mFragmentManager = getSupportFragmentManager();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new InComeFragment()).commit();
		}

	}

	RelativeLayout rlIncome, rlPayout, rlSetting;

	private void initSlidingMenu() {
		mSlidingMenu = new SlidingMenu(this);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.content_frame);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.content_frame,
						new MeunFragment(getSupportFragmentManager(),
								mSlidingMenu)).commit();
		mSlidingMenu.setOnOpenedListener(new OnOpenedListener() {

			@Override
			public void onOpened() {
				menuAdd.setVisible(false);
				menuSearch.setVisible(false);
			}
		});
	}

	private void initActionBarAndListener() {
		ActionBar supportActionBar = this.getSupportActionBar();
		supportActionBar.setHomeButtonEnabled(true);
	}

	private MenuItem menuAdd, menuSearch;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		menuAdd = menu.findItem(R.id.action_add);
		menuSearch = menu.findItem(R.id.action_settings);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		switch (id) {
		case android.R.id.home:
			int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
			if (backStackEntryCount == 0) {
				mSlidingMenu.showMenu();
			}
			break;
		case R.id.action_add:
			FragmentTransaction beginTransaction = mFragmentManager
					.beginTransaction();
			beginTransaction.addToBackStack(null);
			beginTransaction.replace(R.id.container, new AddFzqFragment())
					.commit();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.rl_income:// 收入
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.container, new InComeFragment()).commit();
		// mSlidingMenu.toggle();
		// break;
		//
		// case R.id.rl_payout:
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.container, new PayOutFragment()).commit();
		// mSlidingMenu.toggle();
		// break;
		// case R.id.rl_setting:
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.container, new SettingFragment()).commit();
		// mSlidingMenu.toggle();
		// break;
		default:
			break;
		}
	}

	public void click(View v) {
		int tabCount = getSupportActionBar().getTabCount();
		Toast.makeText(this, tabCount + "", 0).show();
	}
}
