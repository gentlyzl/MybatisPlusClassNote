package com.mp.serviceImpl;

import com.mp.pojo.User;
import com.mp.dao.UserMapper;
import com.mp.api.UserApi;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijiahang
 * @since 2018-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserApi {

}
