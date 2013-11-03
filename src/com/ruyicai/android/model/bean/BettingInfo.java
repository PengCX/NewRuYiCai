package com.ruyicai.android.model.bean;

import java.util.List;

/**
 * 投注信息类：保存投注信息
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-3
 */
public class BettingInfo {
	/** 投注号码集合 */
	private List<List<Integer>> _fBettingNumberLists;

	/**
	 * 构造方法
	 *
	 * @param _fBettingNumberList
	 *            投注号码集合
	 */
	public BettingInfo(List<List<Integer>> aBettingNumberLists) {
		super();
		_fBettingNumberLists = aBettingNumberLists;
	}
}
