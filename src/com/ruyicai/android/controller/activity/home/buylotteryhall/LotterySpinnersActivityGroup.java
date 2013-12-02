package com.ruyicai.android.controller.activity.home.buylotteryhall;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import roboguice.activity.RoboActivityGroup;

/**
 * 下拉列表切换页面基类：包含标题栏、彩种开奖信息栏和下拉列表，使用下拉列表切换页面；适用于江西11选5投注页面
 * 
 * @author xiang_000
 * @since RYC1.0 2013-5-1
 */
public abstract class LotterySpinnersActivityGroup extends RoboActivityGroup {
	/** 下拉列表跳转页面类数组 */
	protected Class<?>[] _fSpinnersClasses;
	/** 下拉列表选项字符串数组 */
	protected int[] _fSpinnerStringIds;
	/** 下拉列表字符串数组 */
	private String[] _fSpinnerStrings;

	/** 下拉列表 */
	protected Spinner _fSpinner;
	/** Activity容器 */
	protected LinearLayout _fContainerLinearLayout;

	/**
	 * 设置下拉菜单切换类数组
	 */
	protected abstract void set_fSpinnerClasses();
	/**
	 * 设置下拉菜单itemid
	 */
	protected abstract void set_fSpinnerItems();
	
	@Override
	protected void onStart() {
		super.onStart();
		// 初始化下拉按钮切换选项卡的显示
		initSpinnersShow();
	}

	/**
	 * 初始化下拉按钮显示
	 */
	private void initSpinnersShow() {
		set_fSpinnerItems();

		// 获取下拉选项字符串资源
		int length = _fSpinnerStringIds.length;
		_fSpinnerStrings = new String[length];
		for (int string_i = 0; string_i < length; string_i++) {
			_fSpinnerStrings[string_i] = getResources().getString(_fSpinnerStringIds[string_i]);
		}

		// 设置下拉列表适配器
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, _fSpinnerStrings);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_fSpinner.setAdapter(spinnerAdapter);

		// 设置下拉列表选项切换页面事件监听
		set_fSpinnerClasses();
		_fSpinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
	}

	/**
	 * 下拉列表选项选中事件监听器实现类
	 * 
	 * @author xiang_000
	 * @since RYC1.0 2013-5-1
	 */
	class SpinnerOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			_fContainerLinearLayout.removeAllViews();
			//在ActivityGroup添加子Activity
			_fContainerLinearLayout.addView(getLocalActivityManager().startActivity(
					_fSpinnerStrings[position],
					new Intent(LotterySpinnersActivityGroup.this, _fSpinnersClasses[position])
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	}
}
