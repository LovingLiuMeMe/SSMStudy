package cn.lovingliu.aop.demo1;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class DemoTest1 {
    @Test
    /*
    * 类的变量操作
    * */
    public void test1(){
        //1.获取类名
        Class cls = SonClass.class;
        System.out.println(cls.getName());
        //2.获取所有的public访问权限的变量(包括当前类声明的和从父类继承的[包括父类继承而来的属性])
        Field[] fileds = cls.getFields();

        //3.获取所有本类声明的变量(私有属性也可访问)
        //Field[] fileds = cls.getDeclaredFields();
        for (Field filed: fileds) {
            // 2.1 获取访问权限并输出
            int modifiers = filed.getModifiers();
            System.out.print("访问权限:"+ Modifier.toString(modifiers));
            // 2.2 输出变量的类型和变量名
            System.out.print("类型:"+filed.getType().getName()+" 名称:"+filed.getName());
            System.out.println();
        }
    }
    @Test
    /*
     * 类的方法的操作 注意Object中的方法也会被遍历出
     * */
    public void test2(){
        Class cls = SonClass.class;
        //1.获取所有public访问权限的方法（包括自己声明和父类继承）
        Method[] methods = cls.getMethods();

        //2.获取本类的所有的方法(包括private)
        //Method[] mMethods = cls.getDeclaredMethods();

        //3.便利所有的方法
        for (Method method: methods) {
            //3.1 获取方法的访问权限
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers)+" ");
            // 3.2 获取并输出方法的返回值类型
            // 3.3 获取方法的名称
            Class returnType = method.getReturnType();
            System.out.print(returnType.getName()+" "+method.getName());
            // 3.4 获取并输出方法的参数
            Parameter[] parameters = method.getParameters();
            System.out.print("(");
            for (Parameter parameter:parameters){
                System.out.print(parameter.getType().getName()
                        + " " + parameter.getName() + ",");
            }
            System.out.print(")");
            // 3.5 获取并输出方法跑出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if(exceptionTypes.length == 0){
                System.out.println("");
            }else{
                for (Class c : exceptionTypes) {
                    System.out.println(" throws "
                            + c.getName());
                }
            }

        }

    }



}
