package cn.lovingliu.aop.demo2;

public class DemoClass {
    private String msg = "Hello Demo";
    private final String  FINAL_VALUE;
    public DemoClass(){
        FINAL_VALUE = "FINAL_VALUE";
    }

    private void demoMethod(String a,Integer b){
        System.out.println(a+b);
    }
    public String getMsg(){
        return this.msg;
    }
    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }
}
