package com.ruyicai.android.model.preferences;

import com.ruyicai.android.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 应用程序SharedPreferences类：集中保存应用程序全局的共享参数
 * 
 * @author xiang_000
 * @since RYC1.0 2013-3-30
 */
public class AppSharedPreferences {
	/**
	 * 使用了单例模式：该对象保存的是应用的全局信息，故整个应用只需要一个该对象，故使用单例模式
	 */
	private static AppSharedPreferences _fUniqueAppSharedPreferences;
	/** SharedPreferences对象 */
	private SharedPreferences _fSharedPreferences;
	/** Editor对象 */
	private Editor _fEditor;

	/**
	 * 私有构造方法，避免使用new创建对象，必须通过getInstance()才能获取该类的对象
	 * 
	 * @param aContext
	 *            上下文对象
	 */
	
	private AppSharedPreferences(Context aContext) {
		super();
		_fSharedPreferences = aContext.getSharedPreferences(
				aContext.getString(R.string.sharedpreferences_file_key), Context.MODE_PRIVATE);
		_fEditor = _fSharedPreferences.edit();
	}
	
	/**
	 * 获取单例对象
	 * 
	 * @param aContext
	 *            上下文对象
	 * @return 单例对象
	 */
	public static AppSharedPreferences getInstance(Context aContext) {
		if (_fUniqueAppSharedPreferences == null) {
			_fUniqueAppSharedPreferences = new AppSharedPreferences(aContext);
		}

		return _fUniqueAppSharedPreferences;
	}

	/**
	 * 根据关键字获取布尔值
	 * 
	 * @param aKey
	 *            关键字
	 * @param aDefalult
	 *            如果无此值的默认值
	 * @return 获取的布尔值
	 */
	public Boolean getBoolean(String aKey, Boolean aDefalult) {
		return _fSharedPreferences.getBoolean(aKey, aDefalult);
	}

	/**
	 * 设置指定关键字布尔值
	 * 
	 * @param aKey
	 *            关键字
	 * @param 设置的布尔值
	 */
	public void putBoolean(String aKey, boolean aBoolean) {
		_fEditor.putBoolean(aKey, aBoolean);
		_fEditor.commit();
	}
}
