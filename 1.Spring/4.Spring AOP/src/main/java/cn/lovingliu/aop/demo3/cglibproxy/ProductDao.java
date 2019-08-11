package cn.lovingliu.aop.demo3.cglibproxy;

public class ProductDao {
    public void save(){
        System.out.println("保存商品成功");
    }
    public void delete(){
        System.out.println("删除商品成功");
    }
    public void find(){
        System.out.println("查询商品成功");
    }
    public void update(){
        System.out.println("修改商品成功");
    }
}
