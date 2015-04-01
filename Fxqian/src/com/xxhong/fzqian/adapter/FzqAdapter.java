package com.xxhong.fzqian.adapter;

import com.xxhong.fzqian.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FzqAdapter extends CursorAdapter {
	private LayoutInflater lf;
	public FzqAdapter(Context context){
		super(context, null, true);
		lf  = LayoutInflater.from(context);
	}
	public FzqAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		lf  = LayoutInflater.from(context);
	}

	public FzqAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = lf.inflate(R.layout.main_list_item, null);
		ViewHolder holder = new ViewHolder();
		holder.tvName =  (TextView) view.findViewById(R.id.tv_name);
		holder.tvMoney =  (TextView) view.findViewById(R.id.tv_money);
		holder.tvCause =  (TextView) view.findViewById(R.id.tv_cause);
		view.setTag(holder);
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		String  userName= cursor.getString(cursor.getColumnIndex("userName"));
		String  money= cursor.getString(cursor.getColumnIndex("money"));
		String  cause= cursor.getString(cursor.getColumnIndex("cause"));
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.tvName.setText(userName);
		holder.tvMoney.setText(money);
		holder.tvCause.setText(cause);
	}
     
	
	class ViewHolder{
		public TextView tvName,tvMoney,tvCause;
	}


	@Override
	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return super.getCursor();
	}

	
	
}
