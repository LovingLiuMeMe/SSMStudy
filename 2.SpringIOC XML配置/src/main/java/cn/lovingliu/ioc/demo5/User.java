package cn.lovingliu.ioc.demo5;

public class User {

    private String name;
    private Integer age;

    public User(String name ,Integer age){
        this.name = name;
        this.age = age;
    }

    public void say(){
        System.out.println("Hello my name is "+name+" and My age is "+age);
    }
}
