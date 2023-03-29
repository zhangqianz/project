package com.zq.project.mysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zq.project.mysql.domain.po.SeatInfoPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangqian
 * @date 2023/3/29 10:48
 * @description:
 */
@Mapper
public interface SeatInfoMapper extends BaseMapper<SeatInfoPo> {
}
