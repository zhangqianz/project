package com.zq.project.mysql.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangqian
 * @date 2023/3/29 10:39
 * @description:
 */
@ApiModel(value = "SeatInfoPo实体类", description = "座位信息表")
@Data
@TableName("seat_info")
public class SeatInfoPo {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职员id")
    @TableField("employee_id")
    private Integer employeeId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "座位x坐标")
    @TableField("seat_x")
    private Integer seatX;

    @ApiModelProperty(value = "座位y坐标")
    @TableField("seat_y")
    private Integer seatY;

    @ApiModelProperty(value = "职员等级")
    @TableField("employee_level")
    private Integer employeeLevel;

    @ApiModelProperty(value = "楼层")
    @TableField("floor")
    private Integer floor;


}
