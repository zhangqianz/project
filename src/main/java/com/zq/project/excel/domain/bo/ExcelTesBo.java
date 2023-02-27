package com.zq.project.excel.domain.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * @author zhangqian
 * @date 2023/2/23 19:30
 * @description:
 */
@Data
@Accessors(chain = true)
public class ExcelTesBo {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "性别")
    private String sex;

    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "身高")
    private Integer high;

    @Excel(name = "体重")
    private Double weight;

    @Excel(name = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime localDateTime;

}
