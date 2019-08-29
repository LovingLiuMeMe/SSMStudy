package cn.lovingliu.demo1;

import javax.annotation.Resource;

public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in,money);
        int i = 1/0;
        accountDao.outMoney(out,money);
    }
}
