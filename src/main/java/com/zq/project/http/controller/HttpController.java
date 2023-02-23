package com.zq.project.http.controller;

import com.alibaba.fastjson.JSON;
import com.zq.project.http.domian.bo.HttpBo;
import com.zq.project.http.service.HttpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangqian
 * @date 2023/2/23 19:47
 * @description:
 */
@RestController()
@RequestMapping("/http")
@Api(tags = "4.http测试接口")
public class HttpController {

    @Autowired
    private HttpService httpService;

    @PostMapping("/getHttpResultByPost")
    @ApiOperation(value = "http测试")
    private JSON getHttpResultByPost(@RequestBody HttpBo httpBo){
        return httpService.getHttpResult(httpBo);
    }
}
