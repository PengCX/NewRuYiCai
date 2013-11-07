package com.ruyicai.android.controller.compontent.bar;

/**
 * 投注栏接口：定义了使用投注栏必须实现的方法
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-18
 */
public interface BettingBarInterface {
	/**
	 * 设置号码篮按钮
	 */
	void setNumberBasketButton();

	/**
	 * 设置清除选中号码按钮
	 */
	void setClearSelectedNumberButton();

	/**
	 * 设置加入号码蓝按钮
	 */
	void setAddToNumberBasketButton();

	/**
	 * 设置投注按钮
	 */
	void setBettingButton();

	/**
	 * 更新已选号码的显示
	 */
	void updateNowSelectBettingInfo();
}
