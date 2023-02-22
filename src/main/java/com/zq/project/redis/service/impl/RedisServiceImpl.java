package com.zq.project.redis.service.impl;

import com.zq.project.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhangqian
 * @date 2023/2/22 15:34
 * @description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public boolean saveForValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    @Override
    public Object getForValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
