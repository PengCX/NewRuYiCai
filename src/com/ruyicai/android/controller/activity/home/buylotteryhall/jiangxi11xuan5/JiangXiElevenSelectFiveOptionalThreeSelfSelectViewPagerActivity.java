package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import com.ruyicai.android.R;
/**
 * 江西11选5任选三自选页面
 * @author Administrator
 * @since RYC1.0 2013-12-2
 */
public class JiangXiElevenSelectFiveOptionalThreeSelfSelectViewPagerActivity extends
		JiangXiElevenSelectFiveOptionalDoubleViewPagerActivity {

	@Override
	protected String setPlayMethodTextViewText() {
		String playMethodString = getResources().getString(
				R.string.jiangxi11xuan5_textview_selfselect_optionalthreepalymethod);
		return playMethodString;
	}
}
