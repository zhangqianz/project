package com.zq.project.mysql.controller;

import com.zq.project.mysql.domain.dto.SeatInfoDto;
import com.zq.project.mysql.domain.po.SeatInfoPo;
import com.zq.project.mysql.service.SeatInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangqian
 * @date 2023/3/29 10:37
 * @description:
 */
@RestController()
@RequestMapping("/mysql")
@Api(tags = "7.mysql测试接口")
public class SeatInfoController {

    @Autowired
    private SeatInfoService seatInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "保存数据")
    private boolean save(@RequestBody SeatInfoDto seatInfoDto){
        return seatInfoService.save(seatInfoDto);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新数据")
    private boolean update(@RequestBody SeatInfoPo seatInfoPo){
        return seatInfoService.update(seatInfoPo);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据")
    private boolean delete(@PathVariable Integer id){
        return seatInfoService.delete(id);
    }

    @PostMapping("/select/{id}")
    @ApiOperation(value = "查询数据")
    private SeatInfoPo select(@PathVariable Integer id){
        return seatInfoService.select(id);
    }
}
