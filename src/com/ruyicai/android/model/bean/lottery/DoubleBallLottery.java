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
