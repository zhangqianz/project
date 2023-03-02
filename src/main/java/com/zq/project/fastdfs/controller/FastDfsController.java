package com.zq.project.fastdfs.controller;

import com.zq.project.fastdfs.service.FastDfsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangqian
 * @date 2023/3/2 19:25
 * @description: controller
 * FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，
 * 解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。
 * FastDFS为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，
 * 使用FastDFS很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。
 */
@RestController()
@RequestMapping("/fastdfs")
@Api(tags = "6.fastdfs测试接口")
public class FastDfsController {

    @Autowired
    private FastDfsService fastDfsService;

    @PostMapping("/insert")
    @ApiOperation(value = "上传文件")
    public boolean uploadFile(@RequestPart @ApiParam(value = "⽂件对象") MultipartFile file){
        return fastDfsService.uploadFile(file);
    }
}
