package com.ruyicai.android.controller.activity.home.buylotteryhall;

import java.util.List;

import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import com.ruyicai.android.controller.compontent.bar.BetBarInterface;
import com.ruyicai.android.model.bean.NumberBasket;
import com.ruyicai.android.model.bean.lottery.DoubleBallLottery;

/**
 * 彩种选项卡选号页面组，实现了投注栏接口
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-2
 */
public abstract class LotterySwitchTabsActivityGroup extends SwitchTabsActivityGroup implements
		BetBarInterface {
	/**在选号组页面底部显示投注信息，为了避免快速显示信息的时候出现延迟，故整个页面使用该对象显示Toast信息*/
	private Toast _fBottomToast;
	
	/** 当前选择的号码集合 */
	private List<List<Integer>> _fNowSelectedNumberLists;
	/** 号码篮对象：一个彩种多种玩法共用一个号码篮，故声明在此类中 */
	protected NumberBasket _fNumberBasket;
	
	//初始化代码块
	{
		_fNumberBasket = new NumberBasket();
	}

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
			showToastInBottom(betInfoString);
		}
	}

	/**
	 * 在屏幕的底部显示Toast提示信息，整个页面使用同一个Toast对象显示，避免了过快显示Toast的时候，出现的延迟现象
	 * 
	 * @param betInfoString
	 *            显示在Toast中的信息字符串
	 */
	private void showToastInBottom(String aBetInfoString) {
		// 如果当前的Toast对象已经存在，就要创建新对象
		if (_fBottomToast == null) {
			_fBottomToast = Toast.makeText(this, aBetInfoString, Toast.LENGTH_SHORT);
		}
		// 如果Toast对象不存在，则设置当前对象的文本
		else {
			_fBottomToast.setText(aBetInfoString);
		}
		_fBottomToast.show();
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
