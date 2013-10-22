package com.ruyicai.android.controller.activity.home.more;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.BaseActivity;
import com.ruyicai.android.controller.adapter.listview.SimpleListViewAdapter;
import com.ruyicai.android.controller.compontent.bar.TitleBar;

public class MoreActivity extends BaseActivity {

	/** 视图引用：更多标题栏 */
	@InjectView(R.id.more_title_bar)
	private TitleBar _fTitleBar;
	/** 视图引用：更多列表 */
	@InjectView(R.id.more_listview_items)
	private ListView _fMoreListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_activity);

		// _fTitleBar.set_fTitleBarInterface(new MoreTitleBarInterface());
		// _fTitleBar.initTitleBarShow();

		int[] itemsStringResourceIds = getItemsStringResourceIds();
		SimpleListViewAdapter simpleListViewAdapter = new SimpleListViewAdapter(this,
				itemsStringResourceIds);
		_fMoreListView.setAdapter(simpleListViewAdapter);
	}

	/**
	 * 获取更多列表的字符串资源Id数组
	 * 
	 * @return 字符串资源Id数组
	 */
	private int[] getItemsStringResourceIds() {
		int[] itemsStringResourceIds = new int[9];

		itemsStringResourceIds[0] = R.string.more_listitem_customerhotline;
		itemsStringResourceIds[1] = R.string.more_listitem_helpercenter;
		itemsStringResourceIds[2] = R.string.more_listitem_checkupdate;
		itemsStringResourceIds[3] = R.string.more_listitem_freshmanguide;
		itemsStringResourceIds[4] = R.string.more_listitem_share;
		itemsStringResourceIds[5] = R.string.more_listitem_ifeedback;
		itemsStringResourceIds[6] = R.string.more_listitem_aboutwe;
		itemsStringResourceIds[7] = R.string.more_listitem_settting;
		itemsStringResourceIds[8] = R.string.more_listitem_aboutsoftware;

		return itemsStringResourceIds;
	}
}
