package com.ruyicai.android.model.intenet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.ruyicai.android.controller.exception.GetSoftVersionFaildException;
import com.ruyicai.android.model.bean.PhoneInfo;
import com.ruyicai.android.model.bean.SoftWareInfo;
import com.ruyicai.android.tools.HttpTools;
import com.ruyicai.android.tools.LogTools;

/**
 * 如意彩网络接口基类
 * 
 * @author PengCX
 * @since RYC1.0 2013-2-26
 */
public abstract class BaseIntenet {
	private static final String TAG = "BaseInterface";

	/** 线程池核心线程数 */
	private static final int CORE_THREAD_SIZE = 5;
	/** 线程池最大线程数 */
	private static final int MAX_THREAD_SIZE = 10;
	/** 额外线程空状态生存时间 */
	private static final int KEEP_ALIVE_TIME = 20;
	/** 线程池中线程运行结束标识 */
	private static final int THREAD_RUN_FINISH = 0;

	/** 使用基本联网接口执行联网操作的上下文对象 */
	private Context _fContext;
	/** 线程池 */
	private static ThreadPoolExecutor _fThreadPoolExecutor;
	/** 阻塞队列-当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程池 */
	private static BlockingQueue<Runnable> _fWorkQueue = new ArrayBlockingQueue<Runnable>(10);
	/** 线程工厂 */
	private static ThreadFactory _fThreadFactory = new ThreadFactory() {
		/** 提供线程安全的自增操作对象 */
		private final AtomicInteger atomicInteger = new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "RuYiCai BaseInterface ThreadPool Thread:"
					+ atomicInteger.getAndIncrement());
		}
	};
	/** 基本网络接口Handler对象-负责在联网后台线程完成后，将后台线程中获取的结果字符串返回 */
	private BaseIntenetHandler _fBaseIntenetHandler;
	/** 基本网络请求结束回调接口 -当联网后台线程完成后，调用该接口中的方法，返回结果字符串 */
	private BaseIntenetCallBackInterface _fBaseIntenetCallBackInterface;
	/** 联网参数Json对象-它使用键值对存储这联网各个参数 */
	private JSONObject _fParamtersJsonObject;

	/** 是否连接正式线标识 */
	private boolean _fIsFormalLine;

	/** 手否添加SIM卡的手机号 */
	private boolean _fAddPhoneSIM;
	/** 是否添加网卡地址 */
	private boolean _fAddMac;

	static {
		_fThreadPoolExecutor = new ThreadPoolExecutor(CORE_THREAD_SIZE, MAX_THREAD_SIZE,
				KEEP_ALIVE_TIME, TimeUnit.SECONDS, _fWorkQueue, _fThreadFactory);

	}

	public BaseIntenet() {
		super();
	}

	public BaseIntenet(Context aContext, boolean aAddPhoneSIM, boolean aAddMac,
			BaseIntenetCallBackInterface aBaseIntenetCallBackInterface) {
		super();
		_fContext = aContext;
		_fAddPhoneSIM = aAddPhoneSIM;
		_fAddMac = aAddMac;
		_fBaseIntenetCallBackInterface = aBaseIntenetCallBackInterface;
	}

	/**
	 * 设置接口特有参数
	 * 
	 * @param aParamtersJsonObject
	 *            将要保存特有参数的Json对象
	 * @return 保存了特有参数的Json对象
	 */
	public abstract JSONObject setParticularParamerters(JSONObject aParamtersJsonObject);

	/**
	 * 获取接口中的JSON字符串数据
	 */
	public void startBackGroundThreadToGetDataFromIntenet() {
		_fParamtersJsonObject = new JSONObject();
		// 设置接口公共参数
		_fParamtersJsonObject = setCommonParameters(_fParamtersJsonObject);
		// 设置接口特有参数
		_fParamtersJsonObject = setParticularParamerters(_fParamtersJsonObject);
		LogTools.showLog(TAG, "发送的联网请求字符串：" + _fParamtersJsonObject.toString(), LogTools.INFO);

		// 将联网Runnalbe对象提高给线程池执行
		_fThreadPoolExecutor.execute(new BaseIntenetRunnable());
	}

	/**
	 * 设置公共的参数
	 * 
	 * @param aContext
	 *            上下文对象
	 * @param aJsonObject
	 *            将要保存公共参数的Json对象
	 * @return 保存了公共参数的Json对象
	 */
	private JSONObject setCommonParameters(JSONObject aJsonObject) {
		PhoneInfo phoneInfo = PhoneInfo.getInstance(_fContext);
		SoftWareInfo softWareInfo = SoftWareInfo.getInstance(_fContext);

		try {
			// 设置用户识别码
			aJsonObject.put("imsi", phoneInfo.getImsi());
			// 设置身份码
			aJsonObject.put("imei", phoneInfo.getImei());
			// 设置版本号
			aJsonObject.put("softwareversion", softWareInfo.getVersionName());
			// 设置手机型号
			aJsonObject.put("machineid", phoneInfo.getMachineid());
			// 设置渠道号
			aJsonObject.put("coopid", SoftWareInfo.CHANNEL_ID);

			if (_fAddPhoneSIM) {
				// 设置SIM卡的手机号
				aJsonObject.put("phoneSIM", phoneInfo.getPhoneSIM());
			}

			// 设置平台
			aJsonObject.put("platform", phoneInfo.getPlatform());

			if (_fAddMac) {
				// 设置网卡地址
				aJsonObject.put("mac", phoneInfo.getMac());
			}

			// 设置是否压缩
			aJsonObject.put("isCompress", SoftWareInfo.ISCOMPRESS);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (GetSoftVersionFaildException e) {
			e.printStackTrace();

		}

		return aJsonObject;
	}

	/**
	 * 基本网络接口的Runnale类，它负责执行基本网络接口的联网操作，并将该类的对象提交给线程池运行
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-9-22
	 */
	private class BaseIntenetRunnable implements Runnable {

		@Override
		public void run() {
			_fBaseIntenetHandler = new BaseIntenetHandler();

			// 连接网络获取结果数据
			String resultJsonString;
			if (_fIsFormalLine) {
				resultJsonString = HttpTools.connectingIntenetForResult(
						"http://www.ruyicai.com/lotserver/RuyicaiServlet",
						HttpTools.POST_METHOD_ID, _fParamtersJsonObject.toString());
			} else {
				resultJsonString = HttpTools.connectingIntenetForResult(
						"http://202.43.152.170:8080/lotserver/RuyicaiServlet",
						HttpTools.POST_METHOD_ID, _fParamtersJsonObject.toString());
			}

			// 获取结果后，使用Handler返回结果数据
			Message message = _fBaseIntenetHandler.obtainMessage();
			message.what = THREAD_RUN_FINISH;
			message.obj = resultJsonString;
			_fBaseIntenetHandler.sendMessage(message);
		}

	}

	/**
	 * 基本网络接口的Handler类：当线程池中的Runnable任务运行结束后，通过该类的对象进行后台线程与主线程之间的通信，返回结果字符串
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-9-22
	 */
	private class BaseIntenetHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
				case THREAD_RUN_FINISH:
					_fBaseIntenetCallBackInterface
							.finishedBackgroundThreadAndGetResultString(msg.obj.toString());
					break;
				default:
					throw new AssertionError("switch语句中，没有新增的分支");
			}
		}
	}

	/**
	 * 基本联网类回调接口：当后台线程完成线程操作的时候，
	 * 自动调用该接口中的finishedBackgroundThreadAndGetResultString
	 * ()方法，通过resultString返回联网请求返回字符串
	 * 
	 * @author Administrator
	 * @since RYC1.0 2013-9-22
	 */
	public interface BaseIntenetCallBackInterface {
		/**
		 * 当联网后台线程运行完成后，自动调用该方法，并将结果字符串通过参数返回
		 * 
		 * @param resultString
		 *            联网获取的结果字符串
		 */
		void finishedBackgroundThreadAndGetResultString(String resultString);
	}
}
