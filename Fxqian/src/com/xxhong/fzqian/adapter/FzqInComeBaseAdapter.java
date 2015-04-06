package com.xxhong.fzqian.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xxhong.fzqian.R;
import com.xxhong.fzqian.utils.domain.UserInfo;

public class FzqInComeBaseAdapter extends BaseAdapter {
	private LayoutInflater lf;
	private List<UserInfo> userInfos;
	class ViewHolder{
		public TextView tvName,tvMoney,tvCause;
	}

	public FzqInComeBaseAdapter(Context context,List<UserInfo> userInfos) {
		lf = LayoutInflater.from(context);
		this.userInfos = userInfos;
	}
	public FzqInComeBaseAdapter(Context context) {
		lf = LayoutInflater.from(context);
	}
	
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	@Override
	public int getCount() {
		
		return userInfos!=null?userInfos.size():0;
		
	}

	@Override
	public Object getItem(int position) {
		return userInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =null;
		if(convertView ==null){
			view = lf.inflate(R.layout.main_list_item, null);
		}else{
			view = convertView;
		}
		ViewHolder holder = new ViewHolder();
		holder.tvName =  (TextView) view.findViewById(R.id.tv_name);
		holder.tvMoney =  (TextView) view.findViewById(R.id.tv_money);
		holder.tvCause =  (TextView) view.findViewById(R.id.tv_cause);
		if(userInfos!=null){
			UserInfo userInfo = userInfos.get(position);
			holder.tvName.setText(userInfo.getUserName());
			holder.tvMoney.setText(userInfo.getMoney());
			holder.tvCause.setText(userInfo.getCause());
		}
		return view;
	}

	
	
}
