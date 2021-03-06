package com.ruyicai.android.controller.compontent.dialog.prompt;

import com.ruyicai.android.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 提示对话框类：拥有标题，内容，确定、取消和其它按钮布局。在使用的时候，根据具体的需要，实现相关的方法，分别设置标题，内容视图，按钮事件和显示属性等等。
 * 由于对话框之间存在工程的oncreate逻辑，故使用抽象类
 *
 * @author xiang_000
 * @since RYC1.0 2013-3-30
 */
public abstract class PromptDialogAbstract extends AlertDialog {

	/** 上下文对象 */
	protected Context _fContext;
	/** 资源对象 */
	protected Resources _fResources;

	/** 标题文本 */
	protected String _fTitleString;
	/** 内容视图 */
	protected View _fContentView;
	/** 确定按钮文本 */
	protected String _fPositiveButtonString;
	/** 其它按钮文本 */
	protected String _fOtherButtonString;
	/** 取消按钮文本 */
	protected String _fNegativeButtonString;

	/** 对话框视图 */
	private View _fDialogView;

	public PromptDialogAbstract(Context aContext) {
		super(aContext);
		_fContext = aContext;
		_fResources = _fContext.getResources();
	}

	/**
	 * 设置提示对话框标题文本
	 */
	public abstract void set_fTitleString();

	/**
	 * 设置提示对话框内容视图
	 */
	public abstract void set_fContentView();

	/**
	 * 设置提示对话框确定按钮文本（没有文本则不显示）
	 */
	public abstract void set_fPositiveButton();

	/**
	 * 设置提示对话框其它按钮文本（没有文本则不显示）
	 */
	public abstract void set_fOtherButton();

	/**
	 * 设置提示对话框取消按钮文本（没有文本则不显示）
	 */
	public abstract void set_fNegativeButton();
	/**
	 * 设置提示对话框确定按钮点击事件（没有该按钮，则空实现）
	 */
	public abstract void setPositiveButtonOnClick();
	/**
	 * 设置提示对话框其它按钮点击事件（没有则空实现）
	 */
	public abstract void setOtherButtonOnClick();
	/**
	 * 设置提示对话框取消按钮点击事件（没有则空实现）
	 */
	public abstract void setNegativeButtonOnClick();

	/**
	 * 设置对话框视图
	 *
	 * @param aContentView
	 *            视图对象
	 * @return Builder对象
	 */
	public void set_fView() {
		LayoutInflater inflater = (LayoutInflater) _fContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_fDialogView = inflater.inflate(R.layout.prompt_dialog, null);
	}

	/**
	 * 创建软件更新对话框按钮
	 *
	 */
	public void create(final Dialog aDialog) {

		// 设置对话框布局
		assert (_fDialogView == null) : "错误：没有设置软件更新对话框的布局视图对象！";
		// FIXME 添加该行代码，使得对话框无边框！！！
		aDialog.show();
		aDialog.getWindow().setContentView(_fDialogView);

		// 设置标题文本
		TextView titleTextView = ((TextView) _fDialogView
				.findViewById(R.id.promptdialog_textview_title));
		if (_fTitleString != null) {
			titleTextView.setText(_fTitleString);
		} else {
			titleTextView.setVisibility(View.GONE);
		}

		// 设置内容填充视图
		LinearLayout contentLinearLayout = ((LinearLayout) _fDialogView
				.findViewById(R.id.promptdialog_linearlayout_container));
		if (_fContentView != null) {
			contentLinearLayout.addView(_fContentView);
		} else {
			contentLinearLayout.setVisibility(View.GONE);
		}

		// 设置确定按钮
		Button positiveButton = ((Button) _fDialogView
				.findViewById(R.id.promptdialog_button_positive));
		if (_fPositiveButtonString != null) {
			positiveButton.setText(_fPositiveButtonString);
			positiveButton.setOnClickListener(new PromptDialogButtonOnclickListener());
		} else {
			// 如果没有确定按钮，仅仅设置该按钮可见性为GONE
			positiveButton.setVisibility(View.GONE);
		}

		// 设置其它按钮
		Button otherButton = ((Button) _fDialogView.findViewById(R.id.promptdialog_button_other));
		if (_fOtherButtonString != null) {
			otherButton.setText(_fOtherButtonString);
			otherButton.setOnClickListener(new PromptDialogButtonOnclickListener());
		} else {
			// 如果没有确定按钮，仅仅设置该按钮可见性为GONE
			otherButton.setVisibility(View.GONE);
		}

		// 设置取消按钮
		Button negativeButton = ((Button) _fDialogView
				.findViewById(R.id.promptdialog_button_negative));
		if (_fNegativeButtonString != null) {
			negativeButton.setText(_fNegativeButtonString);
			negativeButton.setOnClickListener(new PromptDialogButtonOnclickListener());
		} else {
			// 如果没有取消按钮，仅仅设置该按钮的可见性为GONE
			negativeButton.setVisibility(View.GONE);
		}

		// 如果没有按钮，则隐藏按钮栏
		if (_fPositiveButtonString == null && _fPositiveButtonString == null
				&& _fOtherButtonString == null) {
			LinearLayout linearLayout = ((LinearLayout) _fDialogView
					.findViewById(R.id.promptdialog_linearlayout_buttons));
			linearLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 提示对话框按钮事件监听器
	 *
	 * @author xiang_000
	 * @since RYC1.0 2013-11-6
	 */
	class PromptDialogButtonOnclickListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.promptdialog_button_positive:
					setPositiveButtonOnClick();
					break;
				case R.id.promptdialog_button_other:
					setOtherButtonOnClick();
					break;
				case R.id.promptdialog_button_negative:
					setNegativeButtonOnClick();
					break;
				default:
					break;
			}
		}
	}
}
