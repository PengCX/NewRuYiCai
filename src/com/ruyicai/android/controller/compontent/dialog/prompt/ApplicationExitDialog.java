package com.ruyicai.android.controller.compontent.dialog.prompt;

import com.ruyicai.android.R;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

/**
 * 应用程序退出对话框类：退出应用程序提醒对话框
 *
 * @author xiang_000
 * @since RYC1.0 2013-3-30
 */
public class ApplicationExitDialog extends PromptDialogAbstract {

	public ApplicationExitDialog(Context context) {
		super(context);
	}

	@Override
	public void set_fTitleString() {
		_fTitleString = _fResources.getString(R.string.exitapplicationdialog_title_prompt);
	}

	@Override
	public void set_fContentView() {
		_fContentView = new TextView(_fContext);
		// FIXME 如果设置字体的样式
		((TextView) _fContentView).setTextColor(Color.BLACK);
		((TextView) _fContentView).setTextSize(18);
		((TextView) _fContentView).setText(R.string.exitapplicationdialog_message_exit);
	}

	@Override
	public void set_fPositiveButton() {
		_fPositiveButtonString = _fResources
				.getString(R.string.exitapplicationdialog_button_positive);
	}

	@Override
	public void set_fNegativeButton() {
		_fNegativeButtonString = _fResources
				.getString(R.string.exitapplicationdialog_button_negative);
	}

	@Override
	public void set_fOtherButton() {
		// 如果没有该按钮，则空实现
	}

	@Override
	public void setPositiveButtonOnClick() {
		//取消对话框
		dismiss();
		//退出当前登陆的用户
		
		//退出应用程序
		System.exit(0);
	}

	@Override
	public void setOtherButtonOnClick() {
		// 如果没有该按钮，则空实现
	}

	@Override
	public void setNegativeButtonOnClick() {
		dismiss();
	}
}
