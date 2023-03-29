package com.zq.project.mysql.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangqian
 * @date 2023/3/29 10:54
 * @description:
 */
@ApiModel(value = "SeatInfoDto实体类", description = "座位信息表Dto")
@Data
public class SeatInfoDto {

    @ApiModelProperty(value = "职员id")
    private Integer employeeId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "座位x坐标")
    private Integer seatX;

    @ApiModelProperty(value = "座位y坐标")
    private Integer seatY;

    @ApiModelProperty(value = "职员等级")
    private Integer employeeLevel;

    @ApiModelProperty(value = "楼层")
    private Integer floor;
}
