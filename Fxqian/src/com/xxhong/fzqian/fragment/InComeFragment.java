package com.xxhong.fzqian.fragment;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import com.xxhong.fzqian.adapter.FzqInComeBaseAdapter;
import com.xxhong.fzqian.utils.domain.UserInfo;

/**
 * 
 * 收入
 * 
 * @author xuxihong
 *
 */
public class InComeFragment extends BaseFragment implements
		LoaderCallbacks<List<UserInfo>> {
	private ListView mListView;
	private FzqInComeBaseAdapter fzqAdapter;
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
		getLoaderManager().initLoader(0, null, this);
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
			List<UserInfo> findAll = FzqApp.mDb.findAll(UserInfo.class);
			fzqAdapter = new FzqInComeBaseAdapter(getActivity(),findAll);
			mListView.setAdapter(fzqAdapter);
		
		return view;
	}


	public void add() {
	}
	public static class SampleLoader extends AsyncTaskLoader<List<UserInfo>> {

		  private List<UserInfo> mData;

		  public SampleLoader(Context ctx) {
		    super(ctx);
		  }

		  @Override
		  public List<UserInfo> loadInBackground() {
		    List<UserInfo> data =null;
			data = FzqApp.mDb.findAll(UserInfo.class);
		    return data;
		  }

		@Override
		protected void onStartLoading() {
			super.onStartLoading();
			forceLoad();
		}

		@Override
		protected void onStopLoading() {
			super.onStopLoading();
		}

		  /********************************************************/
		  /** (2) Deliver the results to the registered listener **/
		  /********************************************************/

//		  @Override
//		  public void deliverResult(List<UserInfo> data) {
//		    if (isReset()) {
		      // The Loader has been reset; ignore the result and invalidate the data.
//		      releaseResources(data);
//		      return;
//		    }

		    // Hold a reference to the old data so it doesn't get garbage collected.
		    // We must protect it until the new data has been delivered.
//		    List<UserInfo> oldData = mData;
//		    mData = data;

//		    if (isStarted()) {
		      // If the Loader is in a started state, deliver the results to the
		      // client. The superclass method does this for us.
//		      super.deliverResult(data);
//		    }

		    // Invalidate the old data as we don't need it any more.
//		    if (oldData != null && oldData != data) {
//		      releaseResources(oldData);
//		    }
		  }

		  /*********************************************************/
		  /** (3) Implement the Loader’s state-dependent behavior **/
		  /*********************************************************/

//		  @Override
//		  protected void onStartLoading() {
//		    if (mData != null) {
		      // Deliver any previously loaded data immediately.
//		      deliverResult(mData);
//		    }

		    // Begin monitoring the underlying data source.
//		    if (mObserver == null) {
//		      mObserver = new SampleObserver();
//		    }

//		    if (takeContentChanged() || mData == null) {
		      // When the observer detects a change, it should call onContentChanged()
		      // on the Loader, which will cause the next call to takeContentChanged()
		      // to return true. If this is ever the case (or if the current data is
		      // null), we force a new load.
//		      forceLoad();
//		    }
//		  }

//		  @Override
//		  protected void onStopLoading() {
		    // The Loader is in a stopped state, so we should attempt to cancel the 
		    // current load (if there is one).
//		    cancelLoad();

		    // Note that we leave the observer as is. Loaders in a stopped state
		    // should still monitor the data source for changes so that the Loader
		    // will know to force a new load if it is ever started again.
//		  }

//		  @Override
//		  protected void onReset() {
//		    onStopLoading();
//		    if (mData != null) {
//		      releaseResources(mData);
//		      mData = null;
//		    }
//		    if (mObserver != null) {
//		      mObserver = null;
//		    }
//		  }

//		  @Override
//		  public void onCanceled(List<UserInfo> data) {
		    // Attempt to cancel the current asynchronous load.
//		    super.onCanceled(data);

		    // The load has been canceled, so we should release the resources
		    // associated with 'data'.
//		    releaseResources(data);
//		  }

//		  private void releaseResources(List<UserInfo> data) {
		    // For a simple List, there is nothing to do. For something like a Cursor, we 
		    // would close it in this method. All resources associated with the Loader
		    // should be released here.
//		  }
//		 
//		  private SampleObserver mObserver;
//		}

	@Override
	public Loader<List<UserInfo>> onCreateLoader(int id, Bundle args) {
		return new  SampleLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<UserInfo>> loader,
			List<UserInfo> data) {
//		fzqAdapter.setUserInfos(data);
//		fzqAdapter.notifyDataSetChanged();
	}

	@Override
	public void onLoaderReset(Loader<List<UserInfo>> loader) {
		
	}
	

}
