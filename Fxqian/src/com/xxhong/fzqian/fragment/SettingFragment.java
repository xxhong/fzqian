package com.xxhong.fzqian.fragment;

import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.xxhong.fzqian.R;
import com.xxhong.lib.view.XXRelativeLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SettingFragment extends BaseFragment implements OnClickListener {

	private XXRelativeLayout rlFeadback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("设置");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.setting_fragment, null);
		rlFeadback = (XXRelativeLayout) view.findViewById(R.id.rl_feadback);
		rlFeadback.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_feadback:
			FeedbackAgent agent = new FeedbackAgent(getActivity());
			agent.startFeedbackActivity();
			agent.openFeedbackPush();
			PushAgent.getInstance(getActivity()).enable();
			break;

		default:
			break;
		}
	}

}
