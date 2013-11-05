package com.ruyicai.android.controller.activity.home.buylotteryhall;

import static com.ruyicai.android.controller.compontent.dialog.DialogType.NUMBERBASKET_SHOWBETTINGINFO_DIALOG;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.ruyicai.android.controller.activity.home.buylotteryhall.betinformation.BetInformationSwitchTabsActivityGroup;
import com.ruyicai.android.controller.compontent.bar.BetBarInterface;
import com.ruyicai.android.controller.compontent.dialog.DialogFactory;
import com.ruyicai.android.controller.compontent.dialog.prompt.NumberBasketBettingInfoDialog;
import com.ruyicai.android.model.bean.NumberBasket;
import com.ruyicai.android.model.bean.betinfo.BettingInfo;

/**
 * 彩种选项卡选号页面组，实现了投注栏接口
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-2
 */
public abstract class LotterySwitchTabsActivityGroup extends SwitchTabsActivityGroup implements
		BetBarInterface {
	/** 号码篮对象：一个彩种多种玩法共用一个号码篮，故声明在此类中 */
	public NumberBasket _fNumberBasket;
	/** 当前选择的投注信息 */
	protected BettingInfo _fNowSelectBettingInfo;

	/** 在选号组页面底部显示投注信息，为了避免快速显示信息的时候出现延迟，故整个页面使用该对象显示Toast信息 */
	private Toast _fBottomToast;

	// 初始化代码块
	{
		// 初始化号码蓝对象
		_fNumberBasket = new NumberBasket();
	}

	/**
	 * 初始化当前选择的投注信息对象
	 *
	 * @param aCurrentTabIndex
	 *            当前显示的选项卡索引
	 * @param aNowSelectedNumberLists
	 *            页面当前选择的投注号码集合
	 */
	protected abstract void initNowSelectedBettingInfo(int aCurrentTabIndex,
			List<List<Integer>> aNowSelectedNumberLists);

	@Override
	protected void onStart() {
		super.onStart();
		_fSwitchTabHost.setOnTabChangedListener(new TabHostOnTabChangedListener());

		_fBetBar.set_fBetBarInterface(this);
		_fBetBar.initBetBarShow();
	}

	@Override
	public void setClearSelectedNumberButton() {
		((LotteryViewPagerActivity) getCurrentActivity()).clearNowAllSelectedNumbers();
	}

	@Override
	public void setNumberBasketButton() {
		showDialog(NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal());
	}

	@Override
	public void setBettingButton() {
		Intent intent = new Intent(this, BetInformationSwitchTabsActivityGroup.class);
		startActivity(intent);
	}

	@Override
	public void setAddToNumberBasketButton() {
		// 如果当前页面选中的投注信息合法，则将当前选中的投注信息加入到号码篮子中
		if (_fNowSelectBettingInfo.get_fIsLegitimacy()) {
			_fNumberBasket.addBettingInfo(_fNowSelectBettingInfo);
			// 加入完成后，清空当前选中的选号小球
			((LotteryViewPagerActivity) getCurrentActivity()).clearNowAllSelectedNumbers();
			// 更新投注栏号码篮子投注号码的数目
			_fBetBar.setNumberBasketBetInfoNum(_fNumberBasket.getNumberBasketSize());
		}
		// 如果不合法，则提示不合法信息
		else {
			showToastInBottom(_fNowSelectBettingInfo.getNotLegitimacyPromptString());
		}
	}

	@Override
	protected void onPrepareDialog(int aId, Dialog aDialog) {
		super.onPrepareDialog(aId, aDialog);
		//当时号码篮子对框的时候，通知更新列表信息
		if (aId == NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal()) {
			((NumberBasketBettingInfoDialog) aDialog)._fNumberBasketDialogListViewAdapter
					.notifyDataSetChanged();
		}
	}

	@Override
	protected Dialog onCreateDialog(int aId) {
		//注意：用于通过onCreateDialog(id)创建的Dialog对象，在第一调用创建后会自动保存和恢复，不再调用该方法
		//创建新的对话框对象，故当创建号码篮对话框的时候，由于当中包含ListView，当号码篮子中的投注信息改
		//变的时候，必须调用onPrepareDialog(id,dialog)方法通知列表数据更新
		DialogFactory dialogFactory = new DialogFactory(this);
		return dialogFactory.createDialogById(aId);
	}

	@Override
	public void updateSelectedNumbersShow() {
		// 获取当前显示Activity选中的小球号码
		List<List<Integer>> nowSelectedNumberLists = ((LotteryViewPagerActivity) getCurrentActivity())
				.getSelectedNumberBallNumberLists();
		// 初始化当前选中号码的投注信息对象
		initNowSelectedBettingInfo(_fSwitchTabHost.getCurrentTab(), nowSelectedNumberLists);
		// 获取格式化字符串并显示在投注栏已选号码文本框中
		_fBetBar.setSelectedNumberTextViewText(_fNowSelectBettingInfo.get_fFormatedNumberString());
	}

	/**
	 * 显示当前选择号码的投注信息：如，2注4元
	 */
	public void showNowSelectedBettingInfo() {
		// 如果当前选择的投注信息合法，则在页面的底部显示投注信息
		if (_fNowSelectBettingInfo.get_fIsLegitimacy()) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("共").append(_fNowSelectBettingInfo.get_fNumber()).append("注")
					.append(_fNowSelectBettingInfo.get_fAmount()).append("元");
			showToastInBottom(stringBuilder.toString());
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
	 *
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
