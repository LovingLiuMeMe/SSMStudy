package cn.lovovingliu.demo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl implements PersonService {
    @Value("唐雨")
    private String name;
    @Value("老师")
    private String role;
    @Override
    public String say() {
        return "我是一名:"+this.role+" 我的名字叫:"+this.name;
    }
}
