package designpattern.factorypattern.abstractfactorypattern;

public abstract class AbsFactory {
    /*抽象工厂类，定义了一个产品工厂需要创建的产品族*/
    public abstract ProductC createProductC();
    public abstract ProductD createProductD();

}
