package com.xxhong.fzqian.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxhong.fzqian.R;

/**
 * 支出
 * @author xuxihong
 *
 */
public class PayOutFragment extends BaseFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("送礼");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.payout_fragment_view, container, false);
        return rootView;
	}
}
