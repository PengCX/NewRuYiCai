package com.ruyicai.android.controller.activity.home.buylotteryhall.guangdong11xuan5;

import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySpinnersActivityGroup;

/**
 * 广东11选5选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class GuangDongElevenSelectFiveActivityGroup extends LotterySpinnersActivityGroup {


	@Override
	protected void set_fSpinnerClasses() {
		_fSpinnersClasses = new Class<?>[] { GuangDongElevenSelectFiveOptionalTwoActivity.class,
				GuangDongElevenSelectFiveOptionalThreeActivity.class,
				GuangDongElevenSelectFiveOptionalFourActivity.class,
				GuangDongElevenSelectFiveOptionalFiveActivity.class,
				GuangDongElevenSelectFiveOptionalSixActivity.class,
				GuangDongElevenSelectFiveOptionalSevenActivity.class,
				GuangDongElevenSelectFiveOptionalEightActivity.class,
				GuangDongElevenSelectFiveBeforOneSelfSelectActivity.class,
				GuangDongElevenSelectFiveBeforTwoSelfSelectActivity.class,
				GuangDongElevenSelectFiveBeforThreeSelfSelectActivity.class,
				GuangDongElevenSelectFiveBeforTwoGroupSelectActivity.class,
				GuangDongElevenSelectFiveBeforThreeGroupSelectActivity.class };
	}

	@Override
	protected void set_fSpinnerItems() {
		
	}

}
