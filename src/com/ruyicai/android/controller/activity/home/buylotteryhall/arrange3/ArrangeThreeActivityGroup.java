package com.ruyicai.android.controller.activity.home.buylotteryhall.arrange3;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;

/**
 * 排列三选号页面
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class ArrangeThreeActivityGroup extends LotterySwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrangethree_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.arrangethree_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { ArrangeThreeSelfSelectActivity.class,
				ArrangeThreeGroupThreeActivity.class, ArrangeThreeGroupSixActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_group3, R.string.tabhost_textview_group6 };
	}

}
