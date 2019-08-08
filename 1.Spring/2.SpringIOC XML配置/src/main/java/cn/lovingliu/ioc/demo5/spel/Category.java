package cn.lovingliu.ioc.demo5.spel;

public class Category {
    private String name;// 分类名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "分类:"+this.name;
    }
}
