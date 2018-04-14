package designpattern.factorypattern.abstractfactorypattern;

/**
 * 创建数字1风格的不同产品
 * 即不同等级结构的相关联的一个产品族
 */
public class SpecificFactory1 extends AbsFactory {

    /*创建产品C1*/
    @Override
    public ProductC createProductC() {
        return new ProductC1();
    }
    /*创建产品D1*/
    @Override
    public ProductD createProductD() {
        return new ProductD1();
    }
}
