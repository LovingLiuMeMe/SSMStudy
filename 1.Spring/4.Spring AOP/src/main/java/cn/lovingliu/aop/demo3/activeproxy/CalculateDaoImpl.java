package cn.lovingliu.aop.demo3.activeproxy;

public class CalculateDaoImpl implements CalculateDao{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }

    @Override
    public int multiply(int a, int b) {
        return a*b;
    }

    @Override
    public double divide(int a, int b) {
        return a/(b*1.0);
    }
}
