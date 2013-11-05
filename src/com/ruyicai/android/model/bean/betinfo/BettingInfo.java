package com.ruyicai.android.model.bean.betinfo;

import java.util.List;

/**
 * 投注信息类：保存投注信息
 * 
 * @author xiang_000
 * @since RYC1.0 2013-11-3
 */
public abstract class BettingInfo {
	/** 注数 */
	private long _fNumber;
	/** 金额 */
	private long _fAmount;
	/** 是否合法 */
	protected boolean _fIsLegitimacy;
	/** 投注类型 */
	protected BettingType _fBettingType;
	/** 格式化投注号码字符串 */
	protected String _fFormatedNumberString;

	/** 投注号码集合 */
	protected List<List<Integer>> _fBettingNumberLists;

	/**
	 * 获取投注号码格式化字符串
	 * 
	 * @return 格式化字符串
	 */
	protected abstract String get_fFormatedNumberString();

	/**
	 * 获取投注的注数
	 * 
	 * @return 投注注数
	 */
	protected abstract long get_fNumber();

	/**
	 * 获取投注的金额
	 * 
	 * @return 投注的金额
	 */
	protected abstract long get_fAmount();

	/**
	 * 判断该投注是否合法
	 * 
	 * @return
	 */
	protected abstract boolean get_fIsLegitimacy();
	
	/**
	 * 投注类型枚举：如自选，胆拖
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-11-5
	 */
	enum BettingType {
		SELF_SELECT, COURAGE_SELECT;
	}
}
