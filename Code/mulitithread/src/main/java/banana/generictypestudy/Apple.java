package banana.generictypestudy;

public class Apple extends Fruit<String> {

    private String name;

    public Apple(String name) {
        this.name = name;
    }
}
