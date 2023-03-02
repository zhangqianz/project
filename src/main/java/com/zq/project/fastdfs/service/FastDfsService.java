package com.zq.project.fastdfs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangqian
 * @date 2023/3/2 19:26
 * @description:
 */
public interface FastDfsService {

    /**
     * 分布式上传文件
     * @param multipartFile
     * @return
     */
    boolean uploadFile(MultipartFile multipartFile);
}
