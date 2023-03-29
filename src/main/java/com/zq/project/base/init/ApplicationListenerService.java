package com.zq.project.base.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangqian
 * @date 2023/3/28 14:16
 * @description: 我们在开发过程中会有这样的场景：需要在项目启动后执行一些操作，比如：读取配置文件信息，数据库连接，,删除临时文件，清除缓存信息，工厂类初始化，加载活动数据，或者缓存的同步等。我们会用多种实现方式，
 * 例如@PostConstruct、CommandLineRunner、ApplicationRunner、ApplicationListener都可以实现在springboot启动后执行我们特定的逻辑，接下来对比他们的区别
 * https://blog.csdn.net/nlcexiyue/article/details/129443600
 */
//@Component
@Slf4j
public class ApplicationListenerService implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("加载完成：ApplicationListener");
    }
}
