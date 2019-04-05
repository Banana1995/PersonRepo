package banana.generictypestudy;

public  abstract  class Apple extends Fruit<String> {


    private String name;

    public Apple() {
    }

    public Apple(String name) {
        this.name = name;
    }
}
