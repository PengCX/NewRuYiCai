package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import android.os.Bundle;
import android.widget.TextView;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

public abstract class JiangXiElevenSelectFiveOptionalSelfSelectViewPagerActivity extends
		LotteryViewPagerActivity {
	/**普通页面玩法文本框*/
	protected TextView _fNormalPlayMethodTextView;
	/**遗漏值页面玩法文本框*/
	protected TextView _fLossPlayMethodTextView;
	
	/**
	 * 设置玩法文本框的文本
	 */
	protected abstract void setPlayMethodTextViewText();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_fNormalPlayMethodTextView = (TextView) findViewById(R.id.jiangxi11xuan5_selfselect_normal_textview);
		_fLossPlayMethodTextView = (TextView) findViewById(R.id.jiangxi11xuan5_selfselect_loss_textview);
		
		setPlayMethodTextViewText();
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
