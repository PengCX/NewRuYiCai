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
		_fShowLayoutIds = new int[] { R.layout.superlotto_courageselect_normal_page,
				R.layout.superlotto_courageselect_loss_page };

	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] {
				{ R.id.superlotto_courageselect_normal_courage_selectnumberpanel,
						R.id.superlotto_courageselect_normal_drag_selectnumberpanel,
						R.id.superlotto_courageselect_normal_blue_selectnumberpanel },
				{ R.id.superlotto_courageselect_loss_courage_selectnumberpanel,
						R.id.superlotto_courageselect_loss_drag_selectnumberpanel,
						R.id.superlotto_courageselect_loss_blue_selectnumberpanel } };
	}
}