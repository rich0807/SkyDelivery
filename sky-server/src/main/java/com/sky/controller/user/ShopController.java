package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController(" userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {
@Autowired
    private RedisTemplate redisTemplate;
private static final String KEY = "SHOP_STATUS";

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