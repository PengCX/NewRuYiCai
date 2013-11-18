package com.ruyicai.android.controller.activity.home.buylotteryhall;

import roboguice.activity.RoboActivityGroup;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.sensor.ShakeItOffSensor;

/**
 * 彩种资讯页面：
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-4
 */
public class LotteryInformationActivity extends RoboActivityGroup {
	private TestSensor _fTestSensor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lotteryinformation_activity);
		_fTestSensor = new TestSensor(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		_fTestSensor.startSensor();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		_fTestSensor.stopSensor();
	}
	
	class TestSensor extends ShakeItOffSensor {

		public TestSensor(Context aContext) {
			super(aContext);
		}

		@Override
		public void shakeItOffAction() {
			Toast.makeText(LotteryInformationActivity.this, "摇一摇成功", Toast.LENGTH_SHORT).show();
		}

	}
}
