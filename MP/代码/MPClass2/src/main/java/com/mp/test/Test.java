package com.mp.test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author:Li
 * @mender:Li
 * @version:1.0.0
 * @time:2018年6月26日上午11:57:55
 * @docs:Test.java
 */

public class Test {

	public static void main(String[] args) {
        // TODO Auto-generated method stub
        generateCode(new String[] { "user" });
//        generateCode(new String[] { "contract","dd_word_table","ddtable","demand","facilitator","file","file_adjunct","hat_area","hat_city","hat_province","user","witkey_garde","witkey_hire_pay","witkey_mission_type","witkey_project_period","witkey_works_case" });
    }

    /**
     * Dao、Mapper、Service和、Controller生成代码
     * 
     * @param tabelNames
     *            表名
     */
    public static void generateCode(String[] tabelNames) {
        AutoGenerator ag = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //gc.setOutputDir("D:/MP");// 创建的文件输出目录
        gc.setOutputDir("D:\\eclipse-workspace\\MP\\src\\main\\java");// 创建的文件输出目录
        gc.setFileOverride(true);// 覆盖已有文件
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("Li");// 类作者
        // 文件名
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sWeb");
        ag.setGlobalConfig(gc);

        // 数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        ag.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tabelNames); // 需要生成相关代码的表名
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper");// 自定义 dao 父类
        ag.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity("com.ssm.entity");
        pc.setMapper("com.ssm.dao");// Dao接口包名
        pc.setXml("com.ssm.dao");// XML包名
        pc.setService("com.ssm.service");
        pc.setServiceImpl("com.ssm.serviceImpl");
        pc.setController("com.ssm.web");
        ag.setPackageInfo(pc);

        // 执行生成
        ag.execute();
        System.out.println("run over");
    }

    /**
     * 数据源设置
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin123");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8");
        return dsc;
    }
	
}
