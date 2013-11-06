package com.ruyicai.android.controller.adapter.listview;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.controller.compontent.dialog.prompt.NumberBasketBettingInfoDialog;
import com.ruyicai.android.model.bean.betinfo.BettingInfo;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 号码栏投注信息对话框列表适配器
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-4
 */
public class NumberBasketDialogListViewAdapter extends BaseAdapter {
	/**上下文对象*/
	private Context _fContext;
	/**投注信息集合*/
	private List<BettingInfo> _fBettingInformations;
	/**显示的对话框对象*/
	private Dialog _fDialog;
	/**
	 * 构造方法
	 * @param numberBasketBettingInfoDialog
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aBettingInformations
	 *            投注信息集合
	 */
	public NumberBasketDialogListViewAdapter(Dialog aDialog, Context aContext,
			List<BettingInfo> aBettingInformations) {
		super();
		_fDialog = aDialog;
		_fContext = aContext;
		_fBettingInformations = aBettingInformations;
	}

	@Override
	public int getCount() {
		return _fBettingInformations.size();
	}

	@Override
	public Object getItem(int aPosition) {
		return _fBettingInformations.get(aPosition);
	}

	@Override
	public long getItemId(int aPosition) {
		return aPosition;
	}

	@Override
	public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
		ViewHolder viewHoler = null;
		if (aConvertView == null) {
			viewHoler = new ViewHolder();

			LayoutInflater fInflater = LayoutInflater.from(_fContext);
			aConvertView = fInflater.inflate(R.layout.numberbasket_dialog_listview_item, null);

			viewHoler._fSerialTextView = (TextView) aConvertView
					.findViewById(R.id.numberbasket_dialog_listitem_serialnumber);
			viewHoler._fBettingNumberTextView = (TextView) aConvertView
					.findViewById(R.id.numberbasket_dialog_listitem_bettingnumber);
			viewHoler._fBettingInfoTextView = (TextView) aConvertView
					.findViewById(R.id.numberbasket_dialog_listitem_bettinginfo);
			viewHoler._fDeleteImageButton = (ImageButton) aConvertView
					.findViewById(R.id.numberbasket_dialog_listitem_delete);

			aConvertView.setTag(viewHoler);
		} else {
			viewHoler = (ViewHolder) aConvertView.getTag();
		}

		BettingInfo bettingInformation = _fBettingInformations.get(aPosition);
		viewHoler._fSerialTextView.setText(String.valueOf(aPosition + 1));
		viewHoler._fBettingNumberTextView.setText(bettingInformation.get_fFormatedSpannelStringBuilder());
		StringBuilder bettingInfoStringBuilder = new StringBuilder();
		bettingInfoStringBuilder.append(bettingInformation.get_fNumber()).append("注   ").append(bettingInformation.get_fAmount()).append("元");
		viewHoler._fBettingInfoTextView.setText(bettingInfoStringBuilder.toString());
		viewHoler._fDeleteImageButton.setOnClickListener(new NumberBasketDialogListItemButtonOnClickListener(aPosition));

		return aConvertView;
	}

	static class ViewHolder {
		/** 序号 */
		private TextView _fSerialTextView;
		/** 投注号码 */
		private TextView _fBettingNumberTextView;
		/** 投注信息 */
		private TextView _fBettingInfoTextView;
		/** 删除按钮 */
		private ImageButton _fDeleteImageButton;
	}

	/**
	 * 号码篮子对话框列表项按钮事件监听器
	 * @author xiang_000
	 * @since RYC1.0 2013-11-6
	 */
	class NumberBasketDialogListItemButtonOnClickListener implements OnClickListener{
		/**当前点击列表项的索引*/
		private int _fItemIndex;

		private NumberBasketDialogListItemButtonOnClickListener(int _fItemIndex) {
			super();
			this._fItemIndex = _fItemIndex;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.numberbasket_dialog_listitem_delete:
					//从号码篮子中移除该投注信息
					((LotterySwitchTabsActivityGroup)_fContext)._fNumberBasket.deleteBettingInfo(_fItemIndex);
					break;

				default:
					break;
			}
		}
	}
}


