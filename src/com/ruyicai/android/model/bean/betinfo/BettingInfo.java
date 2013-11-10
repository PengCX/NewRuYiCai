package com.ruyicai.android.model.bean.betinfo;

import java.util.List;
import android.text.SpannableStringBuilder;

/**
 * 投注信息类：保存投注信息
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-3
 */
public abstract class BettingInfo{
	/** 注数 */
	protected long _fNumber;
	/** 金额 */
	protected long _fAmount;
	/** 是否合法 */
	protected boolean _fIsLegitimacy;
	/** 投注类型 */
	protected BettingType _fBettingType;
	/** 格式化投注号码字符串 */
	protected SpannableStringBuilder _fFormatedSpannableStringBuilder;
	/**不合法提示字符串*/
	protected String _fNotLegitimacyString;

	/** 投注号码集合 */
	protected List<List<Integer>> _fBettingNumberLists;

	//初始化代码块，初始化一些属性的默认值
	{
		//注数默认-1
		_fNumber = -1;
		//金额默认-1
		_fAmount = -1;
	}

	/**
	 * 根据选项卡的索引，设置投注类型
	 *
	 * @param aTabIndex
	 *            选项卡索引
	 */
	public abstract void set_fBettingType(int aTabIndex);

	/**
	 * 获取投注号码格式化字符串
	 *
	 * @return 格式化字符串
	 */
	public abstract SpannableStringBuilder get_fFormatedSpannelStringBuilder();

	/**
	 * 获取投注的注数
	 *
	 * @return 投注注数
	 */
	public abstract long get_fNumber();

	/**
	 * 获取投注的金额
	 *
	 * @return 投注的金额
	 */
	public abstract long get_fAmount();

	/**
	 * 判断该投注是否合法
	 *
	 * @return
	 */
	public abstract boolean get_fIsLegitimacy();

	/**
	 * 获取投注不合法提示字符串
	 *
	 * @return 不合法提示字符串
	 */
	public abstract String getNotLegitimacyPromptString();

	/**
	 * 构造方法
	 *
	 * @param aBettingType
	 *            投注类型
	 * @param aBettingNumberLists
	 *            投注号码集合
	 */
	public BettingInfo() {
		super();
	}

	/**
	 * 设置投注号码集合
	 *
	 * @param aBettingNumberLists
	 *            投注号码集合
	 */
	public void set_fBettingNumberLists(List<List<Integer>> aBettingNumberLists) {
		_fBettingNumberLists = aBettingNumberLists;
	}

	/**
	 * 获取投注号码集合
	 *
	 * @return 投注号码集合
	 */
	public List<List<Integer>> get_fBettingNumberLists() {
		return _fBettingNumberLists;
	}
}
