package designpattern.factorypattern.facadepattern;

/**
 * 外观角色类
 */
public class FacadeRole {
    private CarDoorSystem carDoorSystem;
    private CarStructSystem carStructSystem;
    private TireSystem tireSystem;

    public FacadeRole() {
        carDoorSystem = new CarDoorSystem();
        carStructSystem = new CarStructSystem();
        tireSystem = new TireSystem();
    }

    public void madeCar() {
        carStructSystem.madeCarStruct();
        carDoorSystem.madeCarDoor();
        tireSystem.madeTire();
        System.out.println("整车制造完成");
    }
}
