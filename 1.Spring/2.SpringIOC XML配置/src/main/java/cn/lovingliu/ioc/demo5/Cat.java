package cn.lovingliu.ioc.demo5;

public class Cat {
    private String name;
    private String dec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Override
    public String toString() {
        return "猫: 名字:"+name+" 描述:"+dec;
    }
}
