package com.mp.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

	/*---------------------------------------------ActiveRecord---------------------------------------------*/

	/**
	 * ARInsert
	 */

	@Test
	public void testARInsert() {
		// 初始化user对象
		User user = new User();
		user.setAccount("aaaaaa");
		user.setPassword("rrrrrr");
		user.setSalt(123321);
		// 进行插入操作 获得结果数据
		Boolean result = user.insert();
		System.out.println("result:" + result);
	}

	/**
	 * ARUpdate
	 */

	@Test
	public void testARUpdateById() {
		// 初始化user对象
		User user = new User();
		user.setId(22);
		user.setAccount("aaaaaa");
		user.setPassword("uuuuuu");
		user.setSalt(123321);
		// 进行插入操作 获得结果数据 自动忽略为空的条件
		Boolean result = user.updateById();
		System.out.println("result:" + result);
	}

	/**
	 * ARSelect
	 */

	@Test
	public void testARSelectById() {
		// 初始化user对象
		User user = new User();
		user.setId(22);
		// 直接通过user来查找
		User user1 = user.selectById();
		// 直接通过id来查找
		User user2 = user.selectById(22);
		// 通过id不为空的user对象来查找
		User user3 = user.selectById(user);
		System.out.println(user1.toString());
		System.out.println(user2.toString());
		System.out.println(user3.toString());
	}

	@Test
	public void testARSelectAll() {
		// 声明list对象来接收取到的所有数据
		List<User> userList = new User().selectAll();
		// 遍历数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	public void testARSelectList() {
		// 声明list对象来接收取到的所有数据
		// 构造EntityWapper
		List<User> userList = new User().selectList(new EntityWrapper<User>().eq("account", "aaaaaa").and()
				.eq("password", "rrrrrr").or().eq("password", "uuuuuu"));
		// 遍历数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	public void testARSelectCount() {
		// 查询总数
		Integer num = new User().selectCount(null);
		System.out.println("num:" + num);
		// 通过Wapper来查询总数 构造EntityWapper
		Integer num1 = new User().selectCount(new EntityWrapper<User>().eq("account", "acc"));
		System.out.println("num1:" + num1);
	}

	@Test
	public void testARSelectPage() {
		// AR进行分页操作 返回page对象
		// 第几页，显示几条
		Page<User> page = new User().selectPage(new Page<User>(1, 2),
				// 构造EntityWapper
				new EntityWrapper<User>().eq("account", "acc"));
		// 拿到page对象里的数据
		List<User> userList = page.getRecords();
		// 便利数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
		
	}

	/**
	 * ARDelete 删除不存在的数据也是true即成功执行完毕
	 */

	@Test
	public void testARDeleteById() {
		// 初始化user对象
		User user = new User();
		user.setId(23);
		// 直接通过user对象删除
		Boolean result = user.deleteById();
		System.out.println("result:" + result);
		// 直接通过id来删除
		Boolean result1 = user.deleteById(25);
		System.out.println("result1:" + result1);
		// 通过id不为空的user对象来删除
		Boolean result2 = user.deleteById(user);
		System.out.println("result2:" + result2);
	}

	@Test
	public void testARDelete() {
		// 通过Wapper来删除数据
		Boolean result = new User().delete(
				// 初始化EntityWapper
				new EntityWrapper<User>().eq("account", "112233").and().like("password", "5566"));
		System.out.println("result:" + result);
	}

}
