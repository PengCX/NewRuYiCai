package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.activitygroups.SpinnersActivityGroup;
import com.ruyicai.android.controller.activity.viewpagers.SelectNumberActivity;

/**
 * 江西11选5选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class JiangXiElevenSelectFiveSpinnersActivityGroup extends SpinnersActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jiangxielevenselectfive_spinners_activitygroup);
		_fSpinner = (Spinner) findViewById(R.id.jiangxielevenselectfive_spinners_activitygroup_spinner);
		_fContainerLinearLayout = (LinearLayout) findViewById(R.id.jiangxielevenselectfive_spinners_activitygroup_container);
	}

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
