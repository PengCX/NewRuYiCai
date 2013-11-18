package com.ruyicai.android.controller.compontent.panel;

import java.util.List;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.button.RandomSelectButton;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallType;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallsTable;

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
	private RandomSelectButton _fRandomSelectButton;
	/** 选号小球表格布局 */
	private SelectNumberBallsTable _fSelectNumberBallsTable;

	/** 标题字符串资源id */
	private int _fTitleTextId;

	//初始化代码块，初始化选号面板的线性布局的属性
	{
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		setLayoutParams(layoutParams);
		setOrientation(VERTICAL);
	}

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
		inflaterSelectNumberPanelLayout(aContext);

		getCustomDefineAttributes(aAttributeSet);

		initTitleTextViewShow();

		initSelectNumberBallsTableShow();
	}

	/**
	 * 解析并填充选号面板的布局
	 *
	 * @param aContext
	 *            上下文对象
	 */
	private void inflaterSelectNumberPanelLayout(Context aContext) {
		// 获取标题栏布局
		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		/**
		 * inflate方法说明：第一个参数resource:是指填充的布局资源id,第二个参数root:是resource布局填充到的根布局。
		 * 相关解释：在这里为设置为root为本身this，将selectnumber_panel中的视图以SelectNumberPanel（继承RelativeLayout）
		 * 自身为根视图填充， 并且在selectnumber_panel布局中使用merge标签，避免深的视图结构。
		 */
		layoutInflater.inflate(R.layout.selectnumber_panel, this);

		_fTitleTextView = (TextView) findViewById(R.id.selectnumberpanel_textview_title);
		_fRandomSelectButton = (RandomSelectButton) findViewById(R.id.selectnumberpanel_selectnumberbutton_random);
		_fSelectNumberBallsTable = new SelectNumberBallsTable(_fContext);
	}

	/**
	 * 获取自定义属性的值
	 *
	 * @param aAttributeSet
	 *            aAttributeSet对象
	 */
	private void getCustomDefineAttributes(AttributeSet aAttributeSet) {
		// 获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.SelectNumberPanel, 0, 0);
		try {
			/**
			 * 获取选号面板标题属性
			 */
			// 标题默认显示：红球区：
			_fTitleTextId = typedArray.getResourceId(R.styleable.SelectNumberPanel__fTitleTextId,
					R.string.doubleball_selfselect_redselectnumberpanel_title);

			/**
			 * 获取随机按钮属性
			 */
			// 随机按钮默认随机号码个数：6个号码
			_fRandomSelectButton.set_fRandomNum(typedArray.getInt(
					R.styleable.SelectNumberPanel__fRandomButtonRandomNum, 6));
			// 随机按钮默认最小随机号码个数：6个号码
			_fRandomSelectButton.set_fMinRandomNum(typedArray.getInt(
					R.styleable.SelectNumberPanel__fRandomButtonMinRandomNum, 6));
			// 随机按钮下拉菜单随机按钮个数：11个按钮
			_fRandomSelectButton.set_fDropDownMenuButtonNum(typedArray.getInt(
					R.styleable.SelectNumberPanel__fRandomButtonDropDownMenuButtonNum, 11));

			/**
			 * 获取选号小球表格属性
			 */
			// 选号小球默认起始号码：1
			_fSelectNumberBallsTable.set_fStartNum(typedArray.getInt(
					R.styleable.SelectNumberPanel__fSelectNumberBallStartNum, 1));
			// 选号小球默认个数：33个
			_fSelectNumberBallsTable.set_fBallNum(typedArray.getInt(
					R.styleable.SelectNumberPanel__fSelectNumberBallNum, 33));
			// 选号小球类型：红球
			int ballType = typedArray.getInt(R.styleable.SelectNumberPanel__fSelectNumberBallType,
					0);
			if (ballType == 0) {
				_fSelectNumberBallsTable.set_fBallType(SelectNumberBallType.REDBALL);
			} else {
				_fSelectNumberBallsTable.set_fBallType(SelectNumberBallType.BLUEBALL);
			}
			// 是否显示遗漏值默认：不显示
			_fSelectNumberBallsTable.set_fIsShowLossValue(typedArray.getBoolean(
					R.styleable.SelectNumberPanel__fIsShowLossValue, false));
		} finally {
			typedArray.recycle();
		}
	}

	/**
	 * 初始化选号面板标题显示
	 */
	private void initTitleTextViewShow() {
		_fTitleTextView.setText(_fTitleTextId);
	}

	/**
	 * 初始化选号小球表格的显示
	 */
	public void initSelectNumberBallsTableShow() {
		// FIXME 在没有输入任何参数的情况下的处理
		_fSelectNumberBallsTable.initSelectNumberBallsTableLayout();

		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.topMargin = 5;
		addView(_fSelectNumberBallsTable, layoutParams);


		// 设置随机按钮控制的选号小球表格
		_fRandomSelectButton.set_fSelectNumberBallsTableLayout(_fSelectNumberBallsTable);
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
		_fRandomSelectButton.setDropDownMenuMinRandomNum(aMinRandomNum);
	}

	/**
	 * 设置随机下拉菜单中选择随机数个数按钮的个数
	 *
	 * @param aSelectButtonNum
	 *            下拉菜单选择随机数个数按钮的个数
	 */
	public void setSelectButtonNum(int aSelectButtonNum) {
		_fRandomSelectButton.setDropDownMenuSelectButtonNum(aSelectButtonNum);
	}

	/**
	 * 设置选号小球的起始号码
	 */
	public void setSelectBallsStartNum(int aSelectNumberBallStartNum) {
		_fSelectNumberBallsTable.set_fSelectNumberBallStartNum(aSelectNumberBallStartNum);
	}

	/**
	 * 设置随机按钮的可见性（在初始化面板之后调用，否则按钮对象为空）
	 *
	 * @param visibility
	 *            可见性标识
	 */
	public void setRandomButtonVisibiity(int visibility) {
		// FIXME 如何控制在为空的调用的时候的异常处理
		if (_fRandomSelectButton != null) {
			_fRandomSelectButton.setVisibility(visibility);
		}
	}

	/**
	 * 获取当前选择的小球号码集合
	 *
	 * @return 当前选择的小球号码集合
	 */
	public List<Integer> getSelectedNumberList() {
		return _fSelectNumberBallsTable.getSelectedBallNumberList();
	}

	/**
	 * 使用指定集合设置选号面板选中的小球
	 *
	 * @param aSpecifiedNumbers
	 *            指定的选号集合
	 */
	public void selectSpecifiedNumberBallsByList(List<Integer> aSpecifiedNumbers) {
		_fSelectNumberBallsTable.selectSpecifiedNumberBallsByList(aSpecifiedNumbers);
	}

	/**
	 * 清空当前选中的小球
	 */
	public void clearNowSelectedNumberBalls(){
		_fSelectNumberBallsTable.clearNowSelectedNumberBalls();
	}
	
	/**
	 * 随机选择号码小球
	 */
	public void randomSelectNumberBalls(){
		_fRandomSelectButton.clickRandomSelectNumberButton();
	}
}
