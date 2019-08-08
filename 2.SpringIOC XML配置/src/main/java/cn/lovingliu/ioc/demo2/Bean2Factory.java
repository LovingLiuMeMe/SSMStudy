package cn.lovingliu.ioc.demo2;

public class Bean2Factory {
    public static Bean2 getBean2(){
        System.out.println("使用静态工厂方法实例化Bean2 的对象");
        return new Bean2();
    }
}
