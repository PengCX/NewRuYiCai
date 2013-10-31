package com.ruyicai.android.controller.activity.home.buylotteryhall;

import java.util.ArrayList;
import java.util.List;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.adapter.viewpager.ViewPagerAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 彩种滑动选号页面
 *
 * @author Administrator
 * @since RYC1.0 2013-10-23
 */
public abstract class LotteryViewPagerSelectNumberActivity extends Activity {
	/** ViewPager对象 */
	protected ViewPager _fViewPager;
	/** ViewPager适配器对象 */
	private ViewPagerAdapter _fViewPagerAdapter;
	/**滑动显示页面的布局资源id数组*/
	protected int[] _fShowLayoutIds;

	/**
	 * 设置显示的布局资源id数组
	 */
	protected abstract void setShowLayoutIds();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottery_viewpager_activity);
		_fViewPager = (ViewPager) findViewById(R.id.lottery_viewpagers_slidearea);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// 初始化滑动控件ViewPager显示
		initViewPagerShow();
	}

	/**
	 * 初始化滑动区域ViewPager的显示
	 */
	private void initViewPagerShow() {
		setShowLayoutIds();
		List<View> viewPagerViewList = getViewPagerViewList();
		setViewPagerAdapter(viewPagerViewList);
	}

	/**
	 * 获取ViewPager滑动区域显示的滑动视图集合
	 *
	 * @return 在ViewPaer中要显示的视图集合
	 */
	private List<View> getViewPagerViewList() {
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		List<View> viewPageViewList = new ArrayList<View>();
		for (int layout_i = 0; layout_i < _fShowLayoutIds.length; layout_i++) {
			View viewPageView = layoutInflater.inflate(_fShowLayoutIds[layout_i], null);
			viewPageViewList.add(viewPageView);
		}
		return viewPageViewList;
	}

	/**
	 * 设置ViewPager的是适配器
	 *
	 * @param aViewPagerViewList
	 *            ViewPager显示的视图集合
	 */
	private void setViewPagerAdapter(List<View> aViewPagerViewList) {
		_fViewPagerAdapter = new ViewPagerAdapter(aViewPagerViewList);
		_fViewPager.setAdapter(_fViewPagerAdapter);
		_fViewPager.setCurrentItem(0);
	}
}
