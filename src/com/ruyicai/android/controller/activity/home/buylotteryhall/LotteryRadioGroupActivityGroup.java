package com.ruyicai.android.controller.activity.home.buylotteryhall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.ruyicai.android.R;

import roboguice.activity.RoboActivityGroup;

/**
 * 单选按钮页面组
 * 
 * @author Administrator
 * @since RYC1.0 2013-11-25
 */
public abstract class LotteryRadioGroupActivityGroup extends RoboActivityGroup {
	/** 子activity容器 */
	private LinearLayout _fContainerLinearLayout;
	/**第一个单选按钮*/
	private RadioButton[] _fRadioButtons;
	
	/** 单选按钮组类数组 */
	protected Class<?>[] _fRadioButtonsClasses;
	/** 单选按钮组id数组 */
	protected int[] _fRadioButtonsIds;
	/** 单选按钮组字符串id数组 */
	protected int[] _fRadioButtonsStringIds;
	/**单选按钮字符串数组*/
	private String[] _fRadioButtonsStrings;
	
	/**
	 * 设置单选按钮切换类数组
	 */
	protected abstract void set_fRadioButtonsClasses();
	/**
	 * 设置单选按钮的按钮字符串id数组
	 */
	protected abstract void set_fRadioButtonsStringIds();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottery_optionselect_activitygroup);
		_fContainerLinearLayout = (LinearLayout) findViewById(R.id.lottery_optionselect_activitygroup_container);
		
		initRadioButtonsActivityGroupShow();
	}

	/**
	 * 初始化单选按钮ActivityGroup的显示
	 */
	private void initRadioButtonsActivityGroupShow() {
		set_fRadioButtonsClasses();
		set_fRadioButtonsIds();
		set_fRadioButtonsStringIds();
		
		initRadioButtonsShow();
	}
	
	/**
	 * 设置单选按钮的按钮id数组
	 */
	protected void set_fRadioButtonsIds() {
		_fRadioButtonsIds = new int[] { R.id.lottery_optionselect_selfselect_radiobutton,
				R.id.lottery_optionselect_courageselect_radiobutton };
	}

	/**
	 * 初始化单选按钮组的显示
	 */
	private void initRadioButtonsShow() {
		_fRadioButtonsStrings = new String[2];
		_fRadioButtons = new RadioButton[2];
		
		int radioNum = _fRadioButtonsClasses.length;
		for (int radio_i = 0; radio_i < radioNum; radio_i++) {
			//获取按钮字符串资源
			_fRadioButtonsStrings[radio_i] = getResources().getString(_fRadioButtonsStringIds[radio_i]);
			
			//获取单选按钮并设置它的字符串和监听器
			_fRadioButtons[radio_i] = (RadioButton) findViewById(_fRadioButtonsIds[radio_i]);
			_fRadioButtons[radio_i].setText(_fRadioButtonsStrings[radio_i]);
			_fRadioButtons[radio_i].setOnCheckedChangeListener(new RadioButtonOnCheckedChangedListener());
		}
		
		//默认选择第一个按钮
		_fRadioButtons[0].setChecked(true);
	}

	/**
	 * 单选按钮是否选中事件监听类
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-11-26
	 */
	class RadioButtonOnCheckedChangedListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
				case R.id.lottery_optionselect_selfselect_radiobutton:
					changeSubActivityByPosition(0, isChecked);
					break;
				case R.id.lottery_optionselect_courageselect_radiobutton:
					changeSubActivityByPosition(1, isChecked);
					break;
			}
		}

		/**
		 * 根据RadioButton的索引切换子Activity
		 * 
		 * @param aRadioIndex
		 *            RadioButton索引
		 * @param isChecked
		 *            单选按钮是否被选中
		 */
		private void changeSubActivityByPosition(int aRadioIndex, boolean isChecked) {
			if (isChecked) {
				_fContainerLinearLayout.removeAllViews();
				// 在ActivityGroup添加子Activity
				_fContainerLinearLayout.addView(getLocalActivityManager().startActivity(
						_fRadioButtonsStrings[aRadioIndex],
						new Intent(LotteryRadioGroupActivityGroup.this,
								_fRadioButtonsClasses[aRadioIndex])
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
			}
		}
	}
}
