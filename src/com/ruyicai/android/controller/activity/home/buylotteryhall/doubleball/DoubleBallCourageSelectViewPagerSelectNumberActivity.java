package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerSelectNumberActivity;

/**
 * 双色球胆拖页面：
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class DoubleBallCourageSelectViewPagerSelectNumberActivity extends LotteryViewPagerSelectNumberActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubleball_courageselect_viewpager_activity);
		_fViewPager = (ViewPager) findViewById(R.id.doubleball_courageselect_viewpager);
	}

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.doubleball_courageselect_normal_activity,
				R.layout.doubleball_courageselect_loss_activity };
	}
}