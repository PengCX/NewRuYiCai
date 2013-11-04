package com.ruyicai.android.controller.adapter.listview;

import com.ruyicai.android.R;
import com.ruyicai.android.model.bean.BettingInformation;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 号码栏投注信息对话框列表适配器
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-4
 */
public class NumberBasketBettingDialogListViewAdapter extends BaseAdapter {
	/**上下文对象*/
	private Context _fContext;
	/**投注信息集合*/
	private List<BettingInformation> _fBettingInformations;

	/**
	 * 构造方法
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aBettingInformations
	 *            投注信息集合
	 */
	public NumberBasketBettingDialogListViewAdapter(Context aContext,
			List<BettingInformation> aBettingInformations) {
		super();
		this._fContext = aContext;
		this._fBettingInformations = aBettingInformations;
	}

	@Override
	public int getCount() {
		return _fBettingInformations.size();
	}

	@Override
	public Object getItem(int position) {
		return _fBettingInformations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
		ViewHolder viewHoler = null;
		if (aConvertView == null) {
			viewHoler = new ViewHolder();

			LayoutInflater fInflater = LayoutInflater.from(_fContext);
			aConvertView = fInflater.inflate(R.layout.numberbasket_bettinginfo_dialog_listview_item, null);

			viewHoler._fTextView = (TextView) aConvertView
					.findViewById(R.id.numberbakset_bettinginfo_dialog_listview_item_textview);
			aConvertView.setTag(viewHoler);
		} else {
			viewHoler = (ViewHolder) aConvertView.getTag();
		}

		viewHoler._fTextView.setText(_fBettingInformations.toString());

		return aConvertView;
	}

	static class ViewHolder{
		private TextView _fTextView;
	}
}


