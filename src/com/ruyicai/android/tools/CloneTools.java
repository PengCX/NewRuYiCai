package com.ruyicai.android.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * 克隆工具类：被提供了使用序列化的方式克隆对象的方法
 * @author xiang_000
 * @since RYC1.0 2013-11-7
 */
public class CloneTools {
	/**
	 * 克隆一个新的对象
	 *
	 * @param obj
	 *            需要克隆的对象
	 * @return 克隆产生的对象
	 */
	public static <T extends Serializable> T clone(T obj) {
		//克隆将要产生的对象
		T clonedObj = null;

		try {
			//读取对象字节数据
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

			//分配内存空间，写入原始对象，生成新对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			//返回新的对象，并作类型转换
			clonedObj = (T)ois.readObject();
			ois.close();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return clonedObj;
	}
}
