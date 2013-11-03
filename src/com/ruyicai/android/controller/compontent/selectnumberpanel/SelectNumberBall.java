package com.ruyicai.android.controller.compontent.selectnumberpanel;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 选号小球类：实现了选号的点击图片更换，遗漏值的显示功能
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-9
 */
public class SelectNumberBall extends LinearLayout {
	/** 上下文对象 */
	private Context _fContext;
	/** 选号球单选按钮 */
	private CheckBox _fSelectBallCheckBox;
	/** 编号文本框 */
	private TextView _fNumberTextView;
	/** 遗漏值文本框 */
	private TextView _fLossValueTextView;

	/** 小球编号*/
	private String _fNumber;
	/** 小球遗漏值*/
	private String _fLossValue;
	/** 选号球类型*/
	private SelectNumberBallType _fSelectNumberBallType;
	/** 是否显示遗漏值*/
	private boolean _fIsShowLossValue;

	// 初始化代码块，初始化选号小球的默认属性
	{
		//小球默认编号为0
		_fNumber = "0";
		//小球默认遗漏值为0
		_fLossValue = "0";
		//小球的默认类型是红球
		_fSelectNumberBallType = SelectNumberBallType.REDBALL;
		//小球默认不显示遗漏值
		_fIsShowLossValue = false;
	}

	/**
	 * 获取选号小球的编号
	 *
	 * @return 选号小球编号
	 */
	public String get_fNumber() {
		return _fNumber;
	}

	public SelectNumberBall(Context aContext) {
		super(aContext);
		_fContext = aContext;

		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.selectnumber_ball, this);

		_fSelectBallCheckBox = (CheckBox) findViewById(R.id.selectnumberball_checkbox_selectball);
		_fSelectBallCheckBox.setOnCheckedChangeListener(new SelectNumberBallSelectedListener());
		_fNumberTextView = (TextView) findViewById(R.id.selectnumberball_textview_number);
		_fLossValueTextView = (TextView) findViewById(R.id.selectnumberball_textview_lossvalue);
	}

	public SelectNumberBall(Context aContext, AttributeSet aAttrs) {
		super(aContext, aAttrs);
		_fContext = aContext;
	}

	/**
	 * 设置选号球的数字
	 *
	 * @param aNumber
	 *            选号球数字字符串
	 */
	public void setNumber(String aNumber) {
		_fNumber = aNumber;
		_fNumberTextView.setText(aNumber);
	}

	/**
	 * 设置选号球的遗漏值：如果遗漏值可见，则显示遗漏值；如果遗漏值不可见，则设置为不可见
	 *
	 * @param aLossValue
	 *            遗漏值字符串
	 * @param aIsShowLossValue
	 *            是否显示遗漏值标识
	 */
	public void setLossValue(String aLossValue, Boolean aIsShowLossValue) {
		_fIsShowLossValue = aIsShowLossValue;
		_fLossValue = aLossValue;

		if (_fIsShowLossValue) {
			_fLossValueTextView.setText(aLossValue);
		} else {
			_fLossValueTextView.setVisibility(View.GONE);
		}

	}

	/**
	 * 设置选号小球的种类：根据小球的种类，设置不同的图片选择器
	 *
	 * @param aSelectNumberBallType
	 *            选号小球种类枚举
	 */
	public void setSelectNumberBallType(SelectNumberBallType aSelectNumberBallType) {
		_fSelectNumberBallType = aSelectNumberBallType;
		// 枚举在switch语句中判断时，是调用original()方法获取枚举声明位置来进行比较的。为了避免调用方法时的NullPointerException，故做此判断
		if (aSelectNumberBallType != null) {
			switch (aSelectNumberBallType) {
				case REDBALL:
					// FIXME 图片的大小不合适，应该处理一下
					_fSelectBallCheckBox
							.setBackgroundResource(R.drawable.selectnumberball_red_selector);
					break;
				case BLUEBALL:
					_fSelectBallCheckBox
							.setBackgroundResource(R.drawable.selectnumberball_blue_selector);
					break;
				default:
					throw new AssertionError("switch语句中，没有新增的分支");

			}
		}
	}

	/**
	 * 检查该选号小球当前是否被选中
	 *
	 * @return 是否被选中标识
	 */
	public boolean isSelected() {
		if (_fSelectBallCheckBox.isChecked()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取选球的号码
	 *
	 * @return 选球小球的号码
	 */
	public Integer getNumber() {
		return Integer.valueOf((String) _fNumberTextView.getText());
	}

	/**
	 * 取消小球的选中状态
	 */
	public void cancelSelected() {
		if (_fSelectBallCheckBox.isChecked()) {
			_fSelectBallCheckBox.setChecked(false);
		}
	}

	/**
	 * 设置小球为选中状态
	 */
	public void setSelected() {
		if (!_fSelectBallCheckBox.isChecked()) {
			_fSelectBallCheckBox.setChecked(true);
		}
	}

	/**
	 * 选号小球选择选择事件监听类：1.当按钮的选中状态发生变化的时候
	 * ，选号小球号码的颜色进行变化：选中为变色，未选中为黑色；2.当按钮发生变化的时候，更新投注已选号码栏当前选中号码的显示。
	 *
	 * @author xiang_000
	 * @since RYC1.0 2013-4-16
	 */
	class SelectNumberBallSelectedListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			//根据小球是否被选中，改变小球的号码的颜色
			changeSelectBallTextColor(isChecked);

			// 当小球状态发生变化的时候，更新投注已选号码栏当前选中号码的显示
			((LotterySwitchTabsActivityGroup) ((LotteryViewPagerActivity) _fContext).getParent())
					.updateSelectedNumbersShow();

			// 显示当前选择号码注数和金额信息
			((LotterySwitchTabsActivityGroup) ((LotteryViewPagerActivity) _fContext).getParent())
			.showNowSelectedNumberBettingInfo();
		}

		/**
		 * 改变小球的号码的颜色
		 *
		 * @param isChecked
		 *            小球是否被选中
		 */
		private void changeSelectBallTextColor(boolean isChecked) {
			// 当按钮的选中状态发生变化的时候，选号小球号码的颜色进行变化
			if (isChecked) {
				// 如果为选中状态，则号码颜色为白色
				_fNumberTextView.setTextColor(getResources().getColor(R.color.white));
			} else {
				// 如果为选中状态，则号码颜色为黑色
				_fNumberTextView.setTextColor(getResources().getColor(R.color.black));
			}
		}
	}
}
