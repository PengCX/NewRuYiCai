package com.ruyicai.android.controller.compontent.button;

import java.util.List;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.dropdownmenu.SelectRandomNumDropDownMenu;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallsTable;
import com.ruyicai.android.tools.LogTools;
import com.ruyicai.android.tools.MathTools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 随机选号按钮：实现了对随机号码个数的选择，和随即号码的选择功能。用户双色球等选号界面。
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-12
 */
public class RandomSelectButton extends LinearLayout {
	/** 上下文对象 */
	private Context _fContext;
	/** 自我对象 */
	private RandomSelectButton _fSelf;
	/** 选择随机个数按钮 */
	private Button _fSelectRandomNumButton;
	/** 随机选号按钮 */
	private Button _fRandomSelectNumberButton;
	/** 选择随机个数下拉列表 */
	private SelectRandomNumDropDownMenu _fSelectRandomNumDropDownMenu;
	/** 控制的选号小球表格 */
	private SelectNumberBallsTable _fSelectNumberBallsTable;

	/** 选择的随机号码个数 */
	private int _fRandomNum;
	/** 下拉菜单最小随机号码个数 */
	private int _fMinRandomNum;
	/** 下拉菜单的按钮个数 */
	private int _fDropDownMenuButtonNum;

	/**
	 * 设置随机按钮随机产生号码的个数
	 *
	 * @param _fRandomNum
	 *            随机选择号码的个数
	 */
	public void set_fRandomNum(int aRandomNum) {
		_fRandomNum = aRandomNum;
		_fSelectRandomNumButton.setText(_fRandomNum
				+ _fContext.getString(R.string.randombutton_button_unit));
	}

	/**
	 * 设置下拉菜单最小随机号码个数
	 * 
	 * @param aMinRandomNum
	 *            下拉菜单最小随机号码个数
	 */
	public void set_fMinRandomNum(int aMinRandomNum) {
		_fMinRandomNum = aMinRandomNum;
	}

	/**
	 * 设置下拉菜单的按钮个数
	 * 
	 * @param aDropDownMenuButtonNum
	 */
	public void set_fDropDownMenuButtonNum(int aDropDownMenuButtonNum) {
		_fDropDownMenuButtonNum = aDropDownMenuButtonNum;
	}

	/**
	 * 设置随机按钮控制的选号小球表格
	 *
	 * @param aSelectNumberBallsTable
	 *            选号小球表格
	 */
	public void set_fSelectNumberBallsTableLayout(
			SelectNumberBallsTable aSelectNumberBallsTable) {
		_fSelectNumberBallsTable = aSelectNumberBallsTable;
	}
	
	
	private RandomSelectButton(Context context) {
		super(context);
	}

	public RandomSelectButton(Context aContext, AttributeSet aAttributeSet) {
		super(aContext, aAttributeSet);
		_fContext = aContext;
		_fSelf = this;
		// 获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.RandomSelectButton, 0, 0);

		try {
			// 随机按钮默认随机号码个数：6个号码
			_fRandomNum = typedArray.getInt(R.styleable.RandomSelectButton__fRandomNum, 6);
			// 随机按钮默认最小随机号码个数：6个号码
			_fMinRandomNum = typedArray.getInt(R.styleable.RandomSelectButton__fMinRandomNum, 6);
			// 随机按钮下拉菜单随机按钮个数：11个按钮
			_fDropDownMenuButtonNum = typedArray.getInt(
					R.styleable.RandomSelectButton__fDropDownMenuButtonNum, 11);
		} finally {
			typedArray.recycle();
		}

		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.randombutton, this);

		_fSelectRandomNumButton = (Button) findViewById(R.id.randombutton_button_selectrandomnum);
		_fSelectRandomNumButton.setText(_fRandomNum
				+ _fContext.getString(R.string.randombutton_button_unit));
		_fSelectRandomNumButton.setOnClickListener(new RandomSelectButtonClickListener());
		
		_fRandomSelectNumberButton = (Button) findViewById(R.id.randombutton_button_randomselect);
		_fRandomSelectNumberButton.setOnClickListener(new RandomSelectButtonClickListener());
	}

	/**
	 * 设置随机按钮下拉菜单中选择的最小的随机数个数
	 *
	 * @param aMinRandomNum
	 *            最小的随机数个数
	 */
	public void setDropDownMenuMinRandomNum(int aMinRandomNum) {
		_fMinRandomNum = aMinRandomNum;
	}

	

	/**
	 * 设置随机按钮下拉菜单中随机数个数选择按钮的个数
	 *
	 * @param _fDropDownMenuButtonNum
	 *            随机数个数选择按钮的个数
	 */
	public void setDropDownMenuSelectButtonNum(int aSelectButtonNum) {
		_fDropDownMenuButtonNum = aSelectButtonNum;
	}

	/**
	 * 随机选号按钮事件监听器实现类
	 * 
	 * @author xiang_000
	 * @since RYC1.0 2013-4-12
	 */
	class RandomSelectButtonClickListener implements OnClickListener {

		private static final String TAG = "RandomSelectButtonClickListener";

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
				case R.id.randombutton_button_selectrandomnum:
					if (_fSelectRandomNumDropDownMenu == null) {
						_fSelectRandomNumDropDownMenu = new SelectRandomNumDropDownMenu(_fContext,
								_fMinRandomNum, _fDropDownMenuButtonNum);
					}
					_fSelectRandomNumDropDownMenu.set_fRandomSelectNumberButton(_fSelf);
					_fSelectRandomNumDropDownMenu.ShowAsRandomButtonDropDownMenu(v);
					break;
				case R.id.randombutton_button_randomselect:
					// 获取小球表格的起始号码，和小球的个数
					int startNumber = _fSelectNumberBallsTable.get_fSelectNumberBallStartNum();
					int ballNum = _fSelectNumberBallsTable.get_fSelectBallNum();
					// 使用数学工具类获取随机数集合
					List<Integer> specifyNumberList = MathTools
							.getSpecifiedRangeRadomNumberWithoutRepetation(startNumber, startNumber
									+ ballNum, _fRandomNum);
					LogTools.showLog(TAG, "产生的指定小球的编号集合为：" + specifyNumberList.toString() + "，大小为："
							+ specifyNumberList.size(), LogTools.INFO);
					// 根据随机数集合指定小球表格当中的小球为选定状态
					_fSelectNumberBallsTable.selectSpecifiedNumberBalls(specifyNumberList);
					break;
				default:
					throw new AssertionError("switch语句无新增分支");
			}
		}
	}
}
