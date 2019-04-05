import java.util.Map;

public class FruitHold<T > {

    private Map<String, T> fruit;

    public Map<String, T> getFruit() {
        return fruit;
    }

    public void setFruit(Map<String, T> fruit) {
        this.fruit = fruit;
    }
}
