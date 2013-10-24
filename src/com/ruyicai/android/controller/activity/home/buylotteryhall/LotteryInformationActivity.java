package com.ruyicai.android.controller.activity.home.buylotteryhall;

import roboguice.activity.RoboActivityGroup;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallsTable;

/**
 * 彩种资讯页面：
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-4
 */
public class LotteryInformationActivity extends RoboActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lotteryinformation_activity);
		SelectNumberBallsTable selectNumberBallsTable = new SelectNumberBallsTable(this);
		selectNumberBallsTable.initSelectNumberBallsTableLayout();

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lotteryinformation);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		linearLayout.addView(selectNumberBallsTable, layoutParams);
	}
}
