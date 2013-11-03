package com.ruyicai.android.controller.compontent.selectnumberpanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * 选号小球表格：容纳选号小球的表格
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-14
 */
public class SelectNumberBallsTable extends TableLayout {
	/** 上下文对象 */
	private Context _fContext;

	/** 选号面板选号小球的行数 */
	private int _fRowNum;
	/** 选号面板每行的列数 */
	private int _fColumOfPreRow;

	/** 选号小球数组 */
	private SelectNumberBall[] _fSelectNumberBalls;
	/** 选号小球起始的数字*/
	private int _fStartNum;
	/** 选号小球的总个数 */
	private int _fBallNum;
	/** 选号小球的类型 */
	private SelectNumberBallType _fBallType;
	/** 选号小球是否显示遗漏值 */
	private boolean _fIsShowLossValue;
	/** 选号小球遗漏值数组*/
	private int[] _fLossValues;

	// 初始化块，初始化选号小球表格的一些属性
	{
		_fRowNum = 1;
		_fColumOfPreRow = 9;
		_fStartNum = 1;
		_fBallNum = 33;
		_fBallType = SelectNumberBallType.REDBALL;
		_fIsShowLossValue = false;
	}

	/**
	 * 获取选号小球的起始号码
	 *
	 * @return 选号小球的起始号码
	 */
	public int get_fSelectNumberBallStartNum() {
		return _fStartNum;
	}

	/**
	 * 获取选号小球的个数
	 *
	 * @return 选号小球的个数
	 */
	public int get_fSelectBallNum() {
		return _fBallNum;
	}

	/**
	 * 设置是否显示遗漏值标识
	 *
	 * @param _fIsShowLossValue
	 *            显示遗漏值标识
	 */
	public void set_fIsShowLossValue(boolean aIsShowLossValue) {
		_fIsShowLossValue = aIsShowLossValue;
	}

	/**
	 * 设置选号小球起始号码
	 *
	 * @param _fSelectNumberBallStartNum
	 *            选号小球起始号码
	 */
	public void set_fSelectNumberBallStartNum(int _fSelectNumberBallStartNum) {
		this._fStartNum = _fSelectNumberBallStartNum;
	}

	/**
	 * 设置选号小球的个数
	 *
	 * @param _fSelectBallNum
	 *            设置选号小球的个数
	 */
	public void set_fBallNum(int _fSelectBallNum) {
		this._fBallNum = _fSelectBallNum;
	}

	/**
	 * 设置选号小球起始的数字
	 *
	 * @param _fStartNum
	 *            起始数字
	 */
	public void set_fStartNum(int _fStartNum) {
		this._fStartNum = _fStartNum;
	}

	/**
	 * 设置选号小球类型
	 *
	 * @param aBallType
	 *            选号小球类型
	 */
	public void set_fBallType(SelectNumberBallType aBallType) {
		_fBallType = aBallType;
	}

	/**
	 * 构造方法
	 *
	 * @param aContext
	 *            上下文对象
	 */
	public SelectNumberBallsTable(Context aContext) {
		super(aContext);
		_fContext = aContext;
	}

	/**
	 * 构造方法
	 *
	 * @param context
	 *            上下文对象
	 * @param attrs
	 */
	private SelectNumberBallsTable(Context aContext, AttributeSet aAttributeSet) {
		super(aContext, aAttributeSet);
		_fContext = aContext;
	}

	/**
	 * 初始化选号小球表格的显示
	 *
	 * @return
	 */
	public void initSelectNumberBallsTableLayout() {
		caculateRow();

		createSelectNumberBalls();

		addSelectNumberBalls();
	}

	/**
	 * 计算选号面板的行
	 */
	private void caculateRow() {
		_fRowNum = caculateRowOfPannel(_fBallNum);
	}

	/**
	 * 创建选号小球对象的集合
	 */
	private void createSelectNumberBalls() {
		_fSelectNumberBalls = new SelectNumberBall[_fBallNum];

		for (int ball_i = 0; ball_i < _fBallNum; ball_i++) {
			SelectNumberBall selectNumberBall = new SelectNumberBall(_fContext);

			selectNumberBall.setNumber(String.valueOf(_fStartNum + ball_i));
			setSelectNumberBallLossValueWithPosition(selectNumberBall, ball_i, _fIsShowLossValue);
			selectNumberBall.setSelectNumberBallType(_fBallType);

			_fSelectNumberBalls[ball_i] = selectNumberBall;
		}
	}

	/**
	 * 将创建好的选号小球对象集合添加到表格布局中
	 */
	private void addSelectNumberBalls() {
		for (int row_i = 0; row_i < _fRowNum; row_i++) {
			// 创建每行选号小球
			TableRow tableRow = new TableRow(_fContext);

			for (int colum_j = 0; colum_j < _fColumOfPreRow; colum_j++) {
				if (!isLastItem(_fBallNum, row_i, colum_j)) {
					// 创建每列选号小球
					SelectNumberBall selectNumberBall = getSelectNumberBallWithRowAndColum(row_i,
							colum_j);
					android.widget.TableRow.LayoutParams tableRowLayoutParams = new android.widget.TableRow.LayoutParams();
					tableRowLayoutParams.setMargins(1, 1, 1, 1);
					tableRowLayoutParams.gravity = Gravity.LEFT;
					tableRow.addView(selectNumberBall, tableRowLayoutParams);
				} else {
					break;
				}
			}

			// 将行选号小球添加到列表中
			android.widget.TableLayout.LayoutParams tableLayoutLayoutParams = new android.widget.TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			addView(tableRow, tableLayoutLayoutParams);
		}
	}

	/**
	 * 根据选号小球的序号计算在面板中的行数
	 *
	 * @param aBallNum
	 *            选号小球的序号
	 * @return 计算的小球所在行数结果
	 */
	private int caculateRowOfPannel(int aBallNum) {
		if (isFullRow(aBallNum)) {
			return aBallNum / _fColumOfPreRow;
		} else {
			return aBallNum / _fColumOfPreRow + 1;
		}
	}


	/**
	 * 根据小球位置，为小球设置遗漏值
	 *
	 * @param aSelectNumberBall
	 *            选号小球
	 * @param aBall_i
	 *            小球的位置
	 * @param aIsShowLossValue
	 *            是否显示遗漏值
	 */
	private void setSelectNumberBallLossValueWithPosition(SelectNumberBall aSelectNumberBall,
			int aBall_i, boolean aIsShowLossValue) {
		// 如果没有遗漏值，默认为0
		if (_fLossValues == null) {
			aSelectNumberBall.setLossValue("0", aIsShowLossValue);
		} else {
			aSelectNumberBall.setLossValue(String.valueOf(_fLossValues[aBall_i]), aIsShowLossValue);
		}
	}

	/**
	 * 判断第aBallNum选号小球是否是最后一个选项
	 *
	 * @param aBallNum
	 *            判断的选号小球的序号
	 * @param aRow
	 *            被判断的选号小球所在选号面板的行数
	 * @param aColum
	 *            被判断的选号小球所在选号面板的列数
	 * @return 是否是最后一个选项标识
	 */
	private boolean isLastItem(int aBallNum, int aRow, int aColum) {
		int itemIndex = aRow * _fColumOfPreRow + aColum;

		if (itemIndex == aBallNum) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据行和列获取相应的小球对象
	 *
	 * @param aRow
	 *            小球的行数
	 * @param aColum
	 *            小球的列数
	 * @return 获取的指向选号小球对象
	 */
	private SelectNumberBall getSelectNumberBallWithRowAndColum(int aRow, int aColum) {
		return _fSelectNumberBalls[(aRow * _fColumOfPreRow) + aColum];
	}

	/**
	 * 是否是满行
	 *
	 * @param _aBallNum
	 *            选号小球的个数
	 * @return 是否是满行标识
	 */
	private boolean isFullRow(int _aBallNum) {
		return _aBallNum % _fColumOfPreRow == 0;
	}

	/**
	 * 获取选中小球的号码，结果以集合的形式返回，如：List<Integer>={1,2,3,4,5,6};
	 *
	 * @return 选中小球号码集合
	 */
	public List<Integer> getSelectedBallNumberList() {
		List<Integer> selectedNumberList = new ArrayList<Integer>();

		for (int ball_i = 0; ball_i < _fBallNum; ball_i++) {
			SelectNumberBall selectNumberBall = _fSelectNumberBalls[ball_i];

			if (selectNumberBall.isSelected()) {
				selectedNumberList.add(selectNumberBall.getNumber());
			}
		}

		return selectedNumberList;
	}

	/**
	 * 清空选中的号码小球
	 */
	public void clearNowSelectedNumberBalls() {
		for (int ball_i = 0; ball_i < _fBallNum; ball_i++) {
			SelectNumberBall selectNumberBall = _fSelectNumberBalls[ball_i];

			if (selectNumberBall.isSelected()) {
				selectNumberBall.cancelSelected();
			}
		}
	}

	/**
	 * 选定指定的小球，小组数组中是指定的小球的位置，如{0,3,4}指定第0,3和4个小球选中（注意，小球的个数从0个开始）
	 *
	 * @param aSpecifiedNumber
	 *            指定小球的号码
	 */
	public void selectSpecifiedNumberBallsByList(List<Integer> aSpecifiedNumbers) {
		// 清空当前选取的小球
		clearNowSelectedNumberBalls();

		// 遍历所有小球，并根据指定的号码集合设置相应的小球为选中
		int specifiedNum = aSpecifiedNumbers.size();
		for (int ball_i = 0; ball_i < specifiedNum; ball_i++) {
			// 注意：小球对象数组是从0开始，小球编号是从1开始，所有做了-1的处理
			_fSelectNumberBalls[aSpecifiedNumbers.get(ball_i) - 1].setSelected();
		}
	}
}
