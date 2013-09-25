package com.ruyicai.android.controller.exception;

/**
 * 获取软件的版本号失败异常
 * 
 * @author Administrator
 * @since RYC1.0 2013-9-18
 */
public class GetSoftVersionFaildException extends Exception {

	public GetSoftVersionFaildException() {
		super();
	}

	public GetSoftVersionFaildException(String detailMessage) {
		super(detailMessage);
	}

}
