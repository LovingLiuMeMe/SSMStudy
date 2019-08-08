package cn.lovovingliu.demo3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("bean1")
public class Bean1 {

    public Bean1(){
        System.out.println("实例化--------");
    }
    @PostConstruct
    public void init(){
        System.out.println("实例化前--------");
    }
    public void run(){
        System.out.println("实例化完成并调用方法--------");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁后--------");
    }
}
