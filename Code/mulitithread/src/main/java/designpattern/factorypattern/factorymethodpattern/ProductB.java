package designpattern.factorypattern.factorymethodpattern;

import designpattern.factorypattern.Product;

public class ProductB extends Product {
    @Override
    public String getProductName() {
        return "ProductB";
    }

    @Override
    public String getAttributes() {
        return this.Attributes;
    }
}
