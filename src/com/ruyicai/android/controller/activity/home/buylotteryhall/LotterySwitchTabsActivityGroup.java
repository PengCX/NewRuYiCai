package com.ruyicai.android.controller.activity.home.buylotteryhall;

import static com.ruyicai.android.controller.compontent.dialog.DialogType.*;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.SensorManager;
import android.text.SpannableStringBuilder;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.betinformation.BetInformationSwitchTabsActivityGroup;
import com.ruyicai.android.controller.activity.loginorregister.LoginActivity;
import com.ruyicai.android.controller.compontent.bar.BettingBarInterface;
import com.ruyicai.android.controller.compontent.dialog.DialogFactory;
import com.ruyicai.android.model.bean.betinfo.BettingInfo;
import com.ruyicai.android.model.bean.numberbasket.NumberBasket;
import com.ruyicai.android.model.preferences.AppSharedPreferences;

/**
 * 彩种选项卡选号页面组，实现了投注栏接口
 *
 * @author xiang_000
 * @since RYC1.0 2013-11-2
 */
public abstract class LotterySwitchTabsActivityGroup extends SwitchTabsActivityGroup implements
		BettingBarInterface {

	/**是否是清除当前选号小球*/
	public boolean _fIsClearSelectedNumberBall;
	
	
	/** 号码篮对象：一个彩种多种玩法共用一个号码篮，故声明在此类中 */
	public NumberBasket _fNumberBasket;
	/**
	 * 使用了策略模式：在子类中构造了相应的Bettinfo的对象，然后再该类中调用BettingInfo抽象的各种算法方法
	 * 但是具体的各种算法在子类中实现
	 */
	public BettingInfo _fNowSelectBettingInfo;
	/** 在选号组页面底部显示投注信息，为了避免快速显示信息的时候出现延迟，故整个页面使用该对象显示Toast信息 */
	private Toast _fBottomToast;

	/**应用共享参数*/
	private AppSharedPreferences _fAppSharedPreferences;
	
	/**传感器管理对象*/
	private SensorManager _fSensorManager;
	

	// 初始化代码块
	{
		// 初始化号码蓝对象
		_fNumberBasket = new NumberBasket();
	}

	/**
	 * 设置当前选择的投注信息对象
	 */
	protected abstract void createNowBettingInfo();

	@Override
	protected void onStart() {
		super.onStart();
		_fSwitchTabHost.setOnTabChangedListener(new SwitchTabsOnTabChangeListener());
		_fBetBar.set_fBetBarInterface(this);
		//使用了管着者模式：将投注栏填加为号码篮子的观察者
		_fNumberBasket.addNumberBasketObserver(_fBetBar);
	}

	@Override
	public void setClearSelectedNumberButton() {
		((LotteryViewPagerActivity) getCurrentActivity()).clearNowAllSelectedNumbers();
	}

	@Override
	public void setNumberBasketButton() {
		//当号码篮子有投注号码的时候，才显示号码篮子对话框
		if(_fNumberBasket.getBettingInfoNumber() > 0){
			showDialog(NUMBERBASKET_SHOWBETTINGINFO_DIALOG.ordinal());
		}else{
			showToastInBottom("号码篮为空");
		}
	}

	@Override
	public void setBettingButton() {
		if(isAlreadyLogged()){
			Intent intent = new Intent(this, BetInformationSwitchTabsActivityGroup.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent(this,LoginActivity.class);
			startActivity(intent);
		}

	}

	private boolean isAlreadyLogged() {
		//初始化应用共享参数对象
		_fAppSharedPreferences = AppSharedPreferences.getInstance(this);

		return _fAppSharedPreferences.getBoolean(
				getString(R.string.sharedpreferences_alreadylogged_key), false);

	}

	@Override
	public void setAddToNumberBasketButton() {
		//如果当前页面还没有进行选号
		if (_fNowSelectBettingInfo == null) {
			showToastInBottom("请选择投注号码");
		} else {
			// 如果当前页面选中的投注信息合法，则将当前选中的投注信息加入到号码篮子中
			if (_fNowSelectBettingInfo.get_fIsLegitimacy()) {
				_fNumberBasket.addBettingInfo(_fNowSelectBettingInfo);
				// 加入完成后，清空当前选中的选号小球
				((LotteryViewPagerActivity) getCurrentActivity()).clearNowAllSelectedNumbers();
			} // 如果不合法，则提示不合法信息
			else {
				showToastInBottom(_fNowSelectBettingInfo.getNotLegitimacyPromptString());
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int aId) {
		/**
		 * 注意：onCreateDialog(aid)方法只会第一次创建的对话对象，接下来该对话框会被保持在当前的Activity中
		 *当你再次调用showDialog(aid)方法的时候就恢复，这可能导致有些对话框中的信息无法更新。可以通过on
		 *PrepareDialog(aid)方法，在对话框显示之前进行预处理。
		 */
		DialogFactory dialogFactory = new DialogFactory(this);
		return  dialogFactory.createDialogById(aId);
	}

	@Override
	public void updateNowBettingInfoShow() {
		// 初始化投注信息对象，设置投注类型，投注号码集合
		createNowBettingInfo();
		// 获取当前显示Activity选中的小球号码
		List<List<Integer>> nowSelectedNumberLists = ((LotteryViewPagerActivity) getCurrentActivity())
				.getNowSelectNumberLists();
		_fNowSelectBettingInfo.set_fBettingType(_fSwitchTabHost.getCurrentTab());
		_fNowSelectBettingInfo.set_fBettingNumberLists(nowSelectedNumberLists);

		// 更新投注栏已选号码显示
		SpannableStringBuilder formatedSpannableStringBuilder = new SpannableStringBuilder("已选：");
		formatedSpannableStringBuilder.append(_fNowSelectBettingInfo
				.get_fFormatedSpannelStringBuilder());
		_fBetBar.updateSelectedNumberShow(formatedSpannableStringBuilder);

		// 如果当前选择的投注信息合法，并且不是代码清除选号面板选择号码的时候，则在页面的底部显示投注信息
		if (_fNowSelectBettingInfo.get_fIsLegitimacy() && !_fIsClearSelectedNumberBall) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("共").append(_fNowSelectBettingInfo.get_fNumber()).append("注")
					.append(_fNowSelectBettingInfo.get_fAmount()).append("元");
			showToastInBottom(stringBuilder.toString());
		}
	}

	/**
	 * 在屏幕的底部显示Toast提示信息，整个页面使用同一个Toast对象显示，避免了过快显示Toast的时候，出现的延迟现象
	 *
	 * @param betInfoString
	 *            显示在Toast中的信息字符串
	 */
	private void showToastInBottom(String aBetInfoString) {
		// 如果当前的Toast对象已经存在，就要创建新对象
		if (_fBottomToast == null) {
			_fBottomToast = Toast.makeText(this, aBetInfoString, Toast.LENGTH_SHORT);
		}
		// 如果Toast对象不存在，则设置当前对象的文本
		else {
			_fBottomToast.setText(aBetInfoString);
		}
		_fBottomToast.show();
	}

	/**
	 * 选项卡切换事件监听器
	 * @author xiang_000
	 * @since RYC1.0 2013-11-7
	 */
	class SwitchTabsOnTabChangeListener implements OnTabChangeListener{

		@Override
		public void onTabChanged(String tabId) {
			updateNowBettingInfoShow();
		}

	}
}
