package com.zq.project.mysql.service;

import com.zq.project.mysql.domain.dto.SeatInfoDto;
import com.zq.project.mysql.domain.po.SeatInfoPo;

/**
 * @author zhangqian
 * @date 2023/3/29 10:52
 * @description:
 */
public interface SeatInfoService {

    /**
     * 保存
     * @param seatInfoDto
     * @return
     */
    boolean save(SeatInfoDto seatInfoDto);

    /**
     * 更新
     * @param seatInfoPo seatInfoPo
     * @return
     */
    boolean update(SeatInfoPo seatInfoPo);

    /**
     * 删除
     * @param id id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 查询
     * @param id id
     * @return
     */
    SeatInfoPo select(Integer id);
}
