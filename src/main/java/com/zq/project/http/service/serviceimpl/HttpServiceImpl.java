package com.zq.project.http.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zq.project.http.domian.bo.HttpBo;
import com.zq.project.http.service.HttpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqian
 * @date 2023/2/23 19:48
 * @description:
 */
@Service
public class HttpServiceImpl implements HttpService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSON getHttpResult(HttpBo httpBo) {
        //远程调用获取数据
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> http = new HttpEntity<>(httpBo.getParams(), httpHeaders);
        //post方式
        return restTemplate.postForObject(httpBo.getUrl(), http, JSONObject.class);
    }
}
