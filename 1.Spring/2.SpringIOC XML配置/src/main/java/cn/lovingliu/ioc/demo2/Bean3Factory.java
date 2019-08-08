package cn.lovingliu.ioc.demo2;

public class Bean3Factory {
    public Bean3 getBean3(){
        System.out.println("使用实例工厂实例化的方法 实例化一个Bean3对象");
        return new Bean3();
    }
}
