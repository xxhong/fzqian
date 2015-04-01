package com.xxhong.fzqian.contentprovider;

import com.xxhong.fzqian.R;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class UserInfoProvider extends ContentProvider {
	SQLiteDatabase mDB;
	private static UriMatcher  sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	public static final String URI_HOST = "com.xxhong.contentprovider";
	public static final  Uri uri = Uri.parse("content://"+URI_HOST);
	
	static{
		sMatcher.addURI(URI_HOST, "user_info", 1);
		sMatcher.addURI(URI_HOST, "user_info/money/#", 2);
		sMatcher.addURI(URI_HOST, "", 3);
	}
	@Override
	public boolean onCreate() {
		String sql = getContext().getString(R.string.sql);
		mDB = getContext().openOrCreateDatabase("fzqian.db3",Context.MODE_PRIVATE, null);
		mDB.execSQL(sql);
	
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor  cursor = null;
		switch (sMatcher.match(uri)) {
		case 1:
			cursor = mDB.query("user_info", null, selection, selectionArgs,
					null, null, null);
			break;
		case 2:
			long parseId = ContentUris.parseId(uri);
			cursor = mDB.query("user_info", null, "money = '"+parseId+"'", selectionArgs,
					null, null, null);
			break;
		case 3:
			cursor = mDB.query("user_info", null, selection, selectionArgs,
					null, null, null);
		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		mDB.insert("user_info", null, values);
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
