package com.ruyicai.android.controller.activity.home.buylotteryhall;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.adapter.viewpager.ViewPagerAdapter;
import com.ruyicai.android.controller.compontent.panel.SelectNumberPanel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 彩种滑动选号页面
 *
 * @author Administrator
 * @since RYC1.0 2013-10-23
 */
public abstract class LotteryViewPagerActivity extends RoboActivity {
	/** ViewPager对象 */
	protected ViewPager _fViewPager;
	/** ViewPager适配器对象 */
	private ViewPagerAdapter _fViewPagerAdapter;
	/**滑动显示页面的布局资源id数组*/
	protected int[] _fShowLayoutIds;
	/**选号面板id数组*/
	protected int[][] _fSelectNumberPanelIds;

	/**选号页面中选号面板对象集合:一个页面的面板放在一个List集合中，故声明了List<List<E>>类型*/
	protected List<List<SelectNumberPanel>> _fSelectNumberPanelList;
	/**
	 * 设置显示的布局资源id数组
	 */
	protected abstract void setShowLayoutIds();

	/**
	 * 设置选号面板id数组
	 */
	protected abstract void setSelectNumberPanelIds();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottery_viewpager_activity);
		_fViewPager = (ViewPager) findViewById(R.id.lottery_viewpagers_slidearea);
		_fViewPager.setOnPageChangeListener(new ViewPagerOnPagerChangeListener());
	}

	@Override
	protected void onStart() {
		super.onStart();
		// 初始化滑动控件ViewPager显示
		initViewPagerShow();
	}

	/**
	 * 初始化滑动区域ViewPager的显示
	 */
	private void initViewPagerShow() {
		setShowLayoutIds();

		setSelectNumberPanelIds();

		List<View> viewPagerViewList = getViewPagerViewListAndSelectPanels();
		setViewPagerAdapter(viewPagerViewList);
	}

	/**
	 * 获取ViewPager滑动区域显示的滑动视图集合，并从视图对象中获取选号面板对象
	 *
	 * @return 在ViewPaer中要显示的视图集合
	 */
	private List<View> getViewPagerViewListAndSelectPanels() {
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		List<View> viewPageViewList = new ArrayList<View>();
		_fSelectNumberPanelList = new ArrayList<List<SelectNumberPanel>>(
				_fSelectNumberPanelIds.length);

		for (int page_i = 0; page_i < _fShowLayoutIds.length; page_i++) {
			View viewPageView = layoutInflater.inflate(_fShowLayoutIds[page_i], null);
			List<SelectNumberPanel> selectNumberPanels = new ArrayList<SelectNumberPanel>(
					_fSelectNumberPanelIds[page_i].length);

			for (int panel_j = 0; panel_j < _fSelectNumberPanelIds[page_i].length; panel_j++) {
				//必须通过View视图对象，才能获取其中的选号面板对象
				SelectNumberPanel selectNumberPanel = (SelectNumberPanel) viewPageView
						.findViewById(_fSelectNumberPanelIds[page_i][panel_j]);
				selectNumberPanels.add(selectNumberPanel);
			}

			viewPageViewList.add(viewPageView);
			_fSelectNumberPanelList.add(selectNumberPanels);
		}
		return viewPageViewList;
	}

	/**
	 * 设置ViewPager的是适配器
	 *
	 * @param aViewPagerViewList
	 *            ViewPager显示的视图集合
	 */
	private void setViewPagerAdapter(List<View> aViewPagerViewList) {
		_fViewPagerAdapter = new ViewPagerAdapter(aViewPagerViewList);
		_fViewPager.setAdapter(_fViewPagerAdapter);
		_fViewPager.setCurrentItem(0);
	}

	/**
	 * 获取当前页面选号面板选取的选号小球
	 *
	 * @return 当前面板选中的选号小球号码集合
	 */
	public List<List<Integer>> getSelectedNumberBallNumberLists() {
		// 通过当前显示的滑动页面索引，获取选号面板集合
		int pageIndex = _fViewPager.getCurrentItem();
		List<SelectNumberPanel> selectNumberPanels = _fSelectNumberPanelList.get(pageIndex);

		// 遍历集合获取当前选择的选号小球
		int panelNum = selectNumberPanels.size();
		List<List<Integer>> selectedNumberLists = new ArrayList<List<Integer>>();
		for (int panel_i = 0; panel_i < panelNum; panel_i++) {
			SelectNumberPanel selectNumberPanel = selectNumberPanels.get(panel_i);
			List<Integer> selectedNumberList = selectNumberPanel.getSelectedNumberList();
			selectedNumberLists.add(selectedNumberList);
		}
		return selectedNumberLists;
	}

	/**
	 * 同步相关的选号面板的选号小球，即要保证普通选号页面和遗漏值选号页面的选号小球的同步
	 * @param _fNumber 需要同步的小球的号码
	 */
	public void syncRelateSelectNumberPanel() {
		// 设置对应选号面板的选号小球号码
		int relateIndex = (_fViewPager.getCurrentItem() + 1) % _fViewPager.getChildCount();
		List<SelectNumberPanel> relateSelectNumberPanels = _fSelectNumberPanelList.get(relateIndex);

		List<List<Integer>> selectedNumberLists = getSelectedNumberBallNumberLists();
		int panelNum = relateSelectNumberPanels.size();
		for (int panel_i = 0; panel_i < panelNum; panel_i++) {
			SelectNumberPanel selectNumberPanel = relateSelectNumberPanels.get(panel_i);
			List<Integer> selectedNumbers = selectedNumberLists.get(panel_i);
			selectNumberPanel.selectSpecifiedNumberBallsByList(selectedNumbers);
		}
	}

	/**
	 * 清楚当前所有选中的号码
	 */
	public void clearNowAllSelectedNumbers() {
		//获取当前页面选号面板的对象集合
		int currentPage = _fViewPager.getCurrentItem();
		List<SelectNumberPanel> selectNumberPanels = _fSelectNumberPanelList.get(currentPage);

		//遍历面板对象集合，清空面板当前选中的号码
		int panelNum = selectNumberPanels.size();
		for(int  panel_i = 0; panel_i < panelNum; panel_i ++){
			SelectNumberPanel selectNumberPanel = (SelectNumberPanel)selectNumberPanels.get(panel_i);
			selectNumberPanel.clearNowSelectedNumberBalls();
		}
	}

	/**
	 * ViewPager控件事件监听器：当ViewPager进行滑动的时候，使得普通投注页面和遗漏值投注页面的相应选号面板的选号小球相同
	 *
	 * @author xiang_000
	 * @since RYC1.0 2013-11-3
	 */
	class ViewPagerOnPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// 此方法在状态改变的时候调用，arg0表示三种状态：0表示未发送，1表示正在滑动，2表示滑动完毕
			if (arg0 == 1) {
				syncRelateSelectNumberPanel();
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// 此方法当页面滑动的时候调用，在滑动停止前，此方法一直会得到调用。arg0表示当前页面，arg1表示当前页面偏移百分比，arg2表示当前页面偏移的像素位置

		}

		@Override
		public void onPageSelected(int arg0) {
			// 此方法在页面滑动完成后得到调用，arg0是你当前选中的页面的索引
		}
	}
}
