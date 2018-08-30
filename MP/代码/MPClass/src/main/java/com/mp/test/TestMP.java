package com.mp.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.Condition;
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

	@Test
	public void testDatasource() throws SQLException {
		DataSource ds = app.getBean("dataSource", DataSource.class);
		Connection con = ds.getConnection();
		System.out.println(con);
	}

	/*---------------------------------------------Instert---------------------------------------------*/

	@Test
	// insert方法会忽略掉实体类为空的字段 sql语句忽略掉为空的字段
	public void testInsert() {
		// 初始化user对象
		User user = new User();
		user.setAccount("acc");
		user.setPassword("qweasdzxc");
		user.setSalt(123456);
		// 获得数据库影响的条数
		Integer result = userMapper.insert(user);
		System.out.println("result : " + result);
		// 获取这条新插入数据的主键值
		Integer key = user.getId();
		System.out.println("key : " + key);
	}

	@Test
	// insertAllColumn方法不会忽略掉实体类为空的字段 sql语句不忽略掉为空的字段
	public void testInsertAllColumn() {
		// 初始化user对象
		User user = new User();
		user.setAccount("add");
		user.setPassword("pwd");
		user.setSalt(123456);
		// 获得数据库影响的条数
		Integer result = userMapper.insertAllColumn(user);
		System.out.println("result : " + result);
		// 获取这条新插入数据的主键值
		Integer key = user.getId();
		System.out.println("key : " + key);
	}

	/*---------------------------------------------Update---------------------------------------------*/

	@Test
	// updateById方法会忽略掉实体类为空的字段 sql语句忽略掉为空的字段
	public void testUpdateById() {
		// 初始化user对象
		User user = new User();
		// 修改id为10的数据
		user.setId(10);
		user.setAccount("test");
		user.setPassword("test");
		user.setSalt(1);
		// 获得数据库影响的条数
		Integer result = userMapper.updateById(user);
		System.out.println("result : " + result);
	}

	@Test
	// updateAllColumnById方法不会忽略掉实体类为空的字段 sql语句不忽略掉为空的字段
	public void testupdateAllColumnById() {
		// 初始化user对象
		User user = new User();
		// 修改id为10的数据
		user.setId(10);
		user.setAccount("test1");
		user.setPassword("test1");
		user.setSalt(12);
		// 获得数据库影响的条数
		Integer result = userMapper.updateAllColumnById(user);
		System.out.println("result : " + result);
	}

	/*---------------------------------------------Select---------------------------------------------*/

	@Test
	// 通过id来查找数据 参数是序列化对象
	public void testSelectById() {
		// 初始化user对象
		User user = new User();
		user.setId(4);
		User user2 = userMapper.selectById(user);
		// User user3 = userMapper.selectById("4");
		System.out.println(user2.toString());
	}

	@Test
	// 通过条件来查找一条数据 如果查询到大于或等于两条数据,就会报错 如果没查到不报错
	public void testSelectOne() {
		// 初始化user对象
		User user = new User();
		user.setSalt(1);
		User user2 = userMapper.selectOne(user);
		System.out.println(user2.toString());
	}

	@Test
	// 通过多个id来查找数据
	public void testSelectBatchIds() {
		// 初始化存放id的集合
		ArrayList<Integer> userIdList = new ArrayList<Integer>();
		userIdList.add(1);
		userIdList.add(2);
		userIdList.add(3);
		// 获得查询到的结果集
		List<User> userList = userMapper.selectBatchIds(userIdList);
		// 迭代结果集
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	// 通过多个map来查找数据
	public void testSelectByMap() {
		// 初始化存放数据的集合
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 这里的key必须和数据库对应
		map.put("salt", 123456);
		// 获得查询到的结果集
		List<User> userList = userMapper.selectByMap(map);
		// 迭代结果集
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	// 分页查询 不是真实的分页 而是内存分页
	public void testSelectPage() {
		// 分页查询获得查询到的结果集
		// 参数1 分页的辅助类 参数类型RowBounds 1,显示的页数2,显示的个数
		// 参数2 条件构造器,可以为空
		List<User> userList = userMapper.selectPage(new Page<User>(1, 3), null);
		// 迭代结果集
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	/*---------------------------------------------Delete---------------------------------------------*/

	@Test
	// 通过id删除信息
	public void testDeleteById() {
		// 初始化user对象
		User user = new User();
		// 传入需要删除的id值
		user.setId(11);
		user.setSalt(123456);
		// 获得影响条数(通过对象删)
		Integer result = userMapper.deleteById(user);
		System.out.println("result:" + result);
		// 获得影响条数(直接通过id删)
		Integer result1 = userMapper.deleteById(10);
		System.out.println("result1:" + result1);
	}

	@Test
	// 通过条件进行删除
	public void testDeleteByMap() {
		// 初始化存放数据的集合
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 这里的key必须和数据库对应
		map.put("account", "acca");
		map.put("salt", 123456);
		// 获得影响条数
		Integer result = userMapper.deleteByMap(map);
		System.out.println("result:" + result);

	}

	@Test
	// 通过多个id来删除
	public void testDeleteBatchIds() {
		// 初始化存放id的集合
		ArrayList<Integer> userIdList = new ArrayList<Integer>();
		userIdList.add(5);
		userIdList.add(6);
		// 获得影响条数
		Integer result = userMapper.deleteBatchIds(userIdList);
		System.out.println("result:" + result);
	}

	/*------------------------------------------EntityWapper------------------------------------------*/
	// 继承了Wapper
	/*------------------------------其中属性必须是数据库的列名 自动拼接and------------------------------*/

	@Test
	// 使用条件构造器 分页查询
	public void testEntityWapperSelectPage() {
		// 查询account=123,并且salt在111109到111110之间,每页显示两条,并且通过salt排序
		// 声明存放数据的集合
		// 第几页，显示几条
		List<User> userList = userMapper.selectPage(new Page<User>(1, 2),
				// 构造Wapper对象
				new EntityWrapper<User>().eq("account", "123").between("salt", 111109, 111110)
						// orderBy默认升序
						// .orderBy("salt")
						// .orderAsc(Arrays.asList(new String[] {"salt"}))
						// true,升序false,降序
						// .orderBy("salt", true)
						.orderBy("salt")
						// 在SQL语句最后面添加desc !!!有SQL注入的风险
						.last("desc"));
		// 遍历数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	// 使用条件构造器 查询集合
	public void testEntityWapperSelectList() {
		// 查询salt=123456,并且账号中有tt,或者密码中有ss
		List<User> userList = userMapper.selectList(
				// 构造Wapper对象
				new EntityWrapper<User>().eq("salt", "123456").like("account", "tt")
						// .or() // SQL: (salt = ? AND account LIKE ? OR password LIKE ?)
						.orNew() // SQL: (salt = ? AND account LIKE ?) OR (password LIKE ?)
						.like("password", "ss"));
		// 遍历数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

	@Test
	// 使用条件构造器 进行修改操作
	public void testEntityWapperUpdate() {
		// 初始化修改的结果对象
		User user = new User();
		user.setAccount("ttt");
		user.setPassword("ggggg");
		user.setSalt(123456);
		// 获得影响条数
		// 修改account=ttt并且salt=123456
		Integer result = userMapper.update(user,
				// 构造Wapper对象
				new EntityWrapper<User>().eq("account", "ttt").eq("salt", 123456));
		System.out.println("result:" + result);
	}

	@Test
	// 使用条件构造器 进行删除操作
	public void testEntityWapperDelete() {
		// 删除account=qqq,并且password=eee,并且salt=999999
		// 获得影响条数
		Integer result = userMapper.delete(
				// 构造Wapper对象
				new EntityWrapper<User>().eq("account", "qqq").eq("password", "eee").eq("salt", 999999));
		System.out.println("result:" + result);
	}

	/*-------------------------------------------Condition-------------------------------------------*/
	// 也继承了Wapper 用法和EntityWapper大同小异 通过Condition.create()方法来创建Condition对象

	@Test
	// 使用条件构造器Condition 查询集合
	public void testConditionSelectList() {
		// 查询salt=123456,并且账号中有tt,或者密码中有ss
		@SuppressWarnings("unchecked")
		List<User> userList = userMapper.selectList(
				// 构造Condition对象
				Condition.create().eq("salt", "123456").like("account", "tt")
						// .or() // SQL: (salt = ? AND account LIKE ? OR password LIKE ?)
						.orNew() // SQL: (salt = ? AND account LIKE ?) OR (password LIKE ?)
						.like("password", "ss"));
		// 遍历数据
		for (User user : userList) {
			System.out.println(user.toString());
		}
	}

}
