package com.ruyicai.android.controller.compontent.bar;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.dropdownmenu.TitleDropDownMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 标题栏自定义控件
 *
 * @author PengCX
 * @since RYC1.0 2013-3-18
 */
public class TitleBar extends RelativeLayout {
	/** 上下文对象 */
	protected Context					_fContext;

	/** 左侧标题 */
	public TextView						_fLeftTextView;
	/** 右侧按钮 */
	public Button						_fRightButton;
	/** 下拉按钮 */
	public Button						_fSpreadButton;
	/** 下拉菜单对象 */
	public TitleDropDownMenu			_fDropDownMenu;

	/** 左标题文本 */
	private int				_fLeftTextString;
	/** 右按钮文本 */
	private int				_fRightButtonString;
	/** 右按钮是否显示 */
	private Boolean				_fIsShowRightButton;

	/** 右按钮点击监听接口 */
	private OnRightButtonClickListener	_fOnRightButtonClickListener;

	public int get_fLeftTextString() {
		return _fLeftTextString;
	}

	public void set_fLeftTextString(int _fLeftTextString) {
		this._fLeftTextString = _fLeftTextString;
		_fLeftTextView.setText(_fLeftTextString);
	}

	public int get_fRightButtonString() {
		return _fRightButtonString;
	}

	public void set_fRightButtonString(int _fRightButtonString) {
		this._fRightButtonString = _fRightButtonString;
		_fRightButton.setText(_fRightButtonString);
	}

	public Boolean get_fIsShowRightButton() {
		return _fIsShowRightButton;
	}

	public void set_fIsShowRightButton(Boolean _fIsShowRightButton) {
		this._fIsShowRightButton = _fIsShowRightButton;
		if(_fIsShowRightButton){
			_fRightButton.setVisibility(View.VISIBLE);
		}else{
			_fRightButton.setVisibility(View.GONE);
		}
	}

	public OnRightButtonClickListener get_fOnRightButtonClickListener() {
		return _fOnRightButtonClickListener;
	}

	public void set_fOnRightButtonClickListener(
			OnRightButtonClickListener _fOnRightButtonClickListener) {
		this._fOnRightButtonClickListener = _fOnRightButtonClickListener;
		_fRightButton.setOnClickListener(_fOnRightButtonClickListener);
	}

	public TitleBar(Context aContext) {
		super(aContext);
		_fContext = aContext;
	}

	/**
	 * 构造方法
	 *
	 * @param aContext
	 *            上下文对象
	 * @param aAttributeSet
	 *            属性对象
	 */
	public TitleBar(Context aContext, AttributeSet aAttributeSet) {
		super(aContext, aAttributeSet);
		_fContext = aContext;

		//获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.TitleBar, 0, 0);
		try {
			_fLeftTextString = typedArray.getResourceId(R.styleable.TitleBar__fLeftTextString, 0);
			_fRightButtonString = typedArray.getResourceId(R.styleable.TitleBar__fRightButtonString,0);
			_fIsShowRightButton = typedArray.getBoolean(R.styleable.TitleBar__fIsShowRightButton, false);
		} finally {
			typedArray.recycle();
		}

		//获取标题栏布局
		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.title_bar, this);

		//获取标题栏控件并设置相关的属性
		_fLeftTextView = (TextView) findViewById(R.id.titlebar_textview_title);
		_fLeftTextView.setText(_fLeftTextString);

		_fRightButton = (Button) findViewById(R.id.titlebar_button_loginorregister);
		_fRightButton.setText(_fRightButtonString);
		if (_fIsShowRightButton) {
			_fRightButton.setVisibility(View.VISIBLE);
		}
		_fSpreadButton = (Button) findViewById(R.id.titlebar_button_dropdown);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 右按钮点击事件监听器
	 *
	 * @author xiang_000
	 * @since RYC1.0 2013-10-20
	 */
	public interface OnRightButtonClickListener extends OnClickListener {
	}
}
