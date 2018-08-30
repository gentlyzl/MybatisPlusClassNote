package com.mp.test;

import org.junit.Test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author:Lit
 * @mender:Li
 * @version:1.0.0
 * @time:2018年7月26日下午7:40:13
 * @docs:TestMp.java
 */

public class TestMP {

	/**
	 * 代码生成器
	 */

	@Test
	public void testGenerator() {
		// 全局配置
		GlobalConfig gbConfig = new GlobalConfig();
		// 是否支持AR模式
		gbConfig.setActiveRecord(true)
				// 设置作者
				.setAuthor("lijiahang")
				// 生成路径
				.setOutputDir("D:\\eclipse-workspace\\MPClass2\\src\\main\\java")
				// 文件是否覆盖
				.setFileOverride(true)
				// 主键策略
				.setIdType(IdType.AUTO)
				// Service名称 默认IxxxService
				.setServiceName("%sApi")
				// 是否生成ResultMap
				.setBaseResultMap(true)
				// 是否生成二级缓存
				.setEnableCache(false)
				// 是否生成SQL片段
				.setBaseColumnList(true);

		// 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		// 配置数据库类型
		dsConfig.setDbType(DbType.MYSQL)
				// 配置驱动
				.setDriverName("com.mysql.jdbc.Driver")
				// 配置路径
				.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8")
				// 配置账号
				.setUsername("root")
				// 配置密码
				.setPassword("admin123");

		// 策略配置
		StrategyConfig stConfig = new StrategyConfig();
		// 全局大写命名
		stConfig.setCapitalMode(true)
				// 表名是否用了下滑线
				.setDbColumnUnderline(true)
				// 下滑线转驼峰命名策略
				.setNaming(NamingStrategy.underline_to_camel)
				// 是否生成字段常量
				.setEntityColumnConstant(true)
				// 是否为构建者模型
				.setEntityBuilderModel(true)
				// 配置表前缀
				.setTablePrefix("")
				// 生成的表
				.setInclude(new String[] { "user" });

		// 包名策略配置
		PackageConfig pkConfig = new PackageConfig();
		// 声明父包
		pkConfig.setParent("com.mp")
				// 映射接口的包
				.setMapper("dao")
				// service接口的包
				.setService("api")
				// serviceImpl接口的包
				.setServiceImpl("serviceImpl")
				// controller接口的包
				.setController("web")
				// 实体类的包
				.setEntity("pojo")
				// 映射文件的包
				.setXml("dao");

		// 整合配置
		AutoGenerator ag = new AutoGenerator();
		// 整合全局配置
		ag.setGlobalConfig(gbConfig)
				// 整合数据源配置
				.setDataSource(dsConfig)
				// 整合表名配置
				.setStrategy(stConfig)
				// 整合包名策略
				.setPackageInfo(pkConfig);

		// 执行
		ag.execute();

	}

}
