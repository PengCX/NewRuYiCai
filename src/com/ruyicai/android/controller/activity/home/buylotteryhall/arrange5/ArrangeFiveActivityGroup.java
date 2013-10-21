package com.ruyicai.android.controller.activity.home.buylotteryhall.arrange5;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryActivityGroup;

/**
 * 排列五选号页面
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class ArrangeFiveActivityGroup extends LotteryActivityGroup {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrangefive_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.arrangefive_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] {
				ArrangeFiveSelfSelectActivity.class,
				ArrangeFiveRandomSelectActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] {
				R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_randomselect };
	}

}
