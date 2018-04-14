package designpattern.factorypattern.abstractfactorypattern;

/**
 * 创建数字2风格的一组不同产品
 * 即不同等级结构的相关联的一个产品族
 */
public class SpecificFactory2 extends AbsFactory {

    /*创建产品C2*/
    @Override
    public ProductC createProductC() {
        return new ProductC2();
    }
    /*创建产品D2*/
    @Override
    public ProductD createProductD() {
        return new ProductD2();
    }
}
