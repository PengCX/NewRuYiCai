package com.ruyicai.android.controller.activity.home.buylotteryhall.switchtabs;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.betinformation.BetInformationActivityGroup;
import com.ruyicai.android.controller.compontent.bar.BetBar;
import com.ruyicai.android.controller.compontent.bar.BetBarInterface;
import com.ruyicai.android.controller.compontent.bar.LotteryInformationBar;
import com.ruyicai.android.controller.compontent.dropdownmenu.TitleDropDownMenu;
import com.ruyicai.android.model.bean.lottery.LotteryType;

/**
 * 彩种投注选项卡切换页面：主要处理在选项卡切换基类的基础上，其它的事物，如标题按钮的下拉菜单的生成
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public abstract class LotterySwitchTabsActivityGroup extends
		SwitchTabsActivityGroup implements BetBarInterface {
	/** 引用视图：彩种信息栏占位 */
	@InjectView(R.id.switchtabs_activitygroup_lotteryinfomationbar_viewstub)
	private ViewStub				_fLotteryInformationBarViewStub;
	/** 引用视图：投注栏占位 */
	@InjectView(R.id.switchtabs_activitygroup_betbar_viewstub)
	private ViewStub				_fBetBarViewStub;

	/** 投注栏 */
	private BetBar					_fBetBar;
	/** 彩种信息栏 */
	private LotteryInformationBar	_fLotteryInformationBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置彩种信息栏
		initLotteryInformationBarShow();

		// 设置投注栏接口，初始化显示
		initBetBarShow();

		//重设TabHost的相对布局参数
		reSetTabHostRelativeLayoutParams();
	}

	// FIXME 该函数的名称和逻辑有待改进，可以只返回布尔值。
	private void initLotteryInformationBarShow() {
		_fLotteryInformationBarViewStub.inflate();
		_fLotteryInformationBar = (LotteryInformationBar) findViewById(R.id.switchtabs_activitygroup_lotteryinfomationbar);
		_fLotteryInformationBar
				.initLotteryInformationShow(LotteryType.DOUBLE_BALL);
	}

	private void initBetBarShow() {
		_fBetBarViewStub.inflate();
		_fBetBar = (BetBar) findViewById(R.id.switchtabs_activitygroup_betbar);
		_fBetBar.set_fBetBarInterface(this);
		_fBetBar.initBetBarShow();
	}

	/**
	 * 设置TabHost的相对布局参数： 由于彩种投注页面和彩种信息页面的布局不同，所以在彩种投注页面应该重新设置相对布局的参数
	 */
	private void reSetTabHostRelativeLayoutParams() {
		RelativeLayout.LayoutParams relativeLayoutParams = new LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		relativeLayoutParams
				.addRule(
						RelativeLayout.BELOW,
						R.id.switchtabs_activitygroup_lotteryinfomationbar_linearlayout);
		relativeLayoutParams.addRule(RelativeLayout.ABOVE,
				R.id.switchtabs_activitygroup_betbar_linearlayout);
		_fSwitchTabHost.setLayoutParams(relativeLayoutParams);
	}

	@Override
	public void setTitleButton() {
		_fTitleBar._fSpreadButton.setVisibility(View.VISIBLE);
		_fTitleBar._fSpreadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_fTitleBar._fDropDownMenu == null) {
					_fTitleBar._fDropDownMenu = new TitleDropDownMenu(
							LotterySwitchTabsActivityGroup.this);
					_fTitleBar._fDropDownMenu.ShowAsDropDownMenu(v);
				} else {
					_fTitleBar._fDropDownMenu.dismissDropDownMenu();
					_fTitleBar._fDropDownMenu = null;
				}
			}
		});
	}

	@Override
	public void setNumberBasketButton() {
		// FIXME 暂时演示处理，具体后期实现
		_fBetBar._fNumberBasketButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(LotterySwitchTabsActivityGroup.this, "号码篮按钮",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void setClearSelectedNumberButton() {
		// FIXME 暂时演示处理，具体后期实现
		_fBetBar._fClearSelectNumberButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(LotterySwitchTabsActivityGroup.this,
								"清除已选号码按钮", Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	public void setAddToNumberBasketButton() {
		// FIXME 暂时演示处理，具体后期实现
		_fBetBar._fAddToNumberBasketButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(LotterySwitchTabsActivityGroup.this,
								"添加到号码篮按钮", Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	public void setBettingButton() {
		// FIXME 暂时演示处理，具体后期实现
		_fBetBar._fBettingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LotterySwitchTabsActivityGroup.this,
						BetInformationActivityGroup.class);
				startActivity(intent);
			}
		});
	}
}