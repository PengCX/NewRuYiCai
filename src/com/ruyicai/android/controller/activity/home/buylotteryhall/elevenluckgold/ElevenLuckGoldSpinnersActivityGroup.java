package com.ruyicai.android.controller.activity.home.buylotteryhall.elevenluckgold;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.SpinnersActivityGroup;

/**
 * 11运夺金选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class ElevenLuckGoldSpinnersActivityGroup extends SpinnersActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elevenluckgold_spinners_activitygroup);
		_fSpinner = (Spinner) findViewById(R.id.elevenluckgold_spinners_activitygroup_spinner);
		_fContainerLinearLayout = (LinearLayout) findViewById(R.id.elevenluckgold_spinners_activitygroup_container);

	}

	@Override
	protected void set_fSpinnerClasses() {
		_fSpinnersClasses = new Class<?>[] { ElvenLuckGoldOptionalTwoActivity.class,
				ElvenLuckGoldOptionalThreeActivity.class, ElvenLuckGoldOptionalFourActivity.class,
				ElvenLuckGoldOptionalFiveActivity.class, ElvenLuckGoldOptionalSixActivity.class,
				ElvenLuckGoldOptionalSevenActivity.class, ElvenLuckGoldOptionalEightActivity.class,
				ElvenLuckGoldBeforOneSelfSelectActivity.class,
				ElvenLuckGoldBeforTwoSelfSelectActivity.class,
				ElvenLuckGoldBeforThreeSelfSelectActivity.class,
				ElvenLuckGoldBeforTwoGroupSelectActivity.class,
				ElvenLuckGoldBeforThreeGroupSelectActivity.class };
	}
}
