package com.ruyicai.android.model.bean.betinfo;

import java.util.List;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * 双色球投注信息类
 * 
 * @author Administrator
 * @since RYC1.0 2013-11-5
 */
public class DoubleBallBettingInfo extends BettingInfo {
	/** 红球个数 */
	private int redNum;
	/** 蓝球个数 */
	private int blueNum;
	/** 胆码个数 */
	private int courageNum;
	/** 拖码个数 */
	private int dragNum;
	
	
	@Override
	protected String get_fFormatedNumberString() {
		SpannableStringBuilder formatSpannableStringBuilder = new SpannableStringBuilder();
		// 如果是自选玩法
		if (_fBettingType == BettingType.SELF_SELECT) {
			formatSpannableStringBuilder = formatSelfSelectedNumberListsToString();
		}
		// 如果是胆拖玩法
		else {
			formatSpannableStringBuilder = formatCourageSelectNumberListsToString();
		}
		
		//保存格式化字符串
		_fFormatedNumberString = formatSpannableStringBuilder.toString();
		
		return _fFormatedNumberString;
	}

	/**
	 * 将自选选中小球号码集合格式化成指定的字符串
	 * 
	 * @param aSelectedNumberLists
	 *            选中小球集合
	 * @return 格式化字符串对象
	 */
	private SpannableStringBuilder formatSelfSelectedNumberListsToString() {
		// 格式化红球号码和蓝球号码
		String redFormatString = formatSelectNumberList(_fBettingNumberLists.get(0));
		String blueFormatString = formatSelectNumberList(_fBettingNumberLists.get(1));

		// 为红球和蓝球格式化字符串着色
		SpannableStringBuilder redSpannableStringBuilder = new SpannableStringBuilder(
				redFormatString);
		redSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0,
				redSpannableStringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		SpannableStringBuilder blueSpannableStringBuilder = new SpannableStringBuilder(
				blueFormatString);
		blueSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
				blueSpannableStringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 根据要求将红球和篮球字符串拼接
		SpannableStringBuilder formatSpannableStringBuilder = new SpannableStringBuilder();
		formatSpannableStringBuilder.append(redSpannableStringBuilder);
		if (redSpannableStringBuilder.length() > 0 && blueFormatString.length() > 0) {
			formatSpannableStringBuilder.append("|");
		}
		formatSpannableStringBuilder.append(blueSpannableStringBuilder);

		return formatSpannableStringBuilder;
	}

	/**
	 * 格式化选号小球字符串，返回格式为：1，2，3....（中间使用，分隔符分离，最后一个不分隔）
	 * 
	 * @param aSelectedNumberList
	 * @return
	 */
	private String formatSelectNumberList(List<Integer> aSelectedNumberList) {
		StringBuilder formatStringBuilder = new StringBuilder();

		int numberNum = aSelectedNumberList.size();
		for (int int_i = 0; int_i < numberNum; int_i++) {
			formatStringBuilder.append(aSelectedNumberList.get(int_i));

			// 号码之间使用，分隔符分开 ，最后一个号码不使用分隔符
			if (int_i < numberNum - 1) {
				formatStringBuilder.append(",");
			}
		}
		return formatStringBuilder.toString();
	}

	/**
	 * 将胆拖选中小球号码集合格式化成指定的字符串
	 * 
	 * @param aSelectedNumberLists
	 *            选中小球集合
	 * @param formatSpannableStringBuilder
	 *            格式化字符串
	 */
	private SpannableStringBuilder formatCourageSelectNumberListsToString() {
		// 格式化胆号码，脱号码和蓝球号码
		String courageFormatString = formatSelectNumberList(_fBettingNumberLists.get(0));
		String tuoFormatString = formatSelectNumberList(_fBettingNumberLists.get(1));
		String blueFormaString = formatSelectNumberList(_fBettingNumberLists.get(2));

		// 为胆码，拖码和蓝球着色
		SpannableStringBuilder courageSpannableStringBuilder = new SpannableStringBuilder(
				courageFormatString);
		courageSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0,
				courageFormatString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		SpannableStringBuilder tuoSpannableStringBuilder = new SpannableStringBuilder(
				tuoFormatString);
		tuoSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0,
				tuoFormatString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		SpannableStringBuilder blueSpannableStringBuilder = new SpannableStringBuilder(
				blueFormaString);
		blueSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
				blueFormaString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 根据要求将胆码，脱码和蓝球字符串拼接
		SpannableStringBuilder formatSpannableStringBuilder = new SpannableStringBuilder();
		formatSpannableStringBuilder.append(courageSpannableStringBuilder);
		if (courageSpannableStringBuilder.length() > 0 && tuoSpannableStringBuilder.length() > 0) {
			formatSpannableStringBuilder.append("#");
		}
		formatSpannableStringBuilder.append(tuoSpannableStringBuilder);
		if (tuoSpannableStringBuilder.length() > 0 && blueSpannableStringBuilder.length() > 0) {
			formatSpannableStringBuilder.append("|");
		}
		formatSpannableStringBuilder.append(blueSpannableStringBuilder);

		return formatSpannableStringBuilder;
	}

	@Override
	protected long get_fNumber() {
		return 0;
	}

	@Override
	protected long get_fAmount() {
		return 0;
	}

	@Override
	protected boolean get_fIsLegitimacy() {
		//如果是自选玩法，当红球的个数超过6个，并且蓝球的个数大于1个的时候，投注合法
		if (_fBettingType == BettingType.SELF_SELECT) {
			int redNum = _fBettingNumberLists.get(0).size();
			int blueNum = _fBettingNumberLists.get(1).size();

			if (redNum >= 6 && blueNum >= 1) {
				_fIsLegitimacy = true;
			} else {
				_fIsLegitimacy = false;
			}
		}
		//如果是胆拖玩法，当胆码的个数加上拖码的个数大于6个，蓝球的个数大于1个的时候，投注合法
		else if (_fBettingType == BettingType.COURAGE_SELECT) {
			int courageNum = _fBettingNumberLists.get(0).size();
			int dragNum = _fBettingNumberLists.get(1).size();
			int blueNum = _fBettingNumberLists.get(2).size();

			if ((courageNum + dragNum) >= 6 && blueNum >= 1) {
				_fIsLegitimacy = true;
			} else {
				_fIsLegitimacy = false;
			}
		}

		return _fIsLegitimacy;
	}

}
