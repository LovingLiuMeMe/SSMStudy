package cn.lovingliu.ioc.demo3;

public class Man {
    public Man(){
        System.out.println("Man被实例化了------");
    }
    public void initMethod(){
        System.out.println("Man被初始化了------");
    }
    public void destoryMethod(){
        System.out.println("Man被销毁了------");
    }
}
