package com.ruyicai.android.controller.activity.home.buylotteryhall;

import java.util.List;

import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import com.ruyicai.android.controller.compontent.bar.BetBarInterface;
import com.ruyicai.android.model.bean.NumberBasket;
import com.ruyicai.android.model.bean.lottery.DoubleBallLottery;
import com.ruyicai.android.model.bean.lottery.Lottery;

/**
 * 彩种选项卡选号页面组，实现了投注栏接口
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-2
 */
public abstract class LotterySwitchTabsActivityGroup extends SwitchTabsActivityGroup implements
		BetBarInterface {
	/** 当前选择的号码集合 */
	private List<List<Integer>> _fNowSelectedNumberLists;
	/** 号码篮对象：一个彩种多种玩法共用一个号码篮，故声明在此类中 */
	protected NumberBasket _fNumberBasket;

	@Override
	protected void onStart() {
		super.onStart();
		_fSwitchTabHost.setOnTabChangedListener(new TabHostOnTabChangedListener());

		_fBetBar.set_fBetBarInterface(this);
		_fBetBar.initBetBarShow();
	}

	@Override
	public void updateSelectedNumbersShow() {
		// 获取当前显示Activity选中的小球号码
		_fNowSelectedNumberLists = ((LotteryViewPagerActivity) getCurrentActivity())
				.getSelectedNumberBallNumberLists();
		// 格式化显示小球号码
		_fBetBar.set_fSelectedNumberTextViewText(DoubleBallLottery
				.formatSelectedNumberListsToString(_fNowSelectedNumberLists));
	}

	/**
	 * 显示当前选择号码的投注信息：如，2注4元
	 */
	public void showNowSelectedNumberBettingInfo() {
		if("".equals(DoubleBallLottery.judgeSelectedNumberListsLegitimacy(_fNowSelectedNumberLists))){
			String betInfoString = DoubleBallLottery.calculateNowSelectedNumberBetInfo(_fNowSelectedNumberLists);
			Toast.makeText(this, betInfoString, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * TabHost事件监听器：当TabHost切换选项卡的时候，更新新显示页面选中号码
	 * @author xiang_000
	 * @since RYC1.0 2013-11-3
	 */
	class TabHostOnTabChangedListener implements OnTabChangeListener {

		@Override
		public void onTabChanged(String tabId) {
			updateSelectedNumbersShow();
		}
	}
}
