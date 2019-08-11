package cn.lovingliu.aop.demo2;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DemoTest2 {
    @Test
    /*
    * 1.访问私有方法
    * */
    public void test1() throws Exception{
        // 1.获取Class实例
        DemoClass demoClass = new DemoClass();
        Class cls = demoClass.getClass();

        // 2.获取私有方法
        //第一个参数为要获取的私有方法的名称
        //第二个为要获取方法的参数的类型，参数为 Class...，没有参数就是null
        //方法参数也可这么写 ：new Class[]{String.class , int.class}
        Method privateMethod = cls.getDeclaredMethod("demoMethod", String.class, Integer.class);

        // 3.开始操作方法
        if(privateMethod!=null){
            //获取私有方法的访问权
            //只是获取访问权 并不修改实际权限
            privateMethod.setAccessible(true);

            //使用 invoke 反射调用私有方法
            //privateMethod 是获取到的私有方法
            //demoClass是要操作的实例对象
            //后面两个参数传递实参
            privateMethod.invoke(demoClass, "Java 的反射 ", 666);//Java 的反射 666
        }
    }
    @Test
    /*
     * 1.修改私有变量
     * */
    public void test2() throws Exception{
        //1.获取 Class 类实例
        DemoClass demoClass = new DemoClass();
        Class cls = demoClass.getClass();
        //2. 获取私有变量
        Field privateField = cls.getDeclaredField("msg");

        //3.操作私有变量
        if(privateField!=null){
            // 获取私有变量的访问控制权
            privateField.setAccessible(true);
            // 修改私有变量
            System.out.println("Before Modify : MSG = "+demoClass.getMsg());
            privateField.set(demoClass,"MSG发生修改");
            System.out.println("After Modify : MSG = "+demoClass.getMsg());
            //Before Modify : MSG = Hello Demo
            //After Modify : MSG = MSG发生修改
        }
    }
    @Test
    /*
     * 3.修改私有常量
     * */
    public void test3() throws Exception{
        //1. 获取 Class 类实例
        DemoClass demoClass = new DemoClass();
        Class cls = demoClass.getClass();

        //2. 获取私有常量
        Field finalField = cls.getDeclaredField("FINAL_VALUE");

        //3. 修改常量的值
        if (finalField != null) {

            //获取私有常量的访问权
            finalField.setAccessible(true);

            //调用 finalField 的 getter 方法
            //输出 FINAL_VALUE 修改前的值
            System.out.println("Before Modify：FINAL_VALUE = "
                    + finalField.get(demoClass));

            //修改私有常量
            finalField.set(demoClass, "私有常量修改");

            //调用 finalField 的 getter 方法
            //输出 FINAL_VALUE 修改后的值
            System.out.println("After Modify：FINAL_VALUE = "
                    + finalField.get(demoClass));

            //使用对象调用类的 getter 方法
            //获取值并输出
            System.out.println("Actually ：FINAL_VALUE = "
                    + demoClass.getFINAL_VALUE());
        }
    }


}
