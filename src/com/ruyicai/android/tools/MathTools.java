package com.ruyicai.android.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数学运算工具类：1.获取制定范围内的随机数组
 *
 * @author xiang_000
 * @since RYC1.0 2013-4-10
 */
public final class MathTools {
	/**
	 * 获取制定范围不重复的随机数
	 *
	 * @return 随机数数组
	 */
	/**
	 * 获取指定范围内部重复的随机数
	 *
	 * @param aBegainNumber
	 *            范围开始数字
	 * @param aEndNumber
	 *            范围结束数字
	 * @param aNumberNum
	 *            随机数的个数
	 * @return 随机数集合
	 */
	public static final List<Integer> getSpecifiedRangeRadomNumberWithoutRepetation(int aBegainNumber,
			int aEndNumber, int aNumberNum) {
		List<Integer> randomIntegers = new ArrayList<Integer>();
		Random random = new Random();

		for (int randow_i = 0; randow_i < aNumberNum; randow_i++) {
			// 是否是正确的随机数
			boolean isNotCorrectRandomNumber = true;
			do {
				// 产生范围最大数内随机数
				int randomInt = random.nextInt(aEndNumber);

				// 如果随机数在范围内，并且没有和已经产生的随机数重复
				if (isInRange(randomInt, aBegainNumber, aEndNumber)
						&& isNotRepeation(randomInt, randomIntegers)) {
					randomIntegers.add(randomInt);
					isNotCorrectRandomNumber = false;
				}
			} while (isNotCorrectRandomNumber);
		}

		return randomIntegers;
	}

	/**
	 * 产生的随机数是否与已经产生随机数集合重复
	 *
	 * @param aRandomInt
	 *            产生的随机数
	 * @param aRandomNumbers
	 *            已经产生的随机数集合
	 * @return 是否重复标识
	 */
	private static final boolean isNotRepeation(int aRandomInt, List<Integer> aRandomNumbers) {
		int randomNum = aRandomNumbers.size();

		for (int random_i = 0; random_i < randomNum; random_i++) {
			if (aRandomInt == aRandomNumbers.get(random_i)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 随机数是否在范围内
	 *
	 * @param aRandom
	 *            随机数
	 * @param aBegainNumber
	 *            范围开始数字
	 * @param aEndNumber
	 *            范围接受数组
	 * @return 是否在范围内标识
	 */
	private static final boolean isInRange(int aRandom, int aBegainNumber, int aEndNumber) {
		if (aRandom >= aBegainNumber && aRandom <= aEndNumber) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 排列算法：p(a,b) = a * (a-1) * ... ... *(a-b+1) = a! / (a-b)!
	 *
	 * @param a
	 * @param b
	 * @return 排列结果个数，使用long类型，避免大小越界
	 */
	public static final long permutation(int a, int b) {
		return factorial(a)/factorial(a-b);
	}

	/**
	 * 组合算法：c(a,b) = p(a,b) / b! = a! / ((a-b)! * b!)
	 *
	 * @param a
	 * @param b
	 * @return 组合结果个数，使用long型，避免大小越界
	 */
	public static final long combination(int a, int b) {
		return permutation(a, b) / factorial(b);
	}

	/**
	 * 阶乘算法：n!=n*(n-1)*(n-2)*...*2*1
	 *
	 * @param n
	 *            求阶乘的数
	 * @return 阶乘结果，使用long型，避免大小越界
	 */
	public static final long factorial(int n) {
		return (n == 0) ? 1 : n * factorial(n - 1);
	}
}
