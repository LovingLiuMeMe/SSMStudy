package cn.lovovingliu.demo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudenServiceImpl implements PersonService {
    @Value("刘波")
    private String name;
    @Value("学生")
    private String role;

    @Override
    public String say() {
        return "我是一名:"+this.role+" 我的名字叫:"+this.name;
    }
}
