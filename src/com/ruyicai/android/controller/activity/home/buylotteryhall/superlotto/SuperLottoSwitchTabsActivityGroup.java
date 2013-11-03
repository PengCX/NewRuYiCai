package com.ruyicai.android.controller.activity.home.buylotteryhall.superlotto;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.SwitchTabsActivityGroup;

import android.os.Bundle;
import android.widget.TabHost;

/**
 * 大乐透选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class SuperLottoSwitchTabsActivityGroup extends SwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.superlotto_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.superlotto_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { SuperLottoSelfSelectActivity.class,
				SuperLottoCourageSelectActivity.class, SuperLottoTwelveSelectTwoActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_courageselect, R.string.tabhost_textview_12xuan2 };
	}
}
