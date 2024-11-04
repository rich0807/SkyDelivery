package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert(" insert into category(name,type,sort,create_time,update_time,create_user,update_user,status) values" +
            "(#{name},#{type},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser},#{status})")
    void save(Category category);

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 修改分类
     * @param category
     */
    void update(Category category);

    /**
     * 修改分类状态
     * @param category
     */
    @Update("update category set status=#{status} where id=#{id}")
    void updatestatus(Category category);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Select("select * from category where type=#{type}")
    List<Category> list(Integer type);
}