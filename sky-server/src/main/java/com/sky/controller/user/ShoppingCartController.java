package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Api(tags = "C端-购物车接口")
@Slf4j
public class ShoppingCartController {
 @Autowired
private ShoppingCartService shoppingCartService;
    /**
     * 添加到购物车
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加到购物车")
    public Result addToShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
    log.info("添加购物车：{}", shoppingCartDTO);

        shoppingCartService.addToShoppingCart(shoppingCartDTO);

        return Result.success();
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> showShoppingCart() {
        List<ShoppingCart> shoppingCartDTOList = shoppingCartService.showShoppingCart();
        return Result.success(shoppingCartDTOList);
    }

    /**
     *  清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result clear() {
        shoppingCartService.clear();
        return Result.success();
    }
    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("删除购物车中一个商品")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车中一个商品，商品：{}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();}
}