package com.ruyicai.android.controller.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;


/**
 * 摇一摇机选传感器类
 * @author Administrator
 * @since RYC1.0 2013-11-11
 */
public abstract class ShakeAccelerometerSensor implements SensorEventListener{
	/**检测间隔时间常量，毫秒*/
	private static long NTERVAL_TIME = 200;
	/**摇晃幅度的阀值*/
	private static float SHAKE_THRESHOLD_VALUE = 20;
	
	/** 上下文对象 */
	private Context _fContext;
	/** 传感器管理器对象 */
	private SensorManager _fSensorManager;
	/** 传感器对象 */
	private Sensor _fSensor;
	
	/** 上一次检测的时间 */
	private long _fLastTime;
	/** 上一次X方向加速度 */
	private float _fLastX;
	/** 上一次Y方向加速度 */
	private float _fLastY;
	/** 上一次Z方向加速度 */
	private float _fLastZ;
	/**摇晃的幅度*/
	private float _fShake;

	/**
	 * 摇一摇触发的动作方法
	 */
	public abstract void actionLogic();
	
	/**
	 * 构造方法
	 * @param aSensorManager 
	 */
	public ShakeAccelerometerSensor(Context aContext) {
		super();
		_fContext = aContext;
		// 获取系统的穿传感器管理服务
		_fSensorManager = (SensorManager) _fContext.getSystemService(Context.SENSOR_SERVICE);
		_fSensor = _fSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	
	/**
	 * 启动传感器
	 */
	public void startSensor() {
		// 为系统的加速度传感器注册监听器
		_fSensorManager.registerListener(this, _fSensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

	/**
	 * 暂停传感器
	 */
	public void stopSensor(){
		_fSensorManager.unregisterListener(this);
	}
	

	@Override
	public void onSensorChanged(SensorEvent event) {
		//计算检测的间隔时间
		long currentTime = System.currentTimeMillis();
		long ntervalTime = currentTime - _fLastTime;
		
		if(ntervalTime > NTERVAL_TIME){
			// 获取加速度传感器的三个参数
			float dataX = event.values[SensorManager.DATA_X];
			float dataY = event.values[SensorManager.DATA_Y];
			float dataZ = event.values[SensorManager.DATA_Z];
			
			// 计算晃动幅度
			_fShake = Math.abs(dataX - _fLastX) + Math.abs(dataY - _fLastY)
					+ Math.abs(dataZ - _fLastZ);

			//如果摇晃的振幅超过阀值，则调用动作方法
			if(_fShake > SHAKE_THRESHOLD_VALUE){
				//执行摇一摇的动作逻辑
				actionLogic();
				//执行震动
				actionVibrator();
				//重置上一次的值
				resetLastValue();
			}
			
			//保存上一次的值
			saveLastValue(currentTime, dataX, dataY, dataZ);
		}
	}
	
	/**
	 * 保存上一次的相关数据
	 * 
	 * @param currentTime
	 *            当前时间
	 * @param dataX
	 *            当前的X轴加速度
	 * @param dataY
	 *            当前的Y轴加速度
	 * @param dataZ
	 *            当前的Z轴加速度
	 */
	private void saveLastValue(long aCurrentTime, float aDataX, float aDataY, float aDataZ) {
		// 保存上一次的数据
		_fLastX = aDataX;
		_fLastY = aDataY;
		_fLastZ = aDataZ;
		_fLastTime = aCurrentTime;
	}

	/**
	 * 初始化相关的参数
	 */
	private void resetLastValue() {
		_fShake = 0;
		_fLastX = 0;
		_fLastY = 0;
		_fLastZ = 0;
		_fLastTime = 0;
	}

	/**
	 * 执行手机震动
	 */
	private void actionVibrator() {
		Vibrator vibrator = (Vibrator) _fContext.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(100L);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

}
