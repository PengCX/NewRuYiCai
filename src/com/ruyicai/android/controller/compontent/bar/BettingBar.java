package com.ruyicai.android.controller.compontent.bar;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.activity.home.buylotteryhall.LotterySwitchTabsActivityGroup;
import com.ruyicai.android.model.bean.numberbasket.NumberBasketObserver;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 投注栏：包含号码篮、清空号码篮、加入号码篮和投注按钮
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-7
 */
public class BettingBar extends LinearLayout implements  NumberBasketObserver {

	/** 上下文对象 */
	private Context _fContext;

	/** 已选号码 */
	private TextView _fSelectedNumberTextView;
	/** 号码篮按钮 */
	private Button _fNumberBasketButton;
	/** 清除选择号码按钮 */
	private Button _fClearSelectNumberButton;
	/** 加入号码篮按钮 */
	private Button _fAddToNumberBasketButton;
	/** 投注按钮 */
	private Button _fBettingButton;
	/** 投注栏接口对象 */
	private BettingBarInterface _fBetBarInterface;

	{
		// 初始化代码块
		LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.bettingbar, this);

		_fSelectedNumberTextView = (TextView) findViewById(R.id.bettingbar_textview_selectednumber);
		_fNumberBasketButton = (Button) findViewById(R.id.bettingbar_button_numberbasket);
		_fClearSelectNumberButton = (Button) findViewById(R.id.bettingbar_button_clearselectednumber);
		_fAddToNumberBasketButton = (Button) findViewById(R.id.bettingbar_button_addtonumberbasket);
		_fBettingButton = (Button) findViewById(R.id.bettingbar_button_betting);

		_fNumberBasketButton.setOnClickListener(new BettingBarButtonOnClickListener());
		_fClearSelectNumberButton.setOnClickListener(new BettingBarButtonOnClickListener());
		_fAddToNumberBasketButton.setOnClickListener(new BettingBarButtonOnClickListener());
		_fBettingButton.setOnClickListener(new BettingBarButtonOnClickListener());
	}

	/**
	 * 构造方法
	 *
	 * @param context
	 *            上下文对象
	 */
	public BettingBar(Context context) {
		super(context);
	}

	/**
	 * 构造方法
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aAttributeSet
	 *            属性对象
	 */
	public BettingBar(Context aContext, AttributeSet aAttributeSet) {
		super(aContext, aAttributeSet);
		_fContext = aContext;
	}

	/**
	 * 获取投注栏接口
	 *
	 * @return 投注栏接口
	 */
	public BettingBarInterface get_fBetBarInterface() {
		return _fBetBarInterface;
	}

	/**
	 * 获取投注栏接口
	 *
	 * @param _fBarInterface
	 *            投注栏接口
	 */
	public void set_fBetBarInterface(BettingBarInterface _fBarInterface) {
		this._fBetBarInterface = _fBarInterface;
	}

	/**
	 * 更新选中的号码显示
	 * @param aFormatedSpannableStringBuilder 选中号码格式化字符串
	 */
	public void updateSelectedNumberShow(SpannableStringBuilder aFormatedSpannableStringBuilder) {
		_fSelectedNumberTextView.setText(aFormatedSpannableStringBuilder);
	}

	/**
	 * 投注栏按钮事件监听器
	 * @author xiang_000
	 * @since RYC1.0 2013-11-1
	 */
	class BettingBarButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.bettingbar_button_numberbasket:
					_fBetBarInterface.setNumberBasketButton();
					break;
				case R.id.bettingbar_button_clearselectednumber:
					_fBetBarInterface.setClearSelectedNumberButton();
					break;
				case R.id.bettingbar_button_addtonumberbasket:
					_fBetBarInterface.setAddToNumberBasketButton();
					break;
				case R.id.bettingbar_button_betting:
					_fBetBarInterface.setBettingButton();
					break;
				default:
					// 抛出AssertionError错误，当新的按钮添加事件监听时候，忘记处理该按钮，switch语句跳到default语句排除错误提示，易于寻找代码错误。
					throw new AssertionError("投注栏按钮点击事件监听时，没有处理id为：" + v.getId() + "按钮的事件。");
			}
		}
	}

	@Override
	public void updateNumberBasketInfoShow() {
		int bettingInfoNumber = ((LotterySwitchTabsActivityGroup)_fContext)._fNumberBasket.getBettingInfoNumber();
		_fNumberBasketButton.setText(String.valueOf(bettingInfoNumber));
	}
}
