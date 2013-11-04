package com.ruyicai.android.model.bean.lottery;

import java.util.List;

import com.ruyicai.android.tools.MathTools;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * 双色球彩种类
 *
 * @author PengCX
 * @since RYC1.0 2013-3-21
 */
public class DoubleBallLottery extends Lottery {

	public DoubleBallLottery(LotteryType _fLotteryType, Boolean _fIsNowLottery,
			Boolean _fIsSaleStop, Boolean _fIsReward) {
		super(_fLotteryType, _fIsNowLottery, _fIsSaleStop, _fIsReward);
	}

	/**
	 * 将选中小球号码集合格式化成指定的格式字符串
	 *
	 * @return 格式化字符串
	 */
	public static SpannableStringBuilder formatSelectedNumberListsToString(
			List<List<Integer>> aSelectedNumberLists) {
		SpannableStringBuilder formatSpannableStringBuilder = new SpannableStringBuilder();

		// 如果是自选玩法
		if (isSelfSelect(aSelectedNumberLists)) {
			formatSpannableStringBuilder = formatSelfSelectedNumberListsToString(aSelectedNumberLists);
		}
		// 如果是胆拖玩法
		else if (isCourageSelect(aSelectedNumberLists)) {
			formatSpannableStringBuilder = formatCourageSelectNumberListsToString(aSelectedNumberLists);
		}

		return formatSpannableStringBuilder;
	}

	/**
	 * 将胆拖选中小球号码集合格式化成指定的字符串
	 *
	 * @param aSelectedNumberLists
	 *            选中小球集合
	 * @param formatSpannableStringBuilder
	 *            格式化字符串
	 */
	private static SpannableStringBuilder formatCourageSelectNumberListsToString(
			List<List<Integer>> aSelectedNumberLists) {
		// 格式化胆号码，脱号码和蓝球号码
		String courageFormatString = formatSelectNumberList(aSelectedNumberLists.get(0));
		String tuoFormatString = formatSelectNumberList(aSelectedNumberLists.get(1));
		String blueFormaString = formatSelectNumberList(aSelectedNumberLists.get(2));

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

	/**
	 * 将自选选中小球号码集合格式化成指定的字符串
	 *
	 * @param aSelectedNumberLists
	 *            选中小球集合
	 * @return 格式化字符串对象
	 */
	private static SpannableStringBuilder formatSelfSelectedNumberListsToString(
			List<List<Integer>> aSelectedNumberLists) {
		// 格式化红球号码和蓝球号码
		String redFormatString = formatSelectNumberList(aSelectedNumberLists.get(0));
		String blueFormatString = formatSelectNumberList(aSelectedNumberLists.get(1));

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
	 * 是否是胆拖玩法
	 *
	 * @param aSelectedNumberLists
	 *            选号小球集合
	 * @return 是否是胆拖玩法布尔标识
	 */
	private static boolean isCourageSelect(List<List<Integer>> aSelectedNumberLists) {
		return aSelectedNumberLists.size() == 3;
	}

	/**
	 * 是否是自选玩法
	 *
	 * @param aSelectedNumberLists
	 *            选号小球集合
	 * @return 是否是自选玩法布尔标识
	 */
	private static boolean isSelfSelect(List<List<Integer>> aSelectedNumberLists) {
		return aSelectedNumberLists.size() == 2;
	}

	/**
	 * 格式化选号小球字符串，返回格式为：1，2，3....（中间使用，分隔符分离，最后一个不分隔）
	 *
	 * @param aSelectedNumberList
	 * @return
	 */
	private static String formatSelectNumberList(List<Integer> aSelectedNumberList) {
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
	 * 判断选择的小球号码的合法性
	 *
	 * @param selectedNumberLists
	 */
	public static String judgeSelectedNumberListsLegitimacy(List<List<Integer>> aSelectedNumberLists) {
		List<Integer> redSelectNumberList = aSelectedNumberLists.get(0);
		List<Integer> blueSelectNumberList = aSelectedNumberLists.get(1);
		int redNum = redSelectNumberList.size();
		int blueNum = blueSelectNumberList.size();

		// 如果选择的红球大于等于6个，并且蓝球大于等于1个，则选择号码合法，返回""标识无不合法提示语句，即合法选择号码
		StringBuilder promptStringBuilder = new StringBuilder();
		if (redNum >= 6 && blueNum >= 1) {
			promptStringBuilder.append("");
		} else {
			promptStringBuilder.append("您选择了（").append(redNum).append("红").append(blueNum)
					.append("蓝），请至少再选择");

			if (redNum < 6) {
				promptStringBuilder.append(6 - redNum).append("个红球");
			}

			if (blueNum < 1) {
				promptStringBuilder.append(1 - blueNum).append("个蓝球");
			}
		}
		return promptStringBuilder.toString();
	}

	/**
	 * 计算当前选择投注号码的投注信息
	 *
	 * @param _fNowSelectedNumberLists
	 *            当前选中投注号码集合
	 * @return 投注信息字符串
	 */
	public static String calculateNowSelectedNumberBetInfo(
			List<List<Integer>> aNowSelectedNumberLists) {
		int redNum = aNowSelectedNumberLists.get(0).size();
		int blueNum = aNowSelectedNumberLists.get(1).size();

		long betNumber = MathTools.combination(redNum, 6) * MathTools.combination(blueNum, 1);
		long betAmount = betNumber * 2;

		StringBuilder betInfStringBuilder = new StringBuilder();
		betInfStringBuilder.append("共").append(betNumber).append("注").append(betAmount).append("元");
		return betInfStringBuilder.toString();
	}
}
