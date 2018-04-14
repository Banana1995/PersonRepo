package designpattern.factorypattern.abstractfactorypattern;

import designpattern.factorypattern.Product;

public class ProductD1 extends ProductD{
    private String color;


    @Override
    public String getProductName() {
        return "ProductD1";
    }

    @Override
    public String getAttributes() {
        return this.Attributes;
    }
}
