package com.zq.project.redis.service;

/**
 * @author zhangqian
 * @date 2023/2/22 15:34
 * @description:
 */
public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @return
     */
    boolean saveForValue(String key,String value);

    /**
     *
     * @param key
     * @return
     */
    Object getForValue(String key);
}
