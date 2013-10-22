package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;

/**
 * 双色球选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class DoubleBallSwitchTabsActivityGroup extends LotterySwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubleball_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.doubleball_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { DoubleBallSelfSelectActivity.class,
				DoubleBallCourageSelectActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_courageselect };
	}
}
