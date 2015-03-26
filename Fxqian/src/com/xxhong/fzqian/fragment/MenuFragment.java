package com.xxhong.fzqian.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.activity.MainActivity;

public class MenuFragment extends Fragment implements OnItemClickListener {
	private FragmentManager fm;
	private SlidingMenu slidingMenu;
	private MainActivity mainActivity;
	private MenuAdapter adapter;
	private View rootView;// 缓存Fragment view
	public String TITLES[] = new String[] { "主页", "图片", "排行", "专题" };
	public int IMAGES[] = new int[] { R.drawable.navigation_tab_news,
			R.drawable.navigation_tab_pics, R.drawable.navigation_tab_tops,
			R.drawable.navigation_tab_zts };

	public MenuFragment(SlidingMenu slidingMenu) {

		this.slidingMenu = slidingMenu;
	}

	public MenuFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		mainActivity = (MainActivity) mainActivity;
		this.fm = getActivity().getSupportFragmentManager();
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.leftmenu_fragment, null);
		ListView listview = (ListView) rootView;
		adapter = new MenuAdapter(TITLES, IMAGES, getActivity());
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
		return rootView;
	}

	public class MenuAdapter extends BaseAdapter {
		public String[] TITLES;
		public int[] IMAGES;
		public LayoutInflater inflater;
		public Context context;
		private ViewHolder viewHolder;
		public int selectedposition;

		public MenuAdapter(String[] TITLES, int[] IMAGES, Context context) {
			this.TITLES = TITLES;
			this.IMAGES = IMAGES;
			this.context = context;
			this.inflater = LayoutInflater.from(context);
		}

		public int getSelectedposition() {
			return selectedposition;
		}

		public void setSelectedposition(int selectedposition) {
			this.selectedposition = selectedposition;
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.leftmenu_item, null);
				viewHolder = new ViewHolder();
				viewHolder.rel = (RelativeLayout) convertView
						.findViewById(R.id.navi_tab);
				viewHolder.img = (ImageView) convertView
						.findViewById(R.id.navi_icon);
				viewHolder.text = (TextView) convertView
						.findViewById(R.id.navi_title);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.text.setText(TITLES[position]);
			viewHolder.img.setImageDrawable(context.getResources().getDrawable(
					IMAGES[position]));
			if (position == selectedposition) {
				viewHolder.rel.setActivated(true);
				viewHolder.rel.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.left_menu_list_selector));
			}
			return convertView;
		}

		class ViewHolder {
			TextView text;
			ImageView img;
			RelativeLayout rel;
		}

	}

	private OnItemClickListener mOnItemClickLister;
	public void setOnItemClickLister(OnItemClickListener listener){
		this.mOnItemClickLister = listener;
	}
	public interface OnItemClickListener{
		void onClick(int position);
	}
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		adapter.setSelectedposition(position);
		adapter.notifyDataSetInvalidated();
		if(mOnItemClickLister!=null){
			mOnItemClickLister.onClick(position);
		}

		slidingMenu.showContent();

	}
}
