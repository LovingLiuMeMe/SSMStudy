package cn.lovingliu.aop.demo3.staticproxy;

public class StaticProxy  {

    private UserDao target;

    public StaticProxy(UserDao userDao){
        this.target = userDao;
    }

    public void save(){
        System.out.println("保存前的时间戳:"+System.currentTimeMillis());
        target.save();
        System.out.println("保存后的时间戳:"+System.currentTimeMillis());
    }
}
