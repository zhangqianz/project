package com.zq.project.excel.controller;

import com.zq.project.excel.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhangqian
 * @date 2023/2/23 18:57
 * @description: excel
 */
@RestController()
@RequestMapping("/excel")
@Api(tags = "3.excel测试接口")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @ApiOperation(value = "Excel导出", notes = "Excel导出")
    @GetMapping(value = "/exportExcel")
    ResponseEntity<byte[]> exportExcelInterfaceInfo() {
        return excelService.export();
    }

}
