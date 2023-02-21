package com.zq.project.designpattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangqian
 * @date 2023/1/16 16:18
 * @description:
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory (Object target){
        this.target=target;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始事务！");
                Object invoke = method.invoke(target, args);
                System.out.println("开始事务！");
                return invoke;
            }
        });
    }
}
