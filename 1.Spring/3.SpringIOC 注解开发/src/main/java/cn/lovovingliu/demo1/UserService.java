package cn.lovovingliu.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
*
* 传统方式要去xml文件中配置bean标签 <bean id="xxx" class="xxx"></bean>
* */
@Service("userService")
public class UserService {
    @Value("面包")
    private String food;

    @Resource(name = "userDao")
    private UserDao userDao;

    public String say(){
        return "hello spring annatation";
    }
    public void eat(){
        System.out.println("我吃的食物是 "+food);
    }

    public void save(){
        System.out.println("调用UserDao中的save方法");
        userDao.save();
    }
}
