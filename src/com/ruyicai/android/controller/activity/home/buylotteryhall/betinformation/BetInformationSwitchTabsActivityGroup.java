package com.ruyicai.android.controller.activity.home.buylotteryhall.betinformation;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.SwitchTabsActivityGroup;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * 投注信息页面：用户显示投注后的投注信息，并设置倍数、追号、合买和赠送页面。用户双色球、大乐透。
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-18
 */
public class BetInformationSwitchTabsActivityGroup extends SwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.betinformation_switchtabs_activitygroup);

		_fSwitchTabHost = (TabHost) findViewById(R.id.betinformation_switchtabs_activitygroup_tabhost);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] { BetInformationActivity.class,
				AppendInformationActivity.class, UnionInformationActivity.class,
				PresentInformationActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_betting,
				R.string.tabhost_textview_append, R.string.tabhost_textview_union,
				R.string.tabhost_textview_present };
	}

	class BettingInformationOnTabChangeListener implements OnTabChangeListener {

		@Override
		public void onTabChanged(String tabId) {
			// 根据不同的选项卡改变标题
			if (tabId.equals(getString(R.string.tabhost_textview_betting))) {
				_fTitleBar.set_fLeftTextString(R.string.bettinginformation_title_betting);
			} else if (tabId.equals(getString(R.string.tabhost_textview_append))) {
				_fTitleBar.set_fLeftTextString(R.string.bettinginformation_title_append);
			} else if (tabId.equals(getString(R.string.tabhost_textview_union))) {
				_fTitleBar.set_fLeftTextString(R.string.bettinginformation_title_union);
			} else if (tabId.equals(getString(R.string.tabhost_textview_present))) {
				_fTitleBar.set_fLeftTextString(R.string.bettinginformation_title_present);
			}
		}
	}
}
