package cn.lovingliu.demo2;

// 转账案例的业务层 接口
public interface AccountService {
    public void transfer(String out, String in, Double money);
}
