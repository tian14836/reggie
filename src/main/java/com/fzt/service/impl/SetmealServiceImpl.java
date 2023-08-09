package com.fzt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fzt.common.CustomException;
import com.fzt.dto.SetmealDto;
import com.fzt.entity.Setmeal;
import com.fzt.entity.SetmealDish;
import com.fzt.mapper.SetmealMapper;
import com.fzt.service.SetmealDishService;
import com.fzt.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map(item->{
            item.setDishId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ids != null & !ids.isEmpty(), Setmeal::getId, ids).eq(Setmeal::getStatus, 1);
        int count = this.count(lambdaQueryWrapper);
        if(count>0){
            throw new CustomException("套餐正在售卖，不能删除");
        }
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.in(SetmealDish::getDishId,ids);
        setmealDishService.remove(dishLambdaQueryWrapper);
    }
}
