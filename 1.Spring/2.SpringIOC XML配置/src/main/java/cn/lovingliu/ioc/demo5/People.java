package cn.lovingliu.ioc.demo5;

public class People {
    private String name;
    private Integer age;

    private Cat cat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void say(){
        System.out.println("my name is "+name+" my age is "+age+" 我养了一只宠物 "+ this.cat.toString());
    }
}
