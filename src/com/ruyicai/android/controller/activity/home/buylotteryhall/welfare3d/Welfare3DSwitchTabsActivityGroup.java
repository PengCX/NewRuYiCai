package com.ruyicai.android.controller.activity.home.buylotteryhall.welfare3d;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.SwitchTabsActivityGroup;

/**
 * 福彩3D选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class Welfare3DSwitchTabsActivityGroup extends SwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welfare3d_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.welfare3d_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { Welfare3DSelfSelectActivity.class,
				Welfare3DGroupThreeActivity.class, Welfare3DGroupSixActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_group3, R.string.tabhost_textview_group6 };
	}
}
