package com.ruyicai.android.controller.activity.home.buylotteryhall.sevenstar;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;

/**
 * 七星彩选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class ServenStarActivityGroup extends LotterySwitchTabsActivityGroup {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servenstar_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.servenstar_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { ServenStarSelfSelectActivity.class,
				ServenStarRandomSelectActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_randomselect };
	}

}
