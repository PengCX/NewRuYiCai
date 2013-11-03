package com.ruyicai.android.test.model.intenet;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.ruyicai.android.tools.MathTools;

/**
 * 数学工具类测试
 *
 * @author Administrator
 * @since RYC1.0 2013-4-12
 */
public class MathToolsTest extends AndroidTestCase {
	public static final String TAG = "MathToolsTest";

	public void testGetSpecifiedRangeRadomNumberWithoutRepetation() {
		for (int i = 0; i < 1000; i++) {
			List<Integer> randoms = MathTools.getSpecifiedRangeRadomNumberWithoutRepetation(0, 10,
					2);
			Log.i(TAG, randoms.toString() + " " + randoms.size());
		}
	}

	public void testFactorial() {
		Log.i(TAG, "1的阶乘：" + MathTools.factorial(1));
		Log.i(TAG, "2的阶乘：" + MathTools.factorial(2));
		Log.i(TAG, "3的阶乘：" + MathTools.factorial(3));
		Log.i(TAG, "4的阶乘：" + MathTools.factorial(4));
		Log.i(TAG, "5的阶乘：" + MathTools.factorial(5));
		Log.i(TAG, "6的阶乘：" + MathTools.factorial(6));
	}

	public void testPermutation(){
		Log.i(TAG, "p(2,1)的排列：" + MathTools.permutation(2, 1));
		Log.i(TAG, "p(3,2)的排列：" + MathTools.permutation(3, 2));
		Log.i(TAG, "p(4,2)的排列：" + MathTools.permutation(4, 2));
		Log.i(TAG, "p(5,3)的排列：" + MathTools.permutation(5, 3));
	}

	public void testCombination(){
		Log.i(TAG, "c(2,1)的组合：" + MathTools.combination(2, 1));
		Log.i(TAG, "c(3,2)的组合：" + MathTools.combination(3, 2));
		Log.i(TAG, "c(4,2)的组合：" + MathTools.combination(4, 2));
		Log.i(TAG, "c(5,3)的组合：" + MathTools.combination(5, 3));
	}
}
