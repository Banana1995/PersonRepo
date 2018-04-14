package designpattern.factorypattern.facadepattern;

public abstract class AbsFacadeRole {
    protected CarDoorSystem carDoorSystem;
    protected CarStructSystem carStructSystem;
    protected TireSystem tireSystem;

    public AbsFacadeRole() {
        carDoorSystem = new CarDoorSystem();
        carStructSystem = new CarStructSystem();
        tireSystem = new TireSystem();
    }

    public abstract void madeCar()  ;
}
