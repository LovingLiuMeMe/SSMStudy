package cn.lovingliu.aop.demo7;

public class CustomerDao {
    public void save() {
        System.out.println("CustomerDao 保存");
    }

    public void delete() {
        System.out.println("CustomerDao 删除");
    }

    public void find() {
        System.out.println("CustomerDao 查询");
    }

    public void update() {
        System.out.println("CustomerDao 更新");
    }
}
