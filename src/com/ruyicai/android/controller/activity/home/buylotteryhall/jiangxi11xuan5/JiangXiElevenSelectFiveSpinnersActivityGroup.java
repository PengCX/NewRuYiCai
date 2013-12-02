package com.ruyicai.android.controller.activity.home.buylotteryhall.jiangxi11xuan5;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySpinnersActivityGroup;

/**
 * 江西11选5选号页面组
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class JiangXiElevenSelectFiveSpinnersActivityGroup extends LotterySpinnersActivityGroup {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置该页面的布局，获取布局中控件对象
		setContentView(R.layout.jiangxielevenselectfive_spinners_activitygroup);
		_fSpinner = (Spinner) findViewById(R.id.jiangxielevenselectfive_spinners_activitygroup_spinner);
		_fContainerLinearLayout = (LinearLayout) findViewById(R.id.jiangxielevenselectfive_spinners_activitygroup_container);
	}

	@Override
	protected void set_fSpinnerClasses() {
		_fSpinnersClasses = new Class<?>[] { JiangXiElevenSelectFiveOptionalTwoRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalThreeRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalFourRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalFiveRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalSixRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalServenRadioActivityGroup.class,
				JiangXiElevenSelectFiveOptionalEightRadioActivityGroup.class,
				JiangXiElevenSelectFiveBeforOneSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforTwoSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforThreeSelfSelectActivity.class,
				JiangXiElevenSelectFiveBeforOneGroupSelectActivity.class,
				JiangXiElevenSelectFiveBeforTwoGroupSelectActivity.class,
				JiangXiElevenSelectFiveBeforThreeGroupSelectActivity.class };
	}

	@Override
	protected void set_fSpinnerItems() {
		_fSpinnerStringIds = new int[] { R.string.spinner_item_optionaltwo,
				R.string.spinner_item_optionalthree, R.string.spinner_item_optionalfour,
				R.string.spinner_item_optionalfive, R.string.spinner_item_optionalsix,
				R.string.spinner_item_optionalseven, R.string.spinner_item_optionaleight,
				R.string.spinner_item_beforoneselfselect, R.string.spinner_item_befortwoselfselect,
				R.string.spinner_item_beforthreeselfselect,
				R.string.spinner_item_beforonesgroupselect,
				R.string.spinner_item_befortwogroupselect,
				R.string.spinner_item_beforthreegroupselect };
	}
}