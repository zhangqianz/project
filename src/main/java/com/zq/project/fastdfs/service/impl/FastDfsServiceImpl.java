package com.zq.project.fastdfs.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.zq.project.fastdfs.service.FastDfsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author zhangqian
 * @date 2023/3/2 19:26
 * @description:
 */
@Service
@Slf4j
public class FastDfsServiceImpl implements FastDfsService {

    @Autowired
    private TrackerClient trackerClient;

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Override
    public boolean uploadFile(MultipartFile multipartFile) {
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            StorePath storePath = defaultGenerateStorageClient.uploadFile(null,
                    byteInputStream, multipartFile.getBytes().length, multipartFile.getOriginalFilename());
            //获取ip
            String ip = trackerClient.getStoreStorage(storePath.getGroup()).getIp();
            //全路径
            String path=ip+"/"+storePath.getFullPath();
            return true;
        } catch (IOException e) {
            log.error("上传文件出现异常!"+e.getMessage(),e);
            return false;
        }
    }
}
