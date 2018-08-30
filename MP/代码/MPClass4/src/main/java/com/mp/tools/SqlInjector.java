package com.mp.tools;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;

/**
 * @author:Li
 * @mender:Li
 * @version:1.0.0
 * @time:2018年8月20日下午3:38:19
 * @docs:SqlInjector.java
 */

public class SqlInjector extends AutoSqlInjector {

	/**
	 * 拓展inject，完成自定义全局操作
	 */
	@Override
	public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass,
			Class<?> modelClass, TableInfo table) {
		// 将UserMapper中定义的方法处理成对应的MappedStatment对象，加入到configuration对象中
		
		// SQL语句
		String sql = "DELETE FROM " + table.getTableName() + " WHERE id = 23";
		// 注入的方法名
		String methodName = "testDelete";
		// 构造sqlSource对象
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		// 构造删除的MappedStatement对象
		this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
	}

}
