package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类管理")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService CategoryService;

    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation(" 新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {

        log.info("新增分类:{}", categoryDTO);
        CategoryService.save(categoryDTO);
        return Result.success();

    }

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(" 分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询:{}", categoryPageQueryDTO);
        PageResult pageResult = CategoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation(" 分类删除")
    public Result delete(Long id) {
        log.info("删除分类:{}", id);
        CategoryService.delete(id);
        return Result.success();
    }

    /**
     * 修改分类
     *
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation(" 修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO) {

        log.info("修改分类:{}", categoryDTO);
        CategoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(" 修改分类状态")
    public Result startOrStop(Integer status, Long id) {
        log.info("修改分类状态:{}", status);
        CategoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
@GetMapping("/list")
@ApiOperation(" 根据类型查询分类")
public Result<List<Category>> list(Integer type){
    log.info("根据类型查询 分类:{}", type);

    List<Category> list = CategoryService.list(type);
    return Result.success(list);
}
}