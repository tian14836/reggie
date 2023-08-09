package com.fzt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzt.entity.ShoppingCart;
import com.fzt.mapper.ShoppingCartMapper;
import com.fzt.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
