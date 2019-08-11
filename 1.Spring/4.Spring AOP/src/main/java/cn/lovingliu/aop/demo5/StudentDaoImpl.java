package cn.lovingliu.aop.demo5;

public class StudentDaoImpl{
    public void save() {
        System.out.println("StudentDao 保存");
    }

    public void delete() {
        System.out.println("StudentDao 删除");
    }

    public void find() {
        System.out.println("StudentDao 查询");
    }

    public void update() {
        System.out.println("StudentDao 更新");
    }
}
