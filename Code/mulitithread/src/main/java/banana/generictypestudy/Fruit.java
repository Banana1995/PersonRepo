package banana.generictypestudy;

public class  Fruit<T> {

    private T fruit;


    private void createFruit(Class<T> cl) throws IllegalAccessException, InstantiationException {
        this.fruit = cl.newInstance();
    }


}
