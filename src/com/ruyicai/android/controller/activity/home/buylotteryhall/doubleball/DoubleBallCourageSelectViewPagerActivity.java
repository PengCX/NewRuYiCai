package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

/**
 * 双色球胆拖页面：
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class DoubleBallCourageSelectViewPagerActivity extends LotteryViewPagerActivity {

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.doubleball_courageselect_normal_activity,
				R.layout.doubleball_courageselect_loss_activity };
	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] {
				{ R.id.doubleball_courageselect_normal_courage_selectnumberpanel,
						R.id.doubleball_courageselect_normal_tuo_selectnumberpanel,
						R.id.doubleball_courageselect_normal_blue_selectnumberpanel },
				{ R.id.doubleball_courageselect_loss_courage_selectnumberpanel,
						R.id.doubleball_courageselect_loss_tuo_selectnumberpanel,
						R.id.doubleball_courageselect_loss_blue_selectnumberpanel } };
	}
}
