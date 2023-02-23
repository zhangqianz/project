package com.zq.project.excel.service;

import org.springframework.http.ResponseEntity;

/**
 * @author zhangqian
 * @date 2023/2/23 18:58
 * @description:
 */
public interface ExcelService {

    /**
     * excel导出
     * @return
     */
    ResponseEntity<byte[]> export();
}
