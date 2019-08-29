package cn.lovingliu.demo2;

import javax.annotation.Resource;

public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in,money);
        //int i = 10/0;
        accountDao.outMoney(out,money);
    }
}
