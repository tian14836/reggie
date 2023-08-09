package com.fzt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzt.entity.Category;


public interface CategoryService extends IService<Category> {

     void remove(Long id);

}
