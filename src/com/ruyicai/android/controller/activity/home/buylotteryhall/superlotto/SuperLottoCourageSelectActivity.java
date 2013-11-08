package com.ruyicai.android.controller.activity.home.buylotteryhall.superlotto;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

/**
 * 大乐透胆拖页面
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class SuperLottoCourageSelectActivity extends LotteryViewPagerActivity {

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.doubleball_selfselect_normal_activity,
				R.layout.doubleball_selfselect_loss_activity };

	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] {
				{ R.id.superlotto_selfselect_normal_proparea_selectnumberpanel,
						R.id.superlotto_selfselect_normal_backarea_selectnumberpanel },
				{ R.id.superlotto_selfselect_loss_proparea_selectnumberpanel,
						R.id.superlotto_selfselect_loss_backarea_selectnumberpanel, } };
	}
}