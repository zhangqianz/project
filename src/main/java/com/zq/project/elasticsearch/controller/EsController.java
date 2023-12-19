package com.zq.project.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.zq.project.elasticsearch.service.EsService;
import com.zq.project.http.domian.bo.HttpBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;

/**
 * @author zhangqian
 * @date 2023/2/27 14:02
 * @description:
 */
@RestController()
@RequestMapping("/elasticsearch")
@Api(tags = "5.es测试接口")
public class EsController {

    @Autowired
    private EsService esService;

    @PostMapping("/insert")
    @ApiOperation(value = "es插入数据")
    private Boolean insert(@RequestBody String json){
        return esService.insert(json);
    }

    @GetMapping("/createIndex")
    @ApiOperation(value = "es创建索引")
    private Boolean createIndex(@RequestParam String name){
        return esService.create(name);
    }

    @PostMapping("/query")
    @ApiOperation(value = "es查询")
    private JSON query(@RequestParam String name){
        return esService.query(name);
    }

    @PostMapping("/search")
    @ApiOperation(value = "es查询")
    private void search(){
        try {
            esService.search();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
