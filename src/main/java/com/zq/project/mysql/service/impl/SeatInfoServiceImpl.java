package com.zq.project.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.project.mysql.dao.SeatInfoMapper;
import com.zq.project.mysql.domain.dto.SeatInfoDto;
import com.zq.project.mysql.domain.po.SeatInfoPo;
import com.zq.project.mysql.service.SeatInfoService;
import com.zq.project.util.ConvertUtil;
import org.springframework.stereotype.Service;

/**
 * @author zhangqian
 * @date 2023/3/29 10:52
 * @description:
 */
@Service
public class SeatInfoServiceImpl extends ServiceImpl<SeatInfoMapper, SeatInfoPo> implements SeatInfoService {

    @Override
    public boolean save(SeatInfoDto seatInfoDto) {
        return this.save(ConvertUtil.convert(seatInfoDto,SeatInfoPo.class));
    }

    @Override
    public boolean update(SeatInfoPo seatInfoPo) {
        return this.updateById(seatInfoPo);
    }

    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    public SeatInfoPo select(Integer id) {
        return this.getById(id);
    }
}
