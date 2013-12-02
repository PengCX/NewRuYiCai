package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

/**
 * 双色球自选页面：创建页面中的选号面板，并将选号面板添加到布局中；初始化各个选号面板的显示
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class DoubleBallSelfSelectViewPagerActivity extends LotteryViewPagerActivity {

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.doubleball_selfselect_normal_page,
				R.layout.doubleball_selfselect_loss_page };
	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] {
				{ R.id.doubleball_selfselect_normal_red_selectnumberpanel,
						R.id.doubleball_selfselect_normal_blue_selectnumberpanel },
				{ R.id.doubleball_selfselect_loss_red_selectnumberpanel,
						R.id.doubleball_selfselect_loss_blue_selectnumberpanel, } };

	}
}
