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
 * 标题栏自定义控件：该控件主要包含控件：左侧标签（_fLeftTextView）和右侧按钮（_fRightButton）两个元素；包含自定义属性：
 * 左侧标签显示的字符串Id
 * （custom:_fLeftTextId）、右侧按钮显示字符串Id（custom:_fRightButtonId）和是否显示右侧按钮
 * （custom:_fIsShowRightButton）。
 * 
 * @author PengCX
 * @since RYC1.0 2013-3-18
 */
public class TitleBar extends RelativeLayout {
	/**
	 * 上下文对象
	 */
	protected Context _fContext;

	/**
	 * 左侧标签
	 */
	private TextView _fLeftTextView;
	/**
	 * 右侧按钮
	 */
	private Button _fRightButton;
	/**
	 * 下拉按钮
	 */
	private Button _fSpreadButton;
	/**
	 * 下拉菜单对象
	 */
	public TitleDropDownMenu _fDropDownMenu;

	/**
	 * 左侧标签文本资源id
	 */
	private int _fLeftTextId;
	/**
	 * 右按钮文本资源id
	 */
	private int _fRightButtonTextId;
	/**
	 * 右按钮是否显示
	 */
	private boolean _fIsShowRightButton;
	/**
	 * 下拉按钮是否显示
	 */
	private boolean _fIsShowSpreadButton;

	/**
	 * 右按钮点击监听接口
	 */
	private OnRightButtonClickListener _fOnRightButtonClickListener;

	/**
	 * 暴露相关的属性，为了在xml文件中初始化意外的地方改变相关的属性
	 */
	public int get_fLeftTextId() {
		return _fLeftTextId;
	}

	/**
	 * 设置标题栏左标签的文本显示
	 * 
	 * @param aLeftTextId
	 *            显示文本的字符串资源id
	 */
	public void set_fLeftTextString(int aLeftTextId) {
		_fLeftTextId = aLeftTextId;
		_fLeftTextView.setText(_fLeftTextId);
	}

	/**
	 * 设置标题栏左标签的文本显示
	 * 
	 * @param aLeftTextString
	 *            显示文本的字符串
	 */
	public void set_fLeftTextString(String aLeftTextString) {
		_fLeftTextView.setText(aLeftTextString);
	}

	public int get_fRightButtonTextId() {
		return _fRightButtonTextId;
	}

	public void set_fRightButtonString(int aRightButtonTextId) {
		_fRightButtonTextId = aRightButtonTextId;
		_fRightButton.setText(_fRightButtonTextId);
	}

	public Boolean get_fIsShowRightButton() {
		return _fIsShowRightButton;
	}

	public void set_fIsShowRightButton(Boolean aIsShowRightButton) {
		_fIsShowRightButton = aIsShowRightButton;
		if (_fIsShowRightButton) {
			_fRightButton.setVisibility(View.VISIBLE);
		} else {
			_fRightButton.setVisibility(View.GONE);
		}
	}

	public OnRightButtonClickListener get_fOnRightButtonClickListener() {
		return _fOnRightButtonClickListener;
	}

	public void set_fOnRightButtonClickListener(
			OnRightButtonClickListener aOnRightButtonClickListener) {
		_fOnRightButtonClickListener = aOnRightButtonClickListener;
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

		// 获取标题栏布局
		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.title_bar, this);

		// 获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.TitleBar, 0, 0);
		try {
			_fLeftTextId = typedArray.getResourceId(R.styleable.TitleBar__fLeftTextId, -1);
			_fRightButtonTextId = typedArray.getResourceId(
					R.styleable.TitleBar__fRightButtonTextId, -1);
			_fIsShowRightButton = typedArray.getBoolean(R.styleable.TitleBar__fIsShowRightButton,
					false);
			_fIsShowSpreadButton = typedArray.getBoolean(R.styleable.TitleBar__fIsShowSpreadButton,
					false);
		} finally {
			typedArray.recycle();
		}

		// 获取标题栏控件并设置相关的属性
		_fLeftTextView = (TextView) findViewById(R.id.titlebar_textview_title);
		if (_fLeftTextId != -1) {
			_fLeftTextView.setText(_fLeftTextId);
		}

		_fRightButton = (Button) findViewById(R.id.titlebar_button_loginorregister);
		if (_fRightButtonTextId != -1) {
			_fRightButton.setText(_fRightButtonTextId);
		}

		if (_fIsShowRightButton) {
			_fRightButton.setVisibility(View.VISIBLE);
		}

		_fSpreadButton = (Button) findViewById(R.id.titlebar_button_dropdown);
		if (_fIsShowSpreadButton) {
			_fSpreadButton.setVisibility(View.VISIBLE);
		}
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
