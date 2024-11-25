package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {
@Autowired
    private RedisTemplate redisTemplate;

private static final String KEY = "SHOP_STATUS";
    /**
     * 设置店铺的营业状态
     * @return
     */
@PutMapping("/{status}")
@ApiOperation("设置店铺的营业状态")
    public Result setstatus(@PathVariable Integer status){
    log.info("设置店铺的营业状态:{}",status==1?"营业中":"打烊");
    String str = String.valueOf(status);
        redisTemplate.opsForValue().set(KEY, str);
        return Result.success();
    }

    /**
     * 获取店铺的营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus(){
        Object status = redisTemplate.opsForValue().get(KEY);
        Integer str = Integer.parseInt(status.toString());
        log.info("获取到店铺的营业状态为：{}",str == 1 ? "营业中" : "打烊中");
        return Result.success(str);
    }
}
