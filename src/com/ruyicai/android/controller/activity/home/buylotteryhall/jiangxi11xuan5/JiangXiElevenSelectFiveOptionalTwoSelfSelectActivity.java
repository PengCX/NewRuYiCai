package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import com.ruyicai.android.R;

public class JiangXiElevenSelectFiveOptionalTwoSelfSelectActivity extends
		JiangXiElevenSelectFiveOptionalSelfSelectViewPagerActivity {

	@Override
	protected void setPlayMethodTextViewText() {
		_fNormalPlayMethodTextView.setText(getResources().getString(
				R.string.jiangxi11xuan5_textview_selfselect_optionaltwopalymethod));
		_fLossPlayMethodTextView.setText(getResources().getString(
				R.string.jiangxi11xuan5_textview_selfselect_optionaltwopalymethod));
	}
}
