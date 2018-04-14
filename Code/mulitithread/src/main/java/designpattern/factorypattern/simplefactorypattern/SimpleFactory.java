package designpattern.factorypattern.simplefactorypattern;

import designpattern.factorypattern.Product;
import designpattern.factorypattern.factorymethodpattern.ProductB;

public class SimpleFactory {

    public static Product createProduct(String name) {
        switch (name) {
            case "A":
                ProductA a = new ProductA();
                a.setPubMethod();
                return a;
            case "B":
                ProductB b = new ProductB();
                b.setPubMethod();
                return b;
            default:
                return null;
        }
    }

}
