package com.zq.project.kafka.controller;

import com.zq.project.kafka.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqian
 * @date 2023/2/21 19:55
 * @description:
 */
@RestController()
@RequestMapping("/kafka")
@Api(tags = "1.kafka测试接口")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/sendMessage/{topic}/{message}")
    @ApiOperation(value = "kafka测试")
    private boolean sendMessage(@PathVariable String topic, @PathVariable String message){
        return projectService.sendMessage(topic,message);
    }
}
