package cn.lovovingliu.demo4;

import javax.annotation.Resource;

public class ProductService {
    @Resource(name = "productDao")
    private ProductDao productDao;
    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;

    public void save(){
        this.productDao.save();
        this.categoryDao.save();
        System.out.println("ProductService 的save 方法执行");
    }
}
