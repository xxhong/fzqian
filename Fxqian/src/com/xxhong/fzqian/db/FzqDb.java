package com.xxhong.fzqian.db;

import com.xxhong.fzqian.R;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FzqDb extends SQLiteOpenHelper {
	public static final int DBVERSION = 1;
	private Context mContext;
	public FzqDb(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}
	
	public FzqDb(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.mContext = context;
		
	}
	
	public FzqDb(Context context) {
		super(context, "fzqian.db3", null, DBVERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = mContext.getString(R.string.sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
