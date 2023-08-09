package com.fzt.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzt.common.CustomException;
import com.fzt.entity.Category;
import com.fzt.entity.Dish;
import com.fzt.entity.R;
import com.fzt.entity.Setmeal;
import com.fzt.mapper.CategoryMapper;

import com.fzt.service.CategoryService;
import com.fzt.service.DishService;
import com.fzt.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(lambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = dishService.count(dishLambdaQueryWrapper);
        if(count2>0){
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        super.removeById(id);
    }
}
