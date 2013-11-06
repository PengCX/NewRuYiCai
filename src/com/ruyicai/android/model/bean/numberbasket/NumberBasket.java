package com.ruyicai.android.model.bean.numberbasket;

import java.util.ArrayList;
import java.util.List;

import com.ruyicai.android.model.bean.betinfo.BettingInfo;

/**
 * 号码篮对象：负责保存投注信息
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-3
 */
public class NumberBasket {
	/** 投注信息集合 */
	private List<BettingInfo> _fbettingInfoList;
	/**总共的注数*/
	private long _fTotalNumber;
	/**总共的金额*/
	private long _fTotalAmount;

	/**
	 * 使用观察者模式：号码篮子为被观察者，观察者实现NumberBasketObserver接口（如投注栏，号码篮子对话框等）。
	 * NumberBakset提供了addObserver()，notifyObervers()等方法添加观察者和通知观察者方法。这样使得观察着能
	 * 在号码篮子中投注信息改变的时候，立即更新号码篮子信息。
	 */
	private List<NumberBasketObserver> _fNumberBasketObservers;

	// 初始化代码块
	{
		_fTotalNumber = -1;
		_fTotalAmount = -1;
	}

	/**
	 *添加号码篮子观察者对象
	 */
	public void addNumberBasketObserver(NumberBasketObserver aNumberBasketObserver){
		if(_fNumberBasketObservers == null){
			_fNumberBasketObservers = new ArrayList<NumberBasketObserver>();
		}
		_fNumberBasketObservers.add(aNumberBasketObserver);
	}

	/**
	 * 通知所以观察者对象
	 */
	public void notifyNumberBasketObervers(){
		int oberserNum = _fNumberBasketObservers.size();
		for(int obe_i = 0; obe_i < oberserNum;obe_i ++){
			_fNumberBasketObservers.get(obe_i).updateNumberBasketInfoShow();
		}
	}

	/**
	 * 向号码篮子添加投注信息
	 *
	 * @param bettingInfo
	 *            添加投注信息对象
	 */
	public void addBettingInfo(BettingInfo bettingInfo) {
		if(_fbettingInfoList == null){
			_fbettingInfoList = new ArrayList<BettingInfo>();
		}
		_fbettingInfoList.add(bettingInfo);

		//通知观察者更新
		notifyNumberBasketObervers();
	}

	/**
	 * 根据需要删除投注信息
	 * @param aIndex
	 */
	public void deleteBettingInfo(int aIndex){
		_fbettingInfoList.remove(aIndex);

		//通知观察者更新
		notifyNumberBasketObervers();
	}

	/**
	 * 获取投注号码的个数
	 *
	 * @return
	 */
	public int getBettingInfoNumber() {
		return _fbettingInfoList.size();
	}

	/**
	 * 获取号码篮子中投注的总共注数
	 * @return
	 */
	public long getTotalBettingNumber(){
		int totalNumber = 0;
		int bettingNum = _fbettingInfoList.size();
		for(int bet_i = 0; bet_i < bettingNum; bet_i ++){
			totalNumber += _fbettingInfoList.get(bet_i).get_fNumber();
		}
		_fTotalNumber = totalNumber;

		return _fTotalNumber;
	}

	/**
	 * 获取号码篮子中投注的总共金额
	 * @return
	 */
	public long getTotalBettingAmount(){
		_fTotalAmount = _fTotalNumber * 2;
		return _fTotalAmount;
	}

	/**
	 * 获取号码篮子投注信息集合
	 *
	 * @return 号码篮子投注信息集合
	 */
	public List<BettingInfo> get_fbettingInfoList() {
		return _fbettingInfoList;
	}
}
