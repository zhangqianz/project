package com.zq.project.designpattern.dynamicProxy;

/**
 * @author zhangqian
 * @date 2023/1/16 16:38
 * @description:  21种设计模式之动态代理
 *
 */
public class DynamicProxy {

    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();
        UserService instance = (UserService)new ProxyFactory(userService).getInstance();
        instance.addUser();
        instance.deleteUser();
        instance.getName();
    }
}
