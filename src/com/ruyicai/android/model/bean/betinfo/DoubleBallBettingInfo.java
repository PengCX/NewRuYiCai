package com.ruyicai.android.model.bean.betinfo;

import static com.ruyicai.android.model.bean.betinfo.BettingType.*;

import java.util.List;

import com.ruyicai.android.tools.MathTools;

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

	@Override
	public SpannableStringBuilder get_fFormatedSpannelStringBuilder() {
		// 如果是自选玩法
		if (isSelfSelect()) {
			_fFormatedSpannableStringBuilder = formatSelfSelectedNumberListsToString();
		}
		// 如果是胆拖玩法
		else if (isCourageSelect()) {
			_fFormatedSpannableStringBuilder = formatCourageSelectNumberListsToString();
		}
		return _fFormatedSpannableStringBuilder;
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
	public long get_fNumber() {
		// 如果已经计算了注数就不再进行计算
		if (_fNumber == -1) {
			if (isSelfSelect()) {
				_fNumber = MathTools.combination(getRedNumberNum(), 6)
						* MathTools.combination(getBlueNumberNum(), 1);
			} else if (isCourageSelect()) {
				_fNumber = MathTools.combination(getCourageNumberNum() + getDragNumberNum(), 6)
						* MathTools.combination(getBlueNumberNum(), 1);
			}
		}
		return _fNumber;
	}

	@Override
	public long get_fAmount() {
		// 如果计算了金额就不再进行计算
		if (_fAmount == -1) {
			_fAmount = get_fNumber() * 2;
		}
		return _fAmount;
	}

	@Override
	public boolean get_fIsLegitimacy() {
		// 如果已经判断了合法性，则不再进行合法性判断
		if (_fIsLegitimacy == false) {
			if (isSelfSelect()) {
				_fIsLegitimacy = isSelfSelectLegitimacy();
			} else if (isCourageSelect()) {
				_fIsLegitimacy = isCourageSelectLegitimacy();
			}
		}
		return _fIsLegitimacy;
	}

	/**
	 * 胆拖是否合法标识
	 *
	 * @return 是否合法标识
	 */
	private boolean isCourageSelectLegitimacy() {
		// 如果是胆拖玩法，当胆码的个数加上拖码的个数大于6个，蓝球的个数大于1个的时候，投注合法
		int courageNum = getCourageNumberNum();
		int dragNum = getDragNumberNum();
		int blueNum = getBlueNumberNum();

		boolean isLegitimacy;
		if ((courageNum + dragNum) >= 6 && blueNum >= 1) {
			isLegitimacy = true;
		} else {
			isLegitimacy = false;
		}

		return isLegitimacy;
	}

	/**
	 * 自选选号是否合法
	 *
	 * @return 是否合法标识
	 */
	private boolean isSelfSelectLegitimacy() {
		// 自选玩法，当红球的个数超过6个，并且蓝球的个数大于1个的时候，投注合法
		int redNum = getRedNumberNum();
		int blueNum = getBlueNumberNum();

		boolean isLegitimacy;
		if (redNum >= 6 && blueNum >= 1) {
			isLegitimacy = true;
		} else {
			isLegitimacy = false;
		}
		return isLegitimacy;
	}

	@Override
	public String getNotLegitimacyPromptString() {
		// 如果已经获取了不合法提示信息字符串，在不再获取
		if (_fNotLegitimacyString == null) {
			if (isSelfSelect()) {
				_fNotLegitimacyString = getSelfSelectNotLegitimacyPromptString();
			} else if (isCourageSelect()) {
				_fNotLegitimacyString = getCourageSelectNotLegitimacyPromptString();
			}
		}
		return _fNotLegitimacyString;
	}

	@Override
	public void set_fBettingType(int aTabIndex) {
		if (aTabIndex == 0) {
			_fBettingType = SELF_SELECT;
		} else if (aTabIndex == 1) {
			_fBettingType = COURAGE_SELECT;
		}
	}

	/**
	 * 获取自选不合法提示信息字符串
	 *
	 * @return 自选不合法提示信息字符串
	 */
	private String getSelfSelectNotLegitimacyPromptString() {
		StringBuilder promptStringBuilder = new StringBuilder();
		int redNum = getRedNumberNum();
		int blueNum = getBlueNumberNum();
		promptStringBuilder.append("您选择了(").append(redNum).append("红+").append(blueNum)
				.append("蓝),请至少再选择");
		if (redNum < 6) {
			promptStringBuilder.append(6 - redNum).append("个红球");
		}
		if (blueNum < 1) {
			promptStringBuilder.append(1 - blueNum).append("个蓝球");
		}
		return promptStringBuilder.toString();
	}

	/**
	 * 获取胆拖不合法提示信息字符串
	 *
	 * @return 胆拖不合法提示信息字符串
	 */
	private String getCourageSelectNotLegitimacyPromptString() {
		StringBuilder promptStringBuilder = new StringBuilder();
		int courageNum = getCourageNumberNum();
		int dragNum = getDragNumberNum();
		int blueNum = getBlueNumberNum();
		promptStringBuilder.append("您选择了(").append(courageNum + dragNum).append("红+")
				.append(blueNum).append("蓝),请至少再选择");

		if ((courageNum + dragNum) < 6) {
			promptStringBuilder.append(6 - (courageNum + dragNum)).append("个红球");
		}
		if (blueNum < 1) {
			promptStringBuilder.append(1 - blueNum).append("个蓝球");
		}

		return promptStringBuilder.toString();
	}

	/**
	 * 获取红球选号个数
	 *
	 * @return 红球选号个数
	 */
	private int getRedNumberNum() {
		return _fBettingNumberLists.get(0).size();
	}

	/**
	 * 获取蓝球选号个数
	 *
	 * @return 蓝球选号个数
	 */
	private int getBlueNumberNum() {
		int blueNum = 0;
		if (isSelfSelect()) {
			blueNum = _fBettingNumberLists.get(1).size();
		} else if (isCourageSelect()) {
			blueNum = _fBettingNumberLists.get(2).size();
		}
		return blueNum;
	}

	/**
	 * 是否是胆拖玩法
	 *
	 * @return 是否是胆拖玩法布尔标识
	 */
	private boolean isCourageSelect() {
		return _fBettingType == COURAGE_SELECT;
	}

	/**
	 * 是否是自选玩法
	 *
	 * @return 是否是自选玩法布尔标识
	 */
	private boolean isSelfSelect() {
		return _fBettingType == SELF_SELECT;
	}

	/**
	 * 获取胆选号个数
	 *
	 * @return 胆选号个数
	 */
	private int getCourageNumberNum() {
		return _fBettingNumberLists.get(0).size();
	}

	/**
	 * 获取拖选号小球的个数
	 *
	 * @return
	 */
	private int getDragNumberNum() {
		return _fBettingNumberLists.get(1).size();
	}
}
