package com.xxhong.fzqian.fragment;

import com.umeng.fb.model.UserInfo;

import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.DataSetObservable;
import android.database.Observable;

public class SampleObserver extends ContentObservable {

	@Override
	public void registerObserver(ContentObserver observer) {
		// TODO Auto-generated method stub
		super.registerObserver(observer);
	}

	@Override
	public void notifyChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.notifyChange(selfChange);
	}

	@Override
	public void unregisterObserver(ContentObserver observer) {
		// TODO Auto-generated method stub
		super.unregisterObserver(observer);
	}

}
