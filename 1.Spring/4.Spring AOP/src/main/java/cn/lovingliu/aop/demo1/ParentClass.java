package cn.lovingliu.aop.demo1;

public class ParentClass{
    public String pname;
    public Integer page;
    private String pnumer;

    public String getParentInfo(){
        return "父亲的名称是"+this.pname+"年龄是:"+this.page;
    }
}
