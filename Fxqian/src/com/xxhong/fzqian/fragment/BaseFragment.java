package com.xxhong.fzqian.fragment;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.xxhong.fzqian.R;

public class BaseFragment extends SherlockFragment {

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		MenuItem item1 = menu.findItem(R.id.action_settings);
		MenuItem item2 = menu.findItem(R.id.action_add);
		item1.setVisible(false);
		item2.setVisible(false);
	}
	protected void setTitle(String title) {
		getSherlockActivity().getActionBar().setTitle(title);
	}
	
}
