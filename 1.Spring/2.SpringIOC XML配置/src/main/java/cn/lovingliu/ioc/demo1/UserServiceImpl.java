package cn.lovingliu.ioc.demo1;

public class UserServiceImpl implements UserService{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void say() {
        System.out.println("hello serviceImpl ! my name is "+this.name);
    }
}
