package designpattern.factorypattern.factorymethodpattern;

import designpattern.factorypattern.Product;

public class SpecificClientUser {
    public static void main(String[] args) {
        /*使用抽象工厂类接受具体工厂类创建的对象，创建哪种具体工厂类可以通过配置文件的方式实现*/
        AbsFactory factory = new SpecificFacoryB();
        /*工厂方法创建产品对象*/
        Product product = factory.createProduct();
        /*调用产品公共方法*/
        product.setPubMethod();
        /*调用产品私有方法*/
        product.getAttributes();

    }
}
