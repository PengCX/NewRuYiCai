package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import static com.ruyicai.android.model.bean.betinfo.BettingType.*;

import java.util.List;

import android.os.Bundle;
import android.widget.TabHost;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.compontent.bar.BettingBar;
import com.ruyicai.android.model.bean.betinfo.DoubleBallBettingInfo;

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
		//设置该页面的布局，获取布局中控件对象
		setContentView(R.layout.doubleball_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.doubleball_switchtabs_activitygroup_tabhost);
		_fBetBar = (BettingBar) findViewById(R.id.doubleball_switchtabs_activitygroup_betbar);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] {
				DoubleBallSelfSelectViewPagerActivity.class,
				DoubleBallCourageSelectViewPagerActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_courageselect };
	}

	@Override
	protected void initNowSelectedBettingInfo(int aCurrentTabIndex,
			List<List<Integer>> aNowSelectedNumberLists) {
		if(aCurrentTabIndex == 0){
			_fNowSelectBettingInfo = new DoubleBallBettingInfo(SELF_SELECT, aNowSelectedNumberLists);
		}else if(aCurrentTabIndex == 1){
			_fNowSelectBettingInfo = new DoubleBallBettingInfo(COURAGE_SELECT, aNowSelectedNumberLists);
		}
	}
}
