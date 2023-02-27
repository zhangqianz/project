package com.zq.project.elasticsearch.service;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.protocol.types.Field;
import springfox.documentation.spring.web.json.Json;

/**
 * @author zhangqian
 * @date 2023/2/27 14:02
 * @description:
 */
public interface EsService {

    /**
     * 插入信息
     * @param json
     * @return
     */
    Boolean insert(String json);

    /**
     * 创建索引
     * @param name
     * @return
     */
    Boolean create(String name);


    /**
     * 查询
     * @param name
     * @return
     */
    JSON query(String name);


}
