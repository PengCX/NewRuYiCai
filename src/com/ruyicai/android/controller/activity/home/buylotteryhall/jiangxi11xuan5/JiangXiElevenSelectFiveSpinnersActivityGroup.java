package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import com.ruyicai.android.controller.activity.home.buylotteryhall.SpinnersActivityGroup;

/**
 * 江西11选5选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class JiangXiElevenSelectFiveSpinnersActivityGroup extends SpinnersActivityGroup {

	@Override
	protected void set_fSpinnerClasses() {
		_fSpinnersClasses = new Class<?>[] { JiangXiElevenSelectFiveOptionalTwoActivity.class,
				JiangXiElevenSelectFiveOptionalThreeActivity.class,
				JiangXiElevenSelectFiveOptionalFourActivity.class,
				JiangXiElevenSelectFiveOptionalFiveActivity.class,
				JiangXiElevenSelectFiveOptionalSixActivity.class,
				JiangXiElevenSelectFiveOptionalSevenActivity.class,
				JiangXiElevenSelectFiveOptionalEightActivity.class,
				JiangXiElevenSelectFiveBeforOneSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforTwoSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforThreeSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforOneGroupSelectActivity.class,
				JiangXiElevenSelectFiveBeforTwoGroupSelectActivity.class,
				JiangXiElevenSelectFiveBeforThreeGroupSelectActivity.class };
	}
}