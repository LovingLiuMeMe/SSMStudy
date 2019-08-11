package cn.lovingliu.aop.demo3.staticproxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class DemoTest {
    @Test
    public void test(){
        UserDao target = new UserDaoImpl();//要代理的目标对象
        MyInvocationHandler invocationHandler = new MyInvocationHandler(target);
        UserDao proxy = (UserDao)Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
        proxy.save();
        //save方法日志 1565359655124
        //保存UserDao
    }
}
