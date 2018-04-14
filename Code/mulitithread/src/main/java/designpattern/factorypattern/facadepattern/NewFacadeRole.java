package designpattern.factorypattern.facadepattern;

public class NewFacadeRole extends AbsFacadeRole {

    private CarEngineSystem carEngineSystem;

    public NewFacadeRole() {
        super();
        carEngineSystem = new CarEngineSystem();
    }

    @Override
    public void madeCar() {
        this.carDoorSystem.madeCarDoor();
        this.carStructSystem.madeCarStruct();
        this.tireSystem.madeTire();
        this.carEngineSystem.madeEngine();
        System.out.println("新的一辆车制造完成");
    }
}
