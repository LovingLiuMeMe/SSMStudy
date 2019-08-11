package cn.lovingliu.aop.demo3.staticproxy;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("保存UserDao");
    }

    @Override
    public void delete() {
        System.out.println("删除UserDao");
    }

    @Override
    public void find() {
        System.out.println("查询UserDao");
    }

    @Override
    public void update() {
        System.out.println("更新UserDao");
    }
}
