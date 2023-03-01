package com.zq.project.base.spi;

/**
 * @author zhangqian
 * @date 2023/3/1 10:14
 * @description:
 */
public class DatabaseSpiServiceImpl implements SpiService{
    @Override
    public void search(String str) {
        System.out.println("数据库查询"+str);
    }
}
