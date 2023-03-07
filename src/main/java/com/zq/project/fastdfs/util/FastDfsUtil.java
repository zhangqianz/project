package com.zq.project.fastdfs.util;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author zhangqian
 * @date 2023/3/6 15:11
 * @description: fastdfs操作工具类
 */
@Component
@Slf4j
public class FastDfsUtil {

    @Autowired
    private TrackerClient trackerClient;

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    /**
     * 上传文件
     * @param inputStream 输入流
     * @param fileSize 文件大小
     * @param fileExtName 文件后缀名
     * @return
     */
    public StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName){
        return defaultGenerateStorageClient.uploadFile(null, inputStream, fileSize, fileExtName);
    }

    /**
     * 上传文件
     * @param multipartFile 文件
     * @return
     * @throws IOException
     */
    public StorePath uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile==null){
            return null;
        }
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(multipartFile.getBytes());
        String fileExtName="";
        String filename = multipartFile.getOriginalFilename();
        if (filename.lastIndexOf(".") != -1) {
            fileExtName = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        }
        return defaultGenerateStorageClient.uploadFile(null, byteInputStream, multipartFile.getSize(), fileExtName);
    }

    /**
     * 上传文件
     * @param groupName 分组名
     * @param inputStream 输入流
     * @param fileSize 文件大小
     * @param fileExtName 文件名后缀名
     * @return
     */
    public StorePath uploadFile(String groupName, InputStream inputStream, long fileSize, String fileExtName){
       return defaultGenerateStorageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
    }

    /**
     * 删除文件
     * @param groupName 分组名
     * @param path 路径
     */
    public void deleteFile(String groupName,String path){
        defaultGenerateStorageClient.deleteFile(groupName,path);
    }

    /**
     * 下载文件
     * @param groupName 分组名
     * @param path 路径
     * @return
     */
    public byte[] downloadFile(String groupName,String path){
        return defaultGenerateStorageClient.downloadFile(groupName, path, new DownloadByteArray());
    }

    /**
     * 查询文件信息
     * @param groupName 分组名
     * @param path 路径
     * @return
     */
    public FileInfo getFileInfo(String groupName,String path){
        return defaultGenerateStorageClient.queryFileInfo(groupName,path);
    }

    /**
     * 查询分组节点信息
     * @param groupName 组名
     * @return
     */
    public StorageNode getStorageNode(String groupName){
        return trackerClient.getStoreStorage(groupName);
    }

    /**
     * 删除分组
     * @param groupName 分组名
     * @param ip ip
     */
    public void deleteStorage(String groupName,String ip){
        trackerClient.deleteStorage(groupName,ip);
    }

    /**
     * 下载图片
     * @param fileName 文件名
     * @param groupName 分组名
     * @param path 文件路径
     * @return
     */
    public ResponseEntity<byte[]> downloadImg(String fileName,String groupName,String path) {
        byte[] bytes = downloadFile(groupName, path);
        if (bytes==null || bytes.length==0){
            log.error("图片下载失败！");
            return null;
        }
        return getResponseEntity(bytes,fileName,"jpg");
    }

    /**
     * 下载视频
     * @param fileName 文件名
     * @param groupName 分组名
     * @param path 文件路径
     * @return
     */
    public ResponseEntity<byte[]> downloadVideo(String fileName,String groupName,String path) {
        byte[] bytes = downloadFile(groupName, path);
        if (bytes==null || bytes.length==0){
            log.error("视频下载失败！");
            return null;
        }
        return getResponseEntity(bytes,fileName,"mp4");
    }

    /**
     * 获取ResponseEntity<byte[]>下载对象
     * @param bytes 字节数组
     * @param fileName 文件名
     * @param fileExtName 文件后缀名
     * @return
     */
    public ResponseEntity<byte[]> getResponseEntity(byte[] bytes,String fileName,String fileExtName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.add("Content-Disposition", String.format("attachment;filename=%s.%s;",
                encodedFileName, fileExtName));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
