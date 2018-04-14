package designpattern.factorypattern.factorymethodpattern;

import designpattern.factorypattern.Product;

public class SpecificFacoryB implements AbsFactory {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
