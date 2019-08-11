package cn.lovingliu.aop.demo1;

public class SonClass extends ParentClass {
    public String sname;
    public Integer sage;
    private String snumber;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSonInfo(){
        return "son name:"+this.sname+" age:"+this.sage;
    }
}
