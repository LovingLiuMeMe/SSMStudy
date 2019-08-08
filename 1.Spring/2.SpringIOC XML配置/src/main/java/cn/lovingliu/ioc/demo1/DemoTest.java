package cn.lovingliu.ioc.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DemoTest {
    @Test
    /*
    * 1.传统方式 耦合严重
    * */
    public void demo1(){
        UserService userService = new UserServiceImpl();
        // 当使用接口实现部分作为实际对象的接收着的时候 并没有办法设置属性
        //userService.setName();

        userService.say();
    }

    @Test
    public void demo2(){
        // 创建Spring的工厂
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 通过工厂来获得类
        UserService userService = (UserService)applicationContext.getBean("userService");

        userService.say();
    }

    @Test
    public void demo3(){
        // 工厂类不一样
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("//Users//lovingliu//Desktop//springioc//src//main//resources//applicationContext.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.say();
    }

}
