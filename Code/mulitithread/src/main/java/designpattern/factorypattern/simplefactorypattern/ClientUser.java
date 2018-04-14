package designpattern.factorypattern.simplefactorypattern;

import designpattern.factorypattern.Product;

public class ClientUser {

    public static void main(String[] args) {
        /*使用工厂方法创建对象*/
        Product product = SimpleFactory.createProduct("A");
        /*调用产品的公用方法*/
        product.setPubMethod();
        /*调用产品A的私有方法*/
        String attributes = product.getAttributes();
        String productName = product.getProductName();
    }
}
