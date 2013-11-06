package com.ruyicai.android.model.bean.betinfo;
/**
 * 投注信息主题接口
 * @author xiang_000
 * @since RYC1.0 2013-11-7
 */
public interface BettingInfoSubject {
	/**
	 * 添加投注信息观察者对象
	 * @param aBettingInfoObserver 投注信息观察者对象
	 */
	void attachBettingInfoObserver(BettingInfoObserver aBettingInfoObserver);
	/**
	 * 删除投注信息观察者对象
	 * @param aBettingInfoObserver 投注信息观察者对象
	 */
	void detachBettingInfoObserver(BettingInfoObserver aBettingInfoObserver);
	/**
	 * 通知所有的投注信息观察者
	 */
	void notifyBettingInfoObservers();
}
