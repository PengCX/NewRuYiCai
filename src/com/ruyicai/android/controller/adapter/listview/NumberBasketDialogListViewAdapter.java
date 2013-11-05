package com.ruyicai.android.controller.adapter.listview;

import com.ruyicai.android.R;
import com.ruyicai.android.model.bean.betinfo.BettingInfo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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

	/**
	 * 构造方法
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aBettingInformations
	 *            投注信息集合
	 */
	public NumberBasketDialogListViewAdapter(Context aContext,
			List<BettingInfo> aBettingInformations) {
		super();
		this._fContext = aContext;
		this._fBettingInformations = aBettingInformations;
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
		viewHoler._fSerialTextView.setText(String.valueOf(aPosition));
		viewHoler._fBettingNumberTextView.setText(bettingInformation.get_fFormatedNumberString());
		viewHoler._fBettingInfoTextView.setText(String.valueOf(bettingInformation.get_fNumber()));

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
}


