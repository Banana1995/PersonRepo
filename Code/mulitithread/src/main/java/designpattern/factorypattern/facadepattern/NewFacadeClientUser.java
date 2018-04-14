package designpattern.factorypattern.facadepattern;

public class NewFacadeClientUser {
    public static void main(String[] args) {
        /*面向抽象外观模式类编程，具体实现类对象可以通过配置文件和反射来获取*/
        AbsFacadeRole facadeRole = new NewFacadeRole();
        /*通过抽象外观模式类来调用同一入口方法*/
        facadeRole.madeCar();
    }
}
