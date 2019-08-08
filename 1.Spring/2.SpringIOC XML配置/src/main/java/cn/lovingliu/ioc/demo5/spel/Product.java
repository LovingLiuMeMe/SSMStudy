package cn.lovingliu.ioc.demo5.spel;

public class Product {
    private String name;
    private Double price;
    private Category category;
    private Double discountPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "商品名称:"+this.name+" 商品价格:"+this.price+" 商品优惠的价格"+this.discountPrice+" 商品分类"+this.category.toString();
    }
}
