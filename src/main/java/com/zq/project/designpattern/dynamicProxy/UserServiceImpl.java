package com.zq.project.designpattern.dynamicProxy;

/**
 * @author zhangqian
 * @date 2023/1/16 16:14
 * @description:
 */
public class UserServiceImpl implements UserService {

    @Override
    public void getName() {
        System.out.println("获取名称");
    }

    @Override
    public void addUser() {
        System.out.println("添加用户");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户");
    }
}
