package cn.lovingliu.service.impl;

import cn.lovingliu.service.PeopleService;

public class TeacherServiceImpl implements PeopleService {
    private String name;
    private String type;
    private StudentServiceImpl student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StudentServiceImpl getStudent() {
        return student;
    }

    public void setStudent(StudentServiceImpl student) {
        this.student = student;
    }

    @Override
    public String say() {
        return "我是一名:"+this.type+" 我的名字是:"+this.name+" 我的学生是:"+this.student.say();
    }
}
