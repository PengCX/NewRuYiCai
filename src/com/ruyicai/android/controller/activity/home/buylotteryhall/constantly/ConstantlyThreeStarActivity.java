package com.ruyicai.android.controller.activity.home.buylotteryhall.constantly;

import android.view.View;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.viewpagers.PlayMethodTextViewInterface;
import com.ruyicai.android.controller.activity.viewpagers.SelectNumberActivity;
import com.ruyicai.android.controller.compontent.bar.BetBarInterface;
import com.ruyicai.android.controller.compontent.panel.SelectNumberPanel;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallType;

public class ConstantlyThreeStarActivity extends SelectNumberActivity implements
		BetBarInterface, PlayMethodTextViewInterface {
	{
//		set_fBettingBarInterface(this);
		set_fPlayMethodTextViewInterface(this);
	}

	@Override
	protected boolean isAddPlayMethodTextView() {
		return true;
	}

	@Override
	protected boolean isAddPageChangeRadioButtons() {
		return false;
	}

	@Override
	public void setPlayMethodTextViewContent() {
		_fPlayMethodTextView
				.setText(R.string.constantly_textview_threestarplaymethod);
	}

	@Override
	protected void setSelectNumberPanelNum() {
		_fNumOfSelectNumberPanel = 3;
	}

	@Override
	protected void initSelectNumberPanelsWithPage(int aPage_i) {
		for (int panel_i = 0; panel_i < _fNumOfSelectNumberPanel; panel_i++) {
			// 获取当前初始化显示的选号面板对象
			SelectNumberPanel selectNumberPanel = _fSelectNumberPanelList.get(
					aPage_i).get(panel_i);

			switch (panel_i) {
			case 0:
				initHundredSelectNumberPanel(aPage_i, selectNumberPanel);
				break;
			case 1:
				initDecadeSelectNumberPanel(aPage_i, selectNumberPanel);
				break;
			case 2:
				initNumberSelectNumberPanel(aPage_i, selectNumberPanel);
				break;
			}
		}
	}

	private void initNumberSelectNumberPanel(int aPage_i,
			SelectNumberPanel selectNumberPanel) {
		if (aPage_i == 0) {
			selectNumberPanel.initSelectNumberPanelShow("个位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, null, false);
		} else {
			int[] lossValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			selectNumberPanel.initSelectNumberPanelShow("个位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, lossValues, true);
		}
		selectNumberPanel.setRandomButtonVisibiity(View.GONE);
	}

	private void initDecadeSelectNumberPanel(int aPage_i,
			SelectNumberPanel selectNumberPanel) {
		if (aPage_i == 0) {
			selectNumberPanel.initSelectNumberPanelShow("十位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, null, false);
		} else {
			int[] lossValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			selectNumberPanel.initSelectNumberPanelShow("十位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, lossValues, true);
		}
		selectNumberPanel.setRandomButtonVisibiity(View.GONE);
	}

	private void initHundredSelectNumberPanel(int aPage_i,
			SelectNumberPanel selectNumberPanel) {
		if (aPage_i == 0) {
			selectNumberPanel.initSelectNumberPanelShow("百位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, null, false);
		} else {
			int[] lossValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			selectNumberPanel.initSelectNumberPanelShow("百位区：", 1, 16, 0, 10,
					SelectNumberBallType.REDBALL, lossValues, true);
		}
		selectNumberPanel.setRandomButtonVisibiity(View.GONE);
	}

	@Override
	public void setNumberBasketButton() {

	}

	@Override
	public void setClearSelectedNumberButton() {

	}

	@Override
	public void setAddToNumberBasketButton() {

	}

	@Override
	public void setBettingButton() {

	}

}
