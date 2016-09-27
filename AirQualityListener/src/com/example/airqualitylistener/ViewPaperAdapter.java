package com.example.airqualitylistener;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ViewPaperAdapter extends PagerAdapter {

	private List<View> views;
	private Context context;
	
	
	public ViewPaperAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}
	
	//销毁不需要的views
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(views.get(position));
			return views.get(position); //返回当前view
		}
	
	@Override
	public int getCount() {
		return views.size();
	}
	

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
