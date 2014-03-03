package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import android.widget.TextView;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

public abstract class JiangXiElevenSelectFiveOptionalDoubleViewPagerActivity extends
		LotteryViewPagerActivity {
	/**普通页面玩法文本框*/
	protected TextView _fNormalPlayMethodTextView;
	/**遗漏值页面玩法文本框*/
	protected TextView _fLossPlayMethodTextView;
	
	/**
	 * 设置玩法文本框的文本
	 */
	protected abstract String setPlayMethodTextViewText();
	
	@Override
	protected void onStart() {
		super.onStart();
		initPlayMethodStringShow();
	}

	/**
	 * 初始化玩法字符串的显示
	 */
	private void initPlayMethodStringShow() {
		_fNormalPlayMethodTextView = (TextView) _fViewPagerViewList.get(0).findViewById(
				R.id.jiangxi11xuan5_selfselect_normal_textview);
		_fLossPlayMethodTextView = (TextView) _fViewPagerViewList.get(1).findViewById(
				R.id.jiangxi11xuan5_selfselect_loss_textview);
		String playMethodString = setPlayMethodTextViewText();
		_fNormalPlayMethodTextView.setText(playMethodString);
		_fLossPlayMethodTextView.setText(playMethodString);
	}
	
	@Override
	protected void setShowLayoutIds() {
		_fShowLayoutIds = new int[] {
				R.layout.jiangxielevenselectfive_optional_selfselect_normal_page,
				R.layout.jiangxielevenselectfive_optional_selfselect_loss_page };
	}

	@Override
	protected void setSelectNumberPanelIds() {
		_fSelectNumberPanelIds = new int[][] {
				{ R.id.jiangxi11xuan5_selfselect_normal_selectnumberpanel },
				{ R.id.jiangxi11xuan5_selfselect_loss_selectnumberpanel } };

	}

}
