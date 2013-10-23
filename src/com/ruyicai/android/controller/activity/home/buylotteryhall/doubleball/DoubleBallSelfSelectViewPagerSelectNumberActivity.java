package com.ruyicai.android.controller.activity.home.buylotteryhall.doubleball;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotteryViewPagerSelectNumberActivity;

/**
 * 双色球自选页面：创建页面中的选号面板，并将选号面板添加到布局中；初始化各个选号面板的显示
 * 
 * @author xiang_000
 * @since RYC1.0 2013-4-21
 */
public class DoubleBallSelfSelectViewPagerSelectNumberActivity extends LotteryViewPagerSelectNumberActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubleball_selfselect_viewpager_activity);
		_fViewPager = (ViewPager) findViewById(R.id.doubleball_selfselect_viewpager);
	}

	@Override
	protected List<View> getViewPagerViewList() {
		
		return null;
	}

	// @Override
	// protected boolean isAddPlayMethodTextView() {
	// return false;
	// }
	//
	// @Override
	// protected boolean isAddPageChangeRadioButtons() {
	// return false;
	// }
	//
	// @Override
	// protected void setSelectNumberPanelNum() {
	// _fNumOfSelectNumberPanel = 2;
	// }
	//
	// @Override
	// protected void initSelectNumberPanelsWithPage(int aPage_i) {
	// for (int panel_i = 0; panel_i < _fNumOfSelectNumberPanel; panel_i++) {
	// // 获取当前初始化显示的选号面板对象
	// SelectNumberPanel selectNumberPanel =
	// _fSelectNumberPanelList.get(aPage_i).get(panel_i);
	//
	// switch (panel_i) {
	// case 0:
	// initRedSelectNumberPanel(aPage_i, selectNumberPanel);
	// break;
	// case 1:
	// initBlueSelectNumberPanel(aPage_i, selectNumberPanel);
	// break;
	// default:
	// throw new AssertionError("switch语句中，没有新增的分支");
	// }
	// }
	// }
	//
	// /**
	// * 初始化篮球选号面板
	// *
	// * @param aPage_i
	// * 页面索引
	// * @param selectNumberPanel
	// * 选号面板对象
	// */
	// private void initBlueSelectNumberPanel(int aPage_i, SelectNumberPanel
	// selectNumberPanel) {
	// if (aPage_i == 0) {
	// selectNumberPanel.initSelectNumberPanelShow("篮球区：", 1, 16, 1, 16,
	// SelectNumberBallType.BLUEBALL, null, false);
	// } else {
	// int[] lossValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
	// 14, 15, 16 };
	// selectNumberPanel.initSelectNumberPanelShow("篮球区：", 1, 16, 1, 16,
	// SelectNumberBallType.BLUEBALL, lossValues, true);
	// }
	// }
	//
	// /**
	// * 初始化红球选号面板
	// *
	// * @param aPage_i
	// * 页面索引
	// * @param selectNumberPanel
	// * 选号面板对象
	// */
	// private void initRedSelectNumberPanel(int aPage_i, SelectNumberPanel
	// selectNumberPanel) {
	// if (aPage_i == 0) {
	// selectNumberPanel.initSelectNumberPanelShow("红球区：", 6, 10, 1, 33,
	// SelectNumberBallType.REDBALL, null, false);
	// } else {
	// int[] lossValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
	// 14, 15, 16,
	// 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33 };
	// selectNumberPanel.initSelectNumberPanelShow("红球区：", 6, 10, 1, 33,
	// SelectNumberBallType.REDBALL, lossValues, true);
	// }
	// }
	//
	// /**
	// * 选号 页面投注栏按钮点击监听实现类
	// *
	// * @author xiang_000
	// * @since RYC1.0 2013-4-18
	// */
	// public class DoubleBallSelfSelectBettingBarButtonOnClickListenter
	// implements OnClickListener {
	//
	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.bettingbar_button_numberbasket:
	// Toast.makeText(DoubleBallSelfSelectActivity.this, "你点击了双色球自选号码篮按钮",
	// Toast.LENGTH_LONG).show();
	// break;
	// case R.id.bettingbar_button_clearselectednumber:
	// Toast.makeText(DoubleBallSelfSelectActivity.this, "你点击了双色球自选清除已选择号码按钮",
	// Toast.LENGTH_LONG).show();
	// break;
	// case R.id.bettingbar_button_addtonumberbasket:
	// Toast.makeText(DoubleBallSelfSelectActivity.this, "你点击了双色球自选加入号码篮按钮",
	// Toast.LENGTH_LONG).show();
	// break;
	// case R.id.bettingbar_button_betting:
	// Intent intent = new Intent(DoubleBallSelfSelectActivity.this,
	// BetInformationActivityGroup.class);
	// startActivity(intent);
	// break;
	// default:
	// throw new AssertionError("switch语句中，没有新增的分支");
	// }
	// }
	//
	// }
}
