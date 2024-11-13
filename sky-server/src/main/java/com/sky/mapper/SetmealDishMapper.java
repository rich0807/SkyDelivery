package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     *
     * @return
     */
    List<Long> getByDishIds(List<Long> dishIds);
}
