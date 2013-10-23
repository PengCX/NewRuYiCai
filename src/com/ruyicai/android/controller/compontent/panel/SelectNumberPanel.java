package com.ruyicai.android.controller.compontent.panel;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.button.RandomSelectNumberButton;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallType;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallsTableLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 选号面板类：实现了彩票选号号功能，默认标题：文本-红球区；随机按钮：最小随机个数-6个，选择按钮-10个，产生随机数个数-6个；选号小球表：起始编号-1，
 * 小球个数-32个，小球种类-红球，是否显示遗漏值-显示，遗漏值-都为0。
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-9
 */
public class SelectNumberPanel extends LinearLayout {
	/** 上下文对象 */
	private Context _fContext;

	/** 标题文本框 */
	private TextView _fTitleTextView;
	/** 随机选号按钮 */
	private RandomSelectNumberButton _fRandomSelectNumberButton;
	/** 选号小球表格布局 */
	private SelectNumberBallsTableLayout _fSelectNumberBallsTableLayout;

	/** 标题字符串资源id */
	private int _fTitleTextId;
	/**随机按钮随机号码个数*/
	private int _fRandomNumberNum;
	/** 随机按钮最小的随机个数 */
	private int _fRandomButtonMinRandomNum;
	/** 随机按钮下拉菜单按钮的个数 */
	private int _fRandomButtonDropDownMenuButtonNum;
	/** 选号小球的起始号码 */
	private int _fSelectNumberBallStartNum;
	/** 选号小球的个数 */
	private int _fSelectNumberBallNum;
	/** 选号小球的类型 */
	private SelectNumberBallType _fSelectNumberBallType;
	/** 是否显示遗漏值 */
	private boolean _fIsShowLossValue;

	/**
	 * 构造函数
	 *
	 * @param aContext
	 *            上下文对象
	 */
	public SelectNumberPanel(Context aContext) {
		super(aContext);
		_fContext = aContext;
	}

	/**
	 * 构造函数
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aAttrs
	 *            属性对象
	 */
	public SelectNumberPanel(Context aContext, AttributeSet aAttributeSet) {
		super(aContext, aAttributeSet);
		_fContext = aContext;

		// 获取标题栏布局
		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.selectnumber_panel, this);

		// 获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.SelectNumberPanel, 0, 0);
		try {
			_fTitleTextId = typedArray.getResourceId(R.styleable.SelectNumberPanel__fTitleTextId,
					-1);
			_fRandomNumberNum = typedArray.getInt(R.styleable.SelectNumberPanel__fRandomNumberNum, -1);
			_fRandomButtonMinRandomNum = typedArray.getInt(
					R.styleable.SelectNumberPanel__fSelectNumberBallNum, -1);
			_fRandomButtonDropDownMenuButtonNum = typedArray.getInt(
					R.styleable.SelectNumberPanel__fRandomButtonDropDownMenuButtonNum, -1);
			_fSelectNumberBallStartNum = typedArray.getInt(
					R.styleable.SelectNumberPanel__fSelectNumberBallStartNum, -1);
			_fSelectNumberBallNum = typedArray.getInt(
					R.styleable.SelectNumberPanel__fSelectNumberBallNum, -1);
			int ballType = typedArray.getInt(R.styleable.SelectNumberPanel__fSelectNumberBallType,
					0);
			if (ballType == 0) {
				_fSelectNumberBallType = SelectNumberBallType.REDBALL;
			} else {
				_fSelectNumberBallType = SelectNumberBallType.BLUEBALL;
			}
			_fIsShowLossValue = typedArray.getBoolean(
					R.styleable.SelectNumberPanel__fIsShowLossValue, false);
		} finally {
			typedArray.recycle();
		}

		_fTitleTextView = (TextView) findViewById(R.id.selectnumberpanel_textview_title);
		if (_fTitleTextId != -1) {
			_fTitleTextView.setText(_fTitleTextId);
		}

		_fRandomSelectNumberButton = (RandomSelectNumberButton) findViewById(R.id.selectnumberpanel_selectnumberbutton_random);
		if (_fRandomNumberNum != -1) {
			_fRandomSelectNumberButton.set_fRandomSelectNum(_fRandomNumberNum);
		}
		if (_fRandomButtonMinRandomNum != -1) {
			_fRandomSelectNumberButton.setDropDownMenuMinRandomNum(_fRandomButtonMinRandomNum);
		}
		if (_fRandomButtonDropDownMenuButtonNum != -1) {
			_fRandomSelectNumberButton
					.setDropDownMenuSelectButtonNum(_fRandomButtonDropDownMenuButtonNum);
		}

		_fSelectNumberBallsTableLayout = (SelectNumberBallsTableLayout) findViewById(R.id.selectnumberpanel_selectnumberballs_tablelayout);
		if(_fSelectNumberBallStartNum != -1){
			_fSelectNumberBallsTableLayout.set_fSelectNumberBallStartNum(_fSelectNumberBallStartNum);
		}

		if(_fSelectNumberBallNum != -1){
			_fSelectNumberBallsTableLayout.set_fSelectNumberBallNum(_fSelectNumberBallNum);
		}
		_fSelectNumberBallsTableLayout.set_fSelectNumberBallType(_fSelectNumberBallType);
		_fSelectNumberBallsTableLayout.set_fIsShowLossValue(_fIsShowLossValue);
		_fSelectNumberBallsTableLayout.initSelectNumberBallsTableLayoutShow();

		// 设置随机按钮控制的选号小球表格
		_fRandomSelectNumberButton
				.set_fSelectNumberBallsTableLayout(_fSelectNumberBallsTableLayout);
	}

	/**
	 * 设置选号面板的标题文本
	 *
	 * @param aTitlString
	 *            标题文本
	 */
	public void setPanelTitle(String aTitleString) {
		_fTitleTextView.setText(aTitleString);
	}

	/**
	 * 设置随机选号按钮最小的随机号码个数
	 *
	 * @param aMinRandomNum
	 *            最小的随机号码个数
	 */
	public void setMinRandomNum(int aMinRandomNum) {
		_fRandomSelectNumberButton.setDropDownMenuMinRandomNum(aMinRandomNum);
	}

	/**
	 * 设置随机下拉菜单中选择随机数个数按钮的个数
	 *
	 * @param aSelectButtonNum
	 *            下拉菜单选择随机数个数按钮的个数
	 */
	public void setSelectButtonNum(int aSelectButtonNum) {
		_fRandomSelectNumberButton.setDropDownMenuSelectButtonNum(aSelectButtonNum);
	}

	/**
	 * 设置选号小球的起始号码
	 */
	public void setSelectBallsStartNum(int aSelectNumberBallStartNum) {
		_fSelectNumberBallsTableLayout.set_fSelectNumberBallStartNum(aSelectNumberBallStartNum);
	}

	/**
	 * 设置随机按钮的可见性（在初始化面板之后调用，否则按钮对象为空）
	 *
	 * @param visibility
	 *            可见性标识
	 */
	public void setRandomButtonVisibiity(int visibility) {
		// FIXME 如何控制在为空的调用的时候的异常处理
		if (_fRandomSelectNumberButton != null) {
			_fRandomSelectNumberButton.setVisibility(visibility);
		}
	}

	/**
	 * 获取当前选择的小球号码集合
	 *
	 * @return 当前选择的小球号码集合
	 */
	public String getSelectedNumbersString() {
		return _fSelectNumberBallsTableLayout.getSelectedNumbersString();
	}
}
