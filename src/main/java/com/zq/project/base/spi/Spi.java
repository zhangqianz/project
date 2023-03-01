package com.zq.project.base.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zhangqian
 * @date 2023/3/1 10:15
 * @description: spi测试
 * SPI（Service Provider Interface），是JDK内置的一种 服务提供发现机制，可以用来启用框架扩展和替换组件，
 * 主要是被框架的开发人员使用，比如java.sql.Driver接口，其他不同厂商可以针对同一接口做出不同的实现，MySQL和PostgreSQL都有不同的实现提供给用户
 * ，而Java的SPI机制可以为某个接口寻找服务实现。Java中SPI机制主要思想是将装配的控制权移到程序之外，在模块化设计中这个机制尤其重要，
 * 其核心思想就是 解耦。
 */
public class Spi {
    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = load.iterator();
        while (iterator.hasNext()){
            SpiService spiService = iterator.next();
            spiService.search("张三");
        }
    }
}
