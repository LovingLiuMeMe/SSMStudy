package cn.lovingliu.service.impl;

import cn.lovingliu.service.PeopleService;

public class StudentServiceImpl implements PeopleService {
    // 不实现构造函数 会默认有一个无参构造函数
    private String name;
    private String type;

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

    @Override
    public String say() {
        return "学生信息:{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
