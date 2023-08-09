package com.fzt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzt.dto.SetmealDto;
import com.fzt.entity.Setmeal;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    void removeWithDish(List<Long> ids);
}
