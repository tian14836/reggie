package com.fzt.dto;


import com.fzt.entity.Setmeal;
import com.fzt.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
