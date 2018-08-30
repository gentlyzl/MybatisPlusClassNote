package com.mp.tools;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * @author:Li
 * @mender:Li
 * @version:1.0.0
 * @time:2018年8月20日下午8:09:45
 * @docs:公共字段填充处理器
 */

public class MyMetaObjectHandler extends MetaObjectHandler {

	/**
	 * 插入操作 自动填充
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		// 获取到需要被填充的值
		Object fieldValue = getFieldValByName("salt", metaObject);
		if (fieldValue == null) {
			System.err.println("******需要填充字段******");
			// 给字段填充
			setFieldValByName("salt", 777777, metaObject);
		}
	}

	/**
	 * 更新操作 自动填充
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		// 获取到需要被填充的值
		Object fieldValue = getFieldValByName("salt", metaObject);
		if (fieldValue == null) {
			System.err.println("******需要填充字段******");
			// 给字段填充
			setFieldValByName("salt", 999999, metaObject);
		}
	}

}
