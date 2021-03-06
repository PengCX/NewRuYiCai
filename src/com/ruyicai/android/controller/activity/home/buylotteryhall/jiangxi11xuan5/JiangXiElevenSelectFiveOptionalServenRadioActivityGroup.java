package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryRadioGroupActivityGroup;

/**
 * 江西11选5任选七选号页面： 1.实现玩法介绍的设置
 * 
 * @author xiang_000
 * @since RYC1.0 2013-5-2
 */
public class JiangXiElevenSelectFiveOptionalServenRadioActivityGroup extends
		LotteryRadioGroupActivityGroup {

	@Override
	protected void set_fRadioButtonsClasses() {
		_fRadioButtonsClasses = new Class<?>[] {
				JiangXiElevenSelectFiveOptionalServenSelfSelectViewPagerActivity.class,
				JiangXiElevenSelectFiveOptionalServenCourageSelectViewPagerActivity.class };
	}
}