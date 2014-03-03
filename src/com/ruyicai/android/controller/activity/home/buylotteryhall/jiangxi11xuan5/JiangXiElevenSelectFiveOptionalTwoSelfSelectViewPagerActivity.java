package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import com.ruyicai.android.R;

/**
 * 江西11选5任选二自选页面
 * 
 * @author Administrator
 * @since RYC1.0 2013-12-2
 */
public class JiangXiElevenSelectFiveOptionalTwoSelfSelectViewPagerActivity extends
		JiangXiElevenSelectFiveOptionalSingleViewPagerActivity {

	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] { R.layout.jiangxielevenselectfive_optiontwo_courageselect_loss_page };
	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] { {
			R.id.jiangxi11xuan5_courageselect_courage_selectnumberpanel,
			R.id.jiangxi11xuan5_courageselect_drag_selectnumberpanel } };
	}

}
