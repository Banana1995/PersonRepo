package designpattern.factorypattern.abstractfactorypattern;

public class AbsClientUser {
    public static void main(String[] args) {
        SpecificFactory1 sp1 = new SpecificFactory1();
        /*客户端使用时不需要关心C和D是否是同一种风格，
        只要使用父类来接收产品对象，并且使用就可以了*/
        ProductD productD = sp1.createProductD();
        ProductC productC = sp1.createProductC();
    }
}
