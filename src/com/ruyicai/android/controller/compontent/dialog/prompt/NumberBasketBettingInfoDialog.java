package com.ruyicai.android.controller.compontent.dialog.prompt;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.adapter.listview.NumberBasketDialogListViewAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 号码篮投注信息对话框：用于在彩种投注页组中投注栏的号码篮按钮点击时，显示号码篮子中的投注号码
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-4
 */
public class NumberBasketBettingInfoDialog extends PromptDialogAbstract {
	/**投注信息列表*/
	private ListView _fBettingInfoListView;
	/**投注信息文本*/
	private TextView _fBettingInfoTextView;
	/**投注信息列表适配器*/
	public NumberBasketDialogListViewAdapter _fNumberBasketDialogListViewAdapter;

	public NumberBasketBettingInfoDialog(Context aContext) {
		super(aContext);
	}

	@Override
	public void set_fTitleString() {
		_fTitleString = _fResources.getString(R.string.doubleball_numberbasket_dialog_title);
	}

	@Override
	public void set_fContentView() {
		LayoutInflater layoutInflater = (LayoutInflater) _fContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_fContentView = layoutInflater.inflate(R.layout.numberbasket_dialog_content,
				null);

		_fBettingInfoListView = (ListView) _fContentView
				.findViewById(R.id.numberbasket_bettinginfo_dialog_listview);
		_fNumberBasketDialogListViewAdapter = new NumberBasketDialogListViewAdapter(_fContext,
				((LotterySwitchTabsActivityGroup) _fContext)._fNumberBasket.get_fbettingInfoList());
		_fBettingInfoListView.setAdapter(_fNumberBasketDialogListViewAdapter);
		_fBettingInfoTextView = (TextView) _fContentView
				.findViewById(R.id.numberbasket_bettinginfo_dialog_textview);
	}

	@Override
	public void set_fPositiveButton() {
		_fPositiveButtonString = _fResources.getString(R.string.doubleball_numberbasket_dialog_positivebutton);
		_fPositiveButtonClickListener = new NumberBasketDialogButtonOnClickListener();
	}

	@Override
	public void set_fOtherButton() {

	}

	@Override
	public void set_fNegativeButton() {
		_fNegativeButtonString = _fResources
				.getString(R.string.doubleball_numberbasket_dialog_negetivebutton);
	}

	/**
	 * 号码篮子对话框按钮点击事件监听器
	 * @author xiang_000
	 * @since RYC1.0 2013-11-5
	 */
	class NumberBasketDialogButtonOnClickListener implements android.view.View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.number:

					break;

				default:
					break;
			}
		}
	}

}
