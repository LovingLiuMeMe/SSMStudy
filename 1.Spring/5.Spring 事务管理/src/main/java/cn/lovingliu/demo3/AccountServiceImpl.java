package cn.lovingliu.demo3;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,readOnly = false,rollbackFor = java.lang.Exception.class,noRollbackFor = java.lang.ArithmeticException.class)
/*
* propagation:事务的传播行为
* isolation:事务的隔离级别
* readOnly:是否只是可读
* rollbackFor:发生什么错误回滚
* noRollbackFor:发生什么错误不回滚
*
 * */
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in,money);
        int i = 10/0;
        accountDao.outMoney(out,money);
    }
}
