package com.ruyicai.android.controller.compontent.dialog;

import static com.ruyicai.android.controller.compontent.dialog.DialogType.*;

import com.ruyicai.android.controller.compontent.dialog.alert.AlertDialogInterface;
import com.ruyicai.android.controller.compontent.dialog.alert.NotConnectedIntenetDialog;
import com.ruyicai.android.controller.compontent.dialog.prompt.ApplicationExitDialog;
import com.ruyicai.android.controller.compontent.dialog.prompt.CompeteFootballPlayMethodChangeDialog;
import com.ruyicai.android.controller.compontent.dialog.prompt.NumberBasketBettingInfoDialog;
import com.ruyicai.android.controller.compontent.dialog.prompt.PromptDialogAbstract;
import com.ruyicai.android.controller.compontent.dialog.prompt.SoftUpdateDialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 对话框工厂类
 *       基本介绍：该工厂类生产警告对话框和提示对话框两种类型的对话框（关于对话框的类型信息，请查阅
 * 		 PromptDialogAbstract类和AlertDialogInterface接口介绍），故有factoryPromptDialogById和fac
 *       toryAlertDialogById两个工厂方法。
 *
 *       使用方法：在两个基类BaseActivity和LotterySwitchTabsActivityGroup中，覆了onCreateDialog(int
 *       id)方法。所以当你在这两个类的子类中，想显示某个对户框的时候，进行如下步骤：
 *       	1.创建你想创建的对画框类，继承PromptDailogAbstract类或者实现AlertDialogInterface接口，根
 *          据要求实现相关的方法。
 *       	2.在DialogType枚举中添加相应的对话框类型枚举。
 *       	3.然后在DialogFactory的isPromptDialog()和isAlert()方法中，添加对话框类型判断条件。
 *          4.根据对户框的类型，在factoryPromptDialogById()或者factoryAlertDialogById()方法中添加创建
 *          对话框的if分支。
 *          5.最后在你想显示对画框的地方调用调用showDialog(intid)方法即可。
 *
 * @author xiang_000
 * @since RYC1.0 2013-3-30
 */
public class DialogFactory {
	/** 上下文对象 */
	private Context _fContext;

	/**
	 * 构造方法
	 *
	 * @param _fContext
	 *            上下文对象
	 */
	public DialogFactory(Context _fContext) {
		super();
		this._fContext = _fContext;
	}

	/**
	 * 根据对话框类型枚举id创建对话框对象
	 *
	 * @param aId
	 *            对话框枚举id
	 * @return 创建的对话框对象
	 */
	public Dialog createDialogById(int aId) {
		Dialog dialog = null;
		if (isAlertDialog(aId)) {
			dialog =  createAlertDialogById(aId);
		} else if (isPromptDialog(aId)) {
			dialog =  createPromptDialogById(aId);
		}
		return dialog;
	}

	/**
	 * 根据对话框类型id创建提示对话框
	 *
	 * @param aId
	 *            对话框类型id
	 * @param dialogFactory
	 *            对话框工厂类
	 * @return 提示对话框对象
	 */
	private Dialog createPromptDialogById(int aId) {
		PromptDialogAbstract promptDialog = factoryPromptDialogById(aId);
		promptDialog.set_fView();
		promptDialog.set_fTitleString();
		promptDialog.set_fContentView();
		promptDialog.set_fPositiveButton();
		promptDialog.set_fOtherButton();
		promptDialog.set_fNegativeButton();

		promptDialog.create(promptDialog);
		return promptDialog;
	}

	/**
	 * 根据对话框类型id创建警告对话框
	 *
	 * @param aId
	 *            对话框类型id
	 * @param dialogFactory
	 *            对话框工厂类
	 * @return 警告对话框对象
	 */
	private Dialog createAlertDialogById(int aId) {
		AlertDialogInterface alertDialog = factoryAlertDialogById(aId);
		alertDialog.setAlertIcon();
		alertDialog.setAlertTitle();
		alertDialog.setAlertMessage();
		alertDialog.setAlertPositiveButton();
		alertDialog.setAlertNegativeButton();
		alertDialog.setAlertNeutralButton();
		return (Dialog) alertDialog;
	}

	/**
	 * 是否是提示对话框
	 *
	 * @param aId
	 *            对话框枚举类型id
	 * @return 是否是提示对话框标识
	 */
	private boolean isPromptDialog(int aId) {
		return aId == SOFTWARE_UPDATE_DIALOG.ordinal() || aId == APPLICATION_EXIT_DIALOG.ordinal()
				|| aId == COMPETEFOOTBALL_PLAYMETHODCHANGE_DIALOG.ordinal()
				|| aId == NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal();
	}

	/**
	 * 是否是静态对话框
	 *
	 * @param aId
	 *            对话框
	 * @return
	 */
	private boolean isAlertDialog(int aId) {
		return aId == NOTCONNECTED_INTENET_DIALOG.ordinal();
	}

	/**
	 * 根据对话框的类型创建提示对话框对象
	 *
	 * @param aId
	 *            对话框类型
	 * @return 提示对话框对象
	 */
	private PromptDialogAbstract factoryPromptDialogById(int aId) {
		PromptDialogAbstract promptDialog = null;
		if (aId == SOFTWARE_UPDATE_DIALOG.ordinal()) {
			promptDialog = new SoftUpdateDialog(_fContext);
		} else if (aId == APPLICATION_EXIT_DIALOG.ordinal()) {
			promptDialog = new ApplicationExitDialog(_fContext);
		} else if (aId == COMPETEFOOTBALL_PLAYMETHODCHANGE_DIALOG.ordinal()) {
			promptDialog = new CompeteFootballPlayMethodChangeDialog(_fContext);
		} else if(aId == NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal()){
			promptDialog = new NumberBasketBettingInfoDialog(_fContext);
		}else {
			// 避免出现没有对话框的类型分支时，抛出错误，易于查找问题
			throw new AssertionError("提示对话框工厂DialogFactory类，根据对话框类型枚举创建对话框实例对象时，没有你需要创建的对话框类型："
					+ aId);
		}
		return promptDialog;
	}

	/**
	 * 根据警告对话框类型创建警告对话框对象
	 *
	 * @param aId
	 *            对话框类型
	 * @return 警告对话框对象
	 */
	private AlertDialogInterface factoryAlertDialogById(int aId) {
		AlertDialogInterface alertDialog = null;

		if (aId == NOTCONNECTED_INTENET_DIALOG.ordinal()) {
			alertDialog = new NotConnectedIntenetDialog(_fContext);
		} else {
			throw new AssertionError("对话框工厂DialogFactory类，根据对话框类型创建警告类型对话框时，没有新增的对话框类型：" + aId);
		}

		return alertDialog;
	}
}
