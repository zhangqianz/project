package com.zq.project.redis.controller;

import com.zq.project.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqian
 * @date 2023/2/22 15:33
 * @description:
 */
@RestController()
@RequestMapping("/redis")
@Api(tags = "2.redis测试接口")
public class RedisController {

    @Autowired
    private RedisService redisService;


    @PostMapping("/saveForValue/{key}/{value}")
    @ApiOperation(value = "redis保存String类型数据")
    private boolean saveForValue(@PathVariable String key, @PathVariable String value){
        return redisService.saveForValue(key,value);
    }


    @PostMapping("/getForValue/{key}")
    @ApiOperation(value = "redis查询String类型数据")
    private Object getForValue(@PathVariable String key){
        return redisService.getForValue(key);
    }

}
