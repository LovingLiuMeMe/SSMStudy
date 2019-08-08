package cn.lovingliu.ioc.demo5;

import cn.lovingliu.ioc.demo5.spel.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest5 {
    @Test
    public void demo1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo5.xml");
        User user = (User)applicationContext.getBean("user");
        user.say();// Hello my name is 刘波 and My age is 1
    }

    @Test
    public void demo2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo5.xml");
        Person person = (Person)applicationContext.getBean("person");
        person.say();//my name is 张三 my age is 10 我养了一只宠物 猫: 名字:小明 描述:白色的毛 非常的可爱呢!
    }

    @Test
    public void demo3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo5.xml");
        People people = (People)applicationContext.getBean("people");
        people.say();//my name is 小王 my age is 24 我养了一只宠物 猫: 名字:小明 描述:白色的毛 非常的可爱呢!
    }

    @Test
    public void demo4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo5.xml");
        Product product = (Product)applicationContext.getBean("product");
        System.out.println(product); //商品名称:ReadMi K20Pro 商品价格:2199.0 商品优惠的价格30.70598711201631 商品分类分类:电子产品
    }

}
