package com.zq.project.base.spi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
//        Iterator<SpiService> iterator = load.iterator();
//        while (iterator.hasNext()){
//            SpiService spiService = iterator.next();
//            spiService.search("张三");
//        }
//        LocalDateTime localDateTime = LocalDateTime.parse("2023-11-01 00:21:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(localDateTime);
        BigDecimal bigDecimal = BigDecimal.valueOf(119);
        BigDecimal decimal = BigDecimal.valueOf(120);
        BigDecimal divide = bigDecimal.divide(decimal, 2, RoundingMode.HALF_UP);
        System.out.println(10_100);
        System.out.println(divide.intValue());
        Integer a = 1000;
        Integer b = 1000;
        Integer c = 100;
        Integer d = 100;
        System.out.println("a == b is " + (a == b));
        System.out.println(("c == d is " + (c == d)));

        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.now().minusMinutes(1)).getSeconds());


    }
}
