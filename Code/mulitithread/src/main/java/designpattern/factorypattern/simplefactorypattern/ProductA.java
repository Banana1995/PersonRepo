package designpattern.factorypattern.simplefactorypattern;

import designpattern.factorypattern.Product;

public class ProductA extends Product {
    @Override
    public String getProductName() {
        return "ProductA";
    }

    @Override
    public String getAttributes() {
        return this.Attributes;
    }
}
