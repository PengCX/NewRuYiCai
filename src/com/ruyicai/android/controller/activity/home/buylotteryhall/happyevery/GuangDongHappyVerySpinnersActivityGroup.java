package com.ruyicai.android.controller.activity.home.buylotteryhall.happyevery;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySpinnersActivityGroup;

/**
 * 广东快乐十分选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class GuangDongHappyVerySpinnersActivityGroup extends LotterySpinnersActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guangdonghappyvery_spinners_activitygroup);
		_fSpinner = (Spinner) findViewById(R.id.guangdonghappyvery_spinners_activitygroup_spinner);
		_fContainerLinearLayout = (LinearLayout) findViewById(R.id.guangdonghappyvery_spinners_activitygroup_container);
	}

	@Override
	protected void set_fSpinnerClasses() {
		_fSpinnersClasses = new Class<?>[] { GuangDongHappyVerySelectOneNumBettingActivity.class,
				GuangDongHappyVerySelectOneRedBettingActivity.class,
				GuangDongHappyVeryOptionalTwoActivity.class,
				GuangDongHappyVeryOptionalThreeActivity.class,
				GuangDongHappyVeryOptionalFourActivity.class,
				GuangDongHappyVeryOptionalFiveActivity.class,
				GuangDongHappyVerySelectTwoLinkDirectlyActivity.class,
				GuangDongHappyVerySelectThreeBeforDirectly.class,
				GuangDongHappyVerySelectTwoLinkGroup.class,
				GuangDongHappyVerySelectThreeBeforGroupActivity.class };
	}

	@Override
	protected void set_fSpinnerItems() {
		
	}

}
