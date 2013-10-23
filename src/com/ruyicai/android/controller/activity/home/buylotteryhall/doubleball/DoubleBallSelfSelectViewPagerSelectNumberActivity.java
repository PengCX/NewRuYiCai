package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerSelectNumberActivity;

/**
 * 双色球自选页面：创建页面中的选号面板，并将选号面板添加到布局中；初始化各个选号面板的显示
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class DoubleBallSelfSelectViewPagerSelectNumberActivity extends LotteryViewPagerSelectNumberActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubleball_selfselect_viewpager_activity);
		_fViewPager = (ViewPager) findViewById(R.id.doubleball_selfselect_viewpager);
	}

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.doubleball_selfselect_normal_activity,
				R.layout.doubleball_selfselect_loss_activity };
	}
}
