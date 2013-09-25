package com.ruyicai.android.model.bean;

import com.ruyicai.android.controller.exception.GetSoftVersionFaildException;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 应用提供了应用的版本号等基本信息
 * 
 * @author PengCX
 * @since RYC1.0 2013-2-26
 */
public class SoftWareInfo {
	private Context				_fContext;

	/** 单例对象 */
	private static SoftWareInfo	fInstance;

	/** 软件渠道号 */
	public static final String	CHANNEL_ID			= "944";
	/** 传输是否压缩标识 */
	public static final String	ISCOMPRESS			= "1";

	/** 软件是否有更新标识 */
	private boolean				hasSoftWareUpdate	= false;
	/** 软件升级提示信息 */
	private String				softWareUpdateMessage;
	/** 软件升级url */
	private String				softWareUpdateUrl;

	public boolean isHasSoftWareUpdate() {
		return hasSoftWareUpdate;
	}

	public void setHasSoftWareUpdate(boolean hasSoftWareUpdate) {
		this.hasSoftWareUpdate = hasSoftWareUpdate;
	}

	public String getSoftWareUpdateMessage() {
		return softWareUpdateMessage;
	}

	public void setSoftWareUpdateMessage(String softWareUpdateMessage) {
		this.softWareUpdateMessage = softWareUpdateMessage;
	}

	public String getSoftWareUpdateUrl() {
		return softWareUpdateUrl;
	}

	public void setSoftWareUpdateUrl(String softWareUpdateUrl) {
		this.softWareUpdateUrl = softWareUpdateUrl;
	}

	private SoftWareInfo(Context aContext) {
		super();
		_fContext = aContext;
	}

	public static SoftWareInfo getInstance(Context aContext) {
		if (fInstance == null) {
			fInstance = new SoftWareInfo(aContext);
		}

		return fInstance;
	}

	/**
	 * 获取应用程序当前的版本号
	 * 
	 * @return 应用程序版本号字符串
	 */
	public String getVersionName() throws GetSoftVersionFaildException {
		String versionName = null;

		try {
			String packageNameString = _fContext.getPackageName();
			versionName = _fContext.getPackageManager().getPackageInfo(
					packageNameString, 0).versionName;
		} catch (NameNotFoundException e) {
			// 为方便开发人员和维护人员而设置的异常信息
			e.printStackTrace();
			// 抛出业务异常
			throw new GetSoftVersionFaildException("通过包名获取对应的软件版本号时，无法找到该包名");
		}

		return versionName;
	}
}
