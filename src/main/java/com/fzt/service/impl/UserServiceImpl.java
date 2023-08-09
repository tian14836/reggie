package com.fzt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzt.entity.User;
import com.fzt.mapper.UserMapper;
import com.fzt.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
