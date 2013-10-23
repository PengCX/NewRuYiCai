package com.ruyicai.android.controller.activity.home.buylotteryhall;

import java.util.List;

import com.ruyicai.android.controller.adapter.viewpager.ViewPagerAdapter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 彩种滑动选号页面
 * 
 * @author Administrator
 * @since RYC1.0 2013-10-23
 */
public abstract class LotteryViewPagerSelectNumberActivity extends Activity {
	/** 滑动控件ViewPager对象 */
	protected ViewPager _fViewPager;

	/** 滑动ViewPager适配器对象 */
	public ViewPagerAdapter _fViewPagerAdapter;

	/**
	 * 获取ViewPager滑动区域显示的滑动视图集合
	 * 
	 * @return 在ViewPaer中要显示的视图集合
	 */
	protected abstract List<View> getViewPagerViewList();

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
		List<View> viewPagerViewList = getViewPagerViewList();
		setViewPagerAdapter(viewPagerViewList);
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
