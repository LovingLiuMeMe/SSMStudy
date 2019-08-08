package cn.lovingliu.test;

import cn.lovingliu.iocFactory.ApplicationContext;
import cn.lovingliu.iocFactory.impl.ClassPathXMLApplicationContext;
import cn.lovingliu.service.PeopleService;

public class MyIOCTest {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXMLApplicationContext("applicationContext.xml");
        PeopleService people = (PeopleService) context.getBean("TeacherServiceImpl");
        System.out.println(people.say());
    }
}
