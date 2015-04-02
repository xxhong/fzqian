package com.xxhong.fzqian.fragment;

import java.net.URI;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.ForceLoadContentObserver;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.xxhong.fzqian.FzqApp;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.adapter.FzqAdapter;
import com.xxhong.fzqian.contentprovider.UserInfoProvider;

/**
 * 
 * 收入
 * 
 * @author xuxihong
 *
 */
public class InComeFragment extends BaseFragment implements
		LoaderCallbacks<Cursor> {
	private ListView mListView;
	private FzqAdapter fzqAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("份子钱");
		Thread currentThread = Thread.currentThread();
		String name = currentThread.getName();
		Log.i("xxhong", "onCreate<<" + name);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Loader<Cursor> loader = getLoaderManager().initLoader(0, null, this);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.income_fragment_view, container,
				false);
		mListView = (ListView) view.findViewById(R.id.lv_income);
		Button btn = (Button) view.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				add();
			}
		});
		fzqAdapter = new FzqAdapter(getActivity(), null, true);
		mListView.setAdapter(fzqAdapter);
		// try {
		// SQLiteDatabase db = FzqApp.mContext.openOrCreateDatabase(
		// "fzqian.db", Context.MODE_PRIVATE, null);
		// Cursor cursor = db.rawQuery("select * from user_info",
		// new String[] {});
		// fzqAdapter = new FzqAdapter(getActivity(), cursor, true);
		// mListView.setAdapter(fzqAdapter);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		// SQLiteDatabase db = FzqApp.mContext.openOrCreateDatabase("fzqian.db",
		// Context.MODE_PRIVATE, null);
		// Cursor cursor = db.rawQuery("select * from user_info", new
		// String[]{});
		// fzqAdapter.changeCursor(cursor);
	}

	public void add() {
//		SQLiteDatabase db = FzqApp.mContext.openOrCreateDatabase("fzqian.db",
//				Context.MODE_PRIVATE, null);
		ContentValues v = new ContentValues();
		v.put("userName", "张三");
		v.put("money", "1100");
		v.put("cause", "结婚");
//		long insert = db.insert("user_info", null, v);
//		Uri uri = Uri.withAppendedPath(UserInfoProvider.uri, "user_info/money/100");
//		Cursor query = getActivity().getContentResolver().query(uri, null, null, null, null);
//		if(query!=null&&query.moveToFirst()){
//			
//			String userName = query.getString(query.getColumnIndex("userName"));
//			Toast.makeText(getActivity(), userName, 0).show();
//			
		Uri uri = Uri.withAppendedPath(UserInfoProvider.uri, "user_info");
		getActivity().getContentResolver().insert(uri, v);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		Thread currentThread = Thread.currentThread();
		String name = currentThread.getName();
		Log.i("xxhong", "onCreateLoader<<" + name);
		Uri uri = Uri.withAppendedPath(UserInfoProvider.uri, "user_info");
		return new SampleLoader(getActivity(), uri, null, null, null, null);
//		return new SampleLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		Thread currentThread = Thread.currentThread();
		String name = currentThread.getName();
		Log.i("xxhong", "onLoadFinished<<" + name);
		try {
			// SQLiteDatabase db = FzqApp.mContext.openOrCreateDatabase(
			// "fzqian.db", Context.MODE_PRIVATE, null);
			// Cursor cursor = db.rawQuery("select * from user_info",
			// new String[] {});
			
			fzqAdapter.swapCursor(arg1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		Thread currentThread = Thread.currentThread();
		String name = currentThread.getName();
		Log.i("xxhong", "onLoaderReset<<" + name);
	}

	public static class SampleLoader extends CursorLoader {

		public SampleLoader(Context context, Uri uri, String[] projection,
				String selection, String[] selectionArgs, String sortOrder) {
			super(context, uri, projection, selection, selectionArgs, sortOrder);
		}
		Cursor mCursor;

		
		@Override
		public Cursor loadInBackground() {
			// TODO Auto-generated method stub
			mCursor = super.loadInBackground();
			return mCursor;
		}
		@Override
		protected void onStartLoading() {
			Log.i("xxhong", "startLoading");
			super.onStartLoading();
		}

		@Override
		protected void onStopLoading() {
			Log.i("xxhong", "onStopLoading");
			super.onStopLoading();
		}

		@Override
		public void onCanceled(Cursor cursor) {
			super.onCanceled(cursor);
		}
		@Override
		public void onContentChanged() {

			  if (!mCursor.isClosed()) {
		            deliverResult(mCursor);
		        }
		        forceLoad();

		}
		@Override
		protected void onForceLoad() {
			super.onForceLoad();
		}
	}
}
