package com.zq.project.http.service;

import com.alibaba.fastjson.JSON;
import com.zq.project.http.domian.bo.HttpBo;
import com.zq.project.http.domian.bo.UmsBo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhangqian
 * @date 2023/2/23 19:48
 * @description:
 */
public interface HttpService {

    /**
     * 获取http结果
     * @param httpBo
     * @return
     */
    JSON getHttpResult(HttpBo httpBo);


    /**
     *
     * @param umsBo
     * @return
     */
    JSON sendUms(UmsBo umsBo);
}
