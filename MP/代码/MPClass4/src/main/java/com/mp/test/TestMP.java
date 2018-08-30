package com.mp.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mp.dao.UserMapper;
import com.mp.pojo.User;

/**
 * @author:Lit
 * @mender:Li
 * @version:1.0.0
 * @time:2018年7月26日下午7:40:13
 * @docs:TestMp.java
 */

public class TestMP {

	private ApplicationContext app = new ClassPathXmlApplicationContext("spring-beans.xml");
	private UserMapper userMapper = app.getBean("userMapper", UserMapper.class);

	/* --------------------------------------------- 插件 --------------------------------------------- */

	/**
	 * 查询分页操作 分页插件操作
	 */
	@Test
	// 当前配置了分页插件 是真实的物理分页，而不是内存分页
	public void testPagination() {
		// 设置分页参数
		Page<User> page = new Page<>(1, 5);
		// 参数1 分页的辅助类 参数类型RowBounds 1,当前的页数2,显示的个数
		// 参数2 条件构造器,可以为空
		// 分页查询获得查询到的结果集
		List<User> userList = userMapper.selectPage(page, null);
		// 迭代结果集
		for (User user : userList) {
			System.out.println(user.toString());
		}
		// 获得分页相关信息
		System.out.println("总条数:" + page.getTotal());
		System.out.println("当前页码:" + page.getCurrent());
		System.out.println("总页码:" + page.getPages());
		System.out.println("每页显示的条数:" + page.getSize());
		System.out.println("是否有上一页:" + page.hasPrevious());
		System.out.println("是否有下一页:" + page.hasNext());
		// 将查询的结果封装到page中
		page.setRecords(userList);
	}

	/**
	 * 测试执行分析插件
	 */
	public void testSqlExplain() {
		// 执行全部删除操作
		Integer result = userMapper.delete(null);
		// 输出结果
		System.out.println("result:" + result);
	}
	
	/**
	 * 更新操作 乐观锁插件测试
	 */
	@Test
	public void testOptimisticLocker() {
		// 初始化user对象
		User user = new User();
		user.setId(24);
		user.setAccount("gsssgg");
		user.setPassword("weweddwe");
		user.setSalt(213256);
		user.setVersion(1);
		// 执行修改操作
		Integer result = userMapper.updateById(user);
		// 输出结果
		System.out.println("result:" + result);
	}
	
	/**
	 * 测试自定义SQL
	 */
	@Test
	public void testInjector() {
		// 测试删除操作
		Integer result = userMapper.testDelete();
		// 输出结果
		System.out.println("result:" + result);
	}
	
	/**
	 * 测试逻辑删除
	 */
	@Test
	public void testLogicDDelete() {
		// 进行逻辑删除操作 实际上是更新操作
		Integer result = userMapper.deleteById(23);
		// 输出结果
		System.out.println("result:" + result); 
		// 查询进行过逻辑删除的操作
		User user = userMapper.selectById(23);
		try {
			// 输出结果
			System.out.println(user.toString());
		} catch (Exception e) {
			System.out.println("没有结果");
		}
	}
	
	/**
	 * 测试自动填充字段
	 */
	@Test
	public void testMetaObjectInsert() {
		// 初始化user对象
		User user = new User();
		user.setAccount("metaobject");
		user.setPassword("insert");
		user.setVersion(1);
		// 测试删除操作
		Integer result = userMapper.insert(user);
		// 输出结果
		System.out.println("result:" + result);
	}
	
	/**
	 * 测试自动填充字段
	 */
	@Test
	public void testMetaObjectUpdate() {
		// 初始化user对象
		User user = new User();
		user.setId(19);
		user.setAccount("metaobject");
		user.setPassword("update");
		user.setVersion(1);
		// 测试删除操作
		Integer result = userMapper.update(user,new EntityWrapper<User>().eq("salt", 777777));
		// 输出结果
		System.out.println("result:" + result);
	}
	
}
