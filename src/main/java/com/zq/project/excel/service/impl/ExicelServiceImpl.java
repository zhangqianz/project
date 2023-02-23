package com.zq.project.excel.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zq.project.excel.domain.bo.ExcelTesBo;
import com.zq.project.excel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangqian
 * @date 2023/2/23 18:58
 * @description:
 */
@Service
@Slf4j
public class ExicelServiceImpl implements ExcelService {

    @Override
    public ResponseEntity<byte[]> export() {
        try {
            String fileName = "学生" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            List<ExcelTesBo> list = new ArrayList<>();
            ExcelTesBo studentOne = new ExcelTesBo().setAge(18).setHigh(172).setName("张三").setSex("男").setWeight(65.3);
            ExcelTesBo studentTwo = new ExcelTesBo().setAge(19).setHigh(168).setName("李四").setSex("男").setWeight(68.3);
            ExcelTesBo studentThree = new ExcelTesBo().setAge(21).setHigh(178).setName("王五").setSex("男").setWeight(69.3);
            ExcelTesBo studentFour = new ExcelTesBo().setAge(22).setHigh(186).setName("小倩").setSex("女").setWeight(45.3);
            list.add(studentOne);
            list.add(studentTwo);
            list.add(studentThree);
            list.add(studentFour);
            return export(list, fileName, fileName, ExtensionType.XLS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static <E> ResponseEntity<byte[]> export(List<E> dataList, String sheetName,
                                                    String fileName, ExtensionType excelExtension) throws Exception {
        if (CollectionUtils.isEmpty(dataList)) {
            throw new Exception("数据量为0，无法导出");
        }
        if (StringUtils.isEmpty(sheetName) || StringUtils.isEmpty(fileName)) {
            throw new Exception("工作簿或文件名不能为空");
        }
        // 文件扩展名默认 xls
        if (Objects.isNull(excelExtension)) {
            excelExtension = ExtensionType.XLS;
        }
        E e = dataList.get(0);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //主要是调用工具类
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, sheetName), e.getClass(), dataList);
        try {
            workbook.write(bos);
        } catch (IOException ioException) {
            log.error(excelExtension.toString());
        }
        return flushToResp(fileName, excelExtension.extension, bos.toByteArray());
    }


    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @param suffix   文件后缀
     * @param bytes    字节数组
     */
    public static ResponseEntity<byte[]> flushToResp(String fileName, String suffix, byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("文件生成失败, 数据不能为空");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.add("Content-Disposition", String.format("attachment;filename=%s.%s;",
                encodedFileName, suffix));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }


    public enum ExtensionType {
        /**
         *
         *xls后缀名
         *
         *
         **/
        XLS("xls"),
        /**
         *
         *xlsx后缀名
         *
         *
         **/
        XLSX("xlsx");
        private final String extension;

        ExtensionType(String extension) {
            this.extension = extension;
        }
    }
}
