package com.zq.project.base.spi;

/**
 * @author zhangqian
 * @date 2023/3/1 10:13
 * @description:
 */
public class FileSpiServiceImpl implements SpiService{

    @Override
    public void search(String str) {
        System.out.println("文件查询"+str);
    }
}
