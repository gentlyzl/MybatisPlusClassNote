package com.mp.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mp.pojo.User;

/**
 * @author:Li
 * @mender:Li
 * @version:1.0.0
 * @time:2018年7月31日上午11:39:56
 * @docs:MPMapper.java
 */

public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 自定义删除操作
	 * @return 影响的条数
	 */
	Integer testDelete();
	
}
 