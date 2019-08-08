package cn.lovovingliu.demo1;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {
    public void save(){
        System.out.println("用户保存");
    }
}
