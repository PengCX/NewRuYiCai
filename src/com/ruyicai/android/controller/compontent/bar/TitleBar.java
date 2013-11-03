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
	/** 上下文对象 */
	protected Context _fContext;

	/** 左侧标签 */
	private TextView _fLeftTextView;
	/** 右侧按钮 */
	private Button _fRightButton;
	/** 下拉按钮 */
	private Button _fSpreadButton;
	/** 下拉菜单对象 */
	public TitleDropDownMenu _fTitleDropDownMenu;

	/** 左侧标签文本资源id */
	private int _fLeftTextId;
	/** 右按钮文本资源id */
	private int _fRightButtonTextId;
	/** 右按钮是否显示 */
	private boolean _fIsShowRightButton;
	/** 下拉按钮是否显示 */
	private boolean _fIsShowSpreadButton;

	/**标题接口*/
	private TitleBarInterface _fTitleBarInterface;

	//初始化代码块，初始化标题栏根线性布局的属性
	{
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		setLayoutParams(layoutParams);
		setBackgroundResource(R.drawable.promptdialog_title_background);
	}

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

	public void set_fTitleBarInterface(TitleBarInterface aTitleBarInterface) {
		this._fTitleBarInterface = aTitleBarInterface;
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

		// 获取自定义属性
		TypedArray typedArray = _fContext.getTheme().obtainStyledAttributes(aAttributeSet,
				R.styleable.TitleBar, 0, 0);
		try {
			// 左侧文本默认显示：双色球
			_fLeftTextId = typedArray.getResourceId(R.styleable.TitleBar__fLeftTextId,
					R.string.doubleball);
			// 右侧按钮默认显示：登陆注册
			_fRightButtonTextId = typedArray.getResourceId(
					R.styleable.TitleBar__fRightButtonTextId,
					R.string.buylotteryhall_titlebar_rightbuttontext);
			// 右侧按钮显示默认：不显示
			_fIsShowRightButton = typedArray.getBoolean(R.styleable.TitleBar__fIsShowRightButton,
					false);
			// 下拉按钮显示默认：不显示
			_fIsShowSpreadButton = typedArray.getBoolean(R.styleable.TitleBar__fIsShowSpreadButton,
					false);
		} finally {
			typedArray.recycle();
		}

		// 获取标题栏布局
		LayoutInflater layoutInflater = (LayoutInflater) aContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		/**
		 * inflate方法说明：第一个参数resource:是指填充的布局资源id,第二个参数root:是resource布局填充到的根布局。
		 * 相关解释：在这里为设置为root为本身this，将title_bar中的视图以TitleBar（继承RelativeLayout）
		 * 自身为根视图填充， 并且在title_bar布局中使用merge标签，避免深的视图结构。
		 */
		layoutInflater.inflate(R.layout.title_bar, this);

		// 获取标题栏控件并设置相关的属性
		_fLeftTextView = (TextView) findViewById(R.id.titlebar_textview_title);
		_fLeftTextView.setText(_fLeftTextId);

		_fRightButton = (Button) findViewById(R.id.titlebar_button_right);
		_fRightButton.setText(_fRightButtonTextId);
		if (_fIsShowRightButton) {
			_fRightButton.setVisibility(View.VISIBLE);
			_fRightButton.setOnClickListener(new TitleBarButtonOnClickListener());
		}

		_fSpreadButton = (Button) findViewById(R.id.titlebar_button_dropdown);
		if (_fIsShowSpreadButton) {
			_fSpreadButton.setVisibility(View.VISIBLE);
			_fSpreadButton.setOnClickListener(new TitleBarButtonOnClickListener());
		}
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 标题栏按钮点击事件监听器
	 *
	 * @author Administrator
	 * @since RYC1.0 2013-10-23
	 */
	class TitleBarButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.titlebar_button_dropdown:
					//下拉按钮
					if (_fTitleDropDownMenu == null) {
						_fTitleDropDownMenu = new TitleDropDownMenu(_fContext);
					}

					if (!_fTitleDropDownMenu.isTitleDropDownMenuShow()) {
						_fTitleDropDownMenu.showTitleDropDownMenu(_fSpreadButton);
					} else {
						_fTitleDropDownMenu.dismissTitleDropDownMenu();
					}

					break;
				case R.id.titlebar_button_right:
					//右按钮
					_fTitleBarInterface.setRightButton();
					break;

				default:
					// 抛出AssertionError错误，当新的按钮添加事件监听时候，忘记处理该按钮，switch语句跳到default语句排除错误提示，易于寻找代码错误。
					throw new AssertionError("标题栏按钮点击事件监听时，没有处理id为：" + v.getId() + "按钮的事件。");
			}
		}

	}
}
