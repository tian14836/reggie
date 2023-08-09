package com.fzt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fzt.dto.SetmealDto;
import com.fzt.entity.SetmealDish;
import com.fzt.mapper.SetmealDishMapper;
import com.fzt.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
