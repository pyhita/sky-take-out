package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
@Mapper
public interface DishFlavorMapper {


    void batchInsert(List<DishFlavor> flavors);

    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    List<DishFlavor> getByDishId(Long id);
}
