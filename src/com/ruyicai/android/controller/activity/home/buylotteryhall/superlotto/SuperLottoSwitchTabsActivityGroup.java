package com.ruyicai.android.controller.activity.home.buylotteryhall.superlotto;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.compontent.bar.BettingBar;
import com.ruyicai.android.model.bean.betinfo.SuperLottoBettingInfo;

import android.os.Bundle;
import android.widget.TabHost;

/**
 * 大乐透选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class SuperLottoSwitchTabsActivityGroup extends LotterySwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.superlotto_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.superlotto_switchtabs_activitygroup_tabhost);
		_fBetBar = (BettingBar) findViewById(R.id.superlotto_switchtabs_activitygroup_betbar);;
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { SuperLottoSelfSelectActivity.class,
				SuperLottoCourageSelectActivity.class};
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_courageselect };
	}

	@Override
	protected void createNowBettingInfo() {
		_fNowSelectBettingInfo = new SuperLottoBettingInfo();
	}
}
