package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import static com.ruyicai.android.controller.compontent.dialog.DialogType.*;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerActivity;
import com.ruyicai.android.controller.activity.home.buylotteryhall.betinformation.BetInformationSwitchTabsActivityGroup;
import com.ruyicai.android.controller.compontent.bar.BettingBar;
import com.ruyicai.android.model.bean.betinfo.BettingInfo;
import com.ruyicai.android.model.bean.lottery.DoubleBallLottery;

/**
 * 双色球选号页面组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-5
 */
public class DoubleBallSwitchTabsActivityGroup extends LotterySwitchTabsActivityGroup {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置该页面的布局，获取布局中控件对象
		setContentView(R.layout.doubleball_switchtabs_activitygroup);
		_fSwitchTabHost = (TabHost) findViewById(R.id.doubleball_switchtabs_activitygroup_tabhost);
		_fBetBar = (BettingBar) findViewById(R.id.doubleball_switchtabs_activitygroup_betbar);
	}

	@Override
	protected void set_fSwithTabSpecClasses() {
		_fSwithTabSpecClasses = new Class<?>[] {
				DoubleBallSelfSelectViewPagerActivity.class,
				DoubleBallCourageSelectViewPagerActivity.class };
	}

	@Override
	protected void set_fSwitchTabSpecTags() {
		_fSwitchTabSpecTagIds = new int[] { R.string.tabhost_textview_selfselect,
				R.string.tabhost_textview_courageselect };
	}

	@Override
	public void setNumberBasketButton() {
		showDialog(NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal());
	}

	@Override
	public void setClearSelectedNumberButton() {
		((LotteryViewPagerActivity)getCurrentActivity()).clearNowAllSelectedNumbers();
	}

	@Override
	public void setAddToNumberBasketButton() {
		// 获取当前选号页面的选择的选号小球
		List<List<Integer>> selectedNumberLists = ((LotteryViewPagerActivity) getCurrentActivity())
				.getSelectedNumberBallNumberLists();

		// 判断当前选择号码的合法性，如果合法，则添加到号码篮子；如果不合法，则提示不合法信息
		String promptString = DoubleBallLottery
				.judgeSelectedNumberListsLegitimacy(selectedNumberLists);
		if (!isLegitimacy(promptString)) {
			Toast.makeText(this, promptString, Toast.LENGTH_SHORT).show();
		}else{
			//初始化投注信息对象
			BettingInfo bettingInfo = new BettingInfo(selectedNumberLists);
			//将投注信息对象添加到号码篮中
			_fNumberBasket.addBettingInfo(bettingInfo);
			Toast.makeText(this, _fNumberBasket.getNumberBasketSize() + "",Toast.LENGTH_SHORT).show();
		}
	}

	private boolean isLegitimacy(String promptString) {
		return "".endsWith(promptString);
	}

	@Override
	public void setBettingButton() {
		Intent intent = new Intent(this, BetInformationSwitchTabsActivityGroup.class);
		startActivity(intent);
	}
}
