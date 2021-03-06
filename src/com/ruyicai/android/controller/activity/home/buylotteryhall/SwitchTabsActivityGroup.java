package com.ruyicai.android.controller.activity.home.buylotteryhall;

import roboguice.activity.RoboActivityGroup;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.bar.BettingBar;
import com.ruyicai.android.controller.compontent.bar.TitleBar;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * 彩种选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-10-21
 */
public abstract class SwitchTabsActivityGroup extends RoboActivityGroup {
	/** 选项卡导航栏标签的跳转页面类数组 */
	protected Class<?>[] _fSwithTabSpecClasses;
	/** 选项卡导航栏标签的视图数组 */
	private View[] _fSwtichTabSpecViews;
	/** 选项卡导航栏标签标记字符串数组 */
	protected int[] _fSwitchTabSpecTagIds;

	/**标题栏*/
	protected TitleBar _fTitleBar;
	/**选项卡*/
	protected TabHost _fSwitchTabHost;
	/**投注栏*/
	protected BettingBar _fBetBar;

	/**
	 * 设置切换选项卡切换卡类集合
	 */
	protected abstract void set_fSwithTabSpecClasses();

	/**
	 * 设置切换选项卡的切换卡标识集合
	 */
	protected abstract void set_fSwitchTabSpecTags();

	@Override
	protected void onStart() {
		super.onStart();

		//当Actvity恢复的时候，避免由于重复的初始化，不停的添加TabHostSpec
		if (isTabHostNotInit()) {
			// 初始化切换选项卡的显示
			init_fSwitchTabsShow();
		}
	}

	/**
	 * TabHost是否已经初始化
	 * @return 是否已经初始化标识
	 */
	private boolean isTabHostNotInit() {
		return _fSwithTabSpecClasses == null;
	}

	/**
	 * 初始化切换选项卡的显示
	 */
	private void init_fSwitchTabsShow() {
		_fSwitchTabHost.setup(getLocalActivityManager());
		set_fSwitchTabSpecTags();
		set_fSwtichTabSpecViews();
		set_fSwithTabSpecClasses();
		add_fSwtichTabSpecs();
	}

	/**
	 * 设置选项卡切换卡的视图
	 */
	private void set_fSwtichTabSpecViews() {
		_fSwtichTabSpecViews = new View[_fSwitchTabSpecTagIds.length];

		for (int view_i = 0; view_i < _fSwitchTabSpecTagIds.length; view_i++) {
			TextView textView = new TextView(this);

			// 设置文本、位置、字体颜色，字体大小和背景图片等属性
			textView.setText(_fSwitchTabSpecTagIds[view_i]);
			textView.setGravity(Gravity.CENTER);
			textView.setTextColor(getResources().getColor(R.color.black));
			textView.setTextSize(16.0f);
			textView.setBackgroundResource(R.drawable.tabhost_tab_selector);

			_fSwtichTabSpecViews[view_i] = textView;
		}
	}

	/**
	 * 向选项卡中添加TabSpe对象
	 */
	private void add_fSwtichTabSpecs() {
		for (int tab_i = 0; tab_i < _fSwitchTabSpecTagIds.length; tab_i++) {
			TabHost.TabSpec tabSpec = _fSwitchTabHost
					.newTabSpec(getString(_fSwitchTabSpecTagIds[tab_i]))
					.setIndicator(_fSwtichTabSpecViews[tab_i])
					.setContent(new Intent(this, _fSwithTabSpecClasses[tab_i]));
			_fSwitchTabHost.addTab(tabSpec);
		}
	}
}
