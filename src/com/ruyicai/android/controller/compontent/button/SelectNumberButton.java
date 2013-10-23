package com.ruyicai.android.controller.compontent.button;

import com.ruyicai.android.R;
import com.ruyicai.android.controller.compontent.selectnumberpanel.SelectNumberBallType;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 选号小球：有选中和被选中的两个状态，分别显示不同的北背景图片
 * 
 * @author Administrator
 * @since RYC1.0 2013-4-15
 */
public class SelectNumberButton extends Button {
	/** 选号小球的类型：默认是红球类型 */
	private SelectNumberBallType _fSelectNumberBallType = SelectNumberBallType.BLUEBALL;
	/** 选号小球是否选中标识：默认未选中 */
	private boolean _fIsSelected = false;

	public SelectNumberButton(Context context) {
		// FIXME 目前该类没有使用
		super(context);
	}

	public SelectNumberButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.drawable.selectnumberball_grey_background);
		setOnClickListener(new SelectNumberButtonOnClickListener());
	}

	public SelectNumberButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 选号小球实现事件监听类
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-4-15
	 */
	class SelectNumberButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 枚举在switch语句中判断时，是调用original()方法获取枚举声明位置来进行比较的。为了避免调用方法时的NullPointerException，故做此判断
			if (_fSelectNumberBallType != null) {
				switch (_fSelectNumberBallType) {
					case REDBALL:
						if (_fIsSelected) {
							setBackgroundResource(R.drawable.selectnumberball_red_background);
						} else {
							setBackgroundResource(R.drawable.selectnumberball_grey_background);
						}
						break;
					case BLUEBALL:
						if (_fIsSelected) {
							setBackgroundResource(R.drawable.selectnumberball_blue_background);
						} else {
							setBackgroundResource(R.drawable.selectnumberball_grey_background);
						}
						break;
					default:
						throw new AssertionError("switch语句中，没有新增的分支");
				}
				_fIsSelected = !_fIsSelected;
			}
		}

	}
}
