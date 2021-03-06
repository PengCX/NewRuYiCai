package com.ruyicai.android.controller.activity;

import static com.ruyicai.android.controller.compontent.dialog.DialogType.*;

import com.ruyicai.android.controller.activity.home.accountrecharge.AccountRechargeActivity;
import com.ruyicai.android.controller.activity.home.buylotteryhall.BuyLotteryHallActivity;
import com.ruyicai.android.controller.activity.home.lotterynotice.LotteryNoticeActivity;
import com.ruyicai.android.controller.activity.home.more.MoreActivity;
import com.ruyicai.android.controller.activity.home.usercenter.UserCenterActivity;
import com.ruyicai.android.controller.compontent.dialog.DialogFactory;
import com.ruyicai.android.model.preferences.AppSharedPreferences;

import roboguice.activity.RoboActivity;
import android.app.Dialog;
import android.view.KeyEvent;

/**
 * 如意彩页面基类，实现了所有页面都要实现的相关方法，如： 1.设置屏幕标题和全屏显示属性
 *
 * @author PengCX
 * @since RYC1.0 2013-2-22
 */
public abstract class BaseActivity extends RoboActivity {
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				// 如果在主页面的四个Tab页面点击BACK键，则显示应用程序退出对话框
				if (this instanceof BuyLotteryHallActivity || this instanceof LotteryNoticeActivity
						|| this instanceof AccountRechargeActivity
						|| this instanceof UserCenterActivity || this instanceof MoreActivity) {
					showDialog(APPLICATION_EXIT_DIALOG.ordinal());
				}
				break;
			default:
				throw new AssertionError("switch语句中，没有新增的分支");
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 创建根据对话框ID页面显示的对话框
	 */
	@Override
	protected Dialog onCreateDialog(int aId) {
		// 将所有对话框的创建都集中于此，在任何继承BaseActivity的字类中，只需要调用showDialog(int id)就可以显示对话框了
		DialogFactory dialogFactory = new DialogFactory(this);
		return dialogFactory.createDialogById(aId);
	}
}
