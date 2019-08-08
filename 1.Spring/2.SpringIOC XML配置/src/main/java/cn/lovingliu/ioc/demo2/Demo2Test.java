package cn.lovingliu.ioc.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo2Test {
    @Test
    /*
    * 1.实例化Bean三种方式-使用构造函数实例化对象
    * */
    public void demo1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean1 bean1 = (Bean1) applicationContext.getBean("bean1");
    }

    @Test
    /*
     * 2.实例化Bean三种方式-使用静态工厂函数实例化对象
     * */
    public void demo2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean2 bean2 = (Bean2) applicationContext.getBean("bean2");
    }

    @Test
    /*
     * 3.实例化Bean三种方式-使用实例工厂实例化对象
     * */
    public void demo3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean3 bean3 = (Bean3) applicationContext.getBean("bean3");
    }
}
