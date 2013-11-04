package com.ruyicai.android.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 号码篮对象：负责保存投注信息
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-3
 */
public class NumberBasket {
	/** 投注信息集合 */
	private List<BettingInformation> _fbettingInfoList;

	// 初始化代码块，初始化投注信息集合
	{
		_fbettingInfoList = new ArrayList<BettingInformation>();
	}


	public List<BettingInformation> get_fbettingInfoList() {
		return _fbettingInfoList;
	}

	/**
	 * 向号码篮子添加投注信息
	 *
	 * @param bettingInfo
	 *            添加投注信息对象
	 */
	public void addBettingInfo(BettingInformation bettingInfo) {
		_fbettingInfoList.add(bettingInfo);
	}

	/**
	 * 获取号码篮子的大小
	 *
	 * @return
	 */
	public int getNumberBasketSize() {
		return _fbettingInfoList.size();
	}
}
