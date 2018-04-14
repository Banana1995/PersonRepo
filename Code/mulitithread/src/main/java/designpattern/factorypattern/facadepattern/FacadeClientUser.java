package designpattern.factorypattern.facadepattern;

public class FacadeClientUser {
    public static void main(String[] args) {
        FacadeRole facadeRole = new FacadeRole();
        facadeRole.madeCar();
    }
}
