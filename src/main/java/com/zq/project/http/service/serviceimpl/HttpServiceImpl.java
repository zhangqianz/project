package com.zq.project.http.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zq.project.http.domian.bo.HttpBo;
import com.zq.project.http.domian.bo.UmsBo;
import com.zq.project.http.service.HttpService;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
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
        httpHeaders.add("x-request-id","1702445053099123456");
        httpHeaders.add("x-sp-code","209037");
        httpHeaders.add("x-app-key","hnwasu_hw");
        httpHeaders.add("x-sign","661AA712FC364F688E8C0808B609C223D3ECF4638793D1CDA4BB97DE66445185");
        HttpEntity<Map<String, Object>> http = new HttpEntity<>(httpBo.getParams(), httpHeaders);
        //post方式
        return restTemplate.postForObject(httpBo.getUrl(), http, JSONObject.class);
    }

    @Override
    public JSON sendUms(UmsBo umsBo) {
        //远程调用获取数据
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("x-request-id",umsBo.getRequestId());
        httpHeaders.add("x-sp-code",umsBo.getSpCpde());
        httpHeaders.add("x-app-key",umsBo.getAppKey());
        httpHeaders.add("x-sign",umsBo.getSign());
        HttpEntity<Map<String, Object>> http = new HttpEntity<>(umsBo.getParam(), httpHeaders);
        //post方式
        return restTemplate.postForObject(umsBo.getUrl(), http, JSONObject.class);
    }
}
