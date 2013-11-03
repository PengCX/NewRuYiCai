package com.ruyicai.android.controller.activity.home.buylotteryhall.constantly;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.SwitchTabsActivityGroup;

/**
 * 时时彩选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class ConstantlySwitchTabsActivityGroup extends SwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.constantly_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.constantly_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { ConstantlyOneStarActivity.class,
				ConstantlyTwoStarActivity.class, ConstantlyThreeStarActivity.class,
				ConstantlyFiveStarActivity.class, ConstantlyBigSmallActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_onestar,
				R.string.tabhost_textview_twostar, R.string.tabhost_textview_threestar,
				R.string.tabhost_textview_fivestar, R.string.tabhost_textview_bigsmall };
	}
}
