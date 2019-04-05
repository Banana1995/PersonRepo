import banana.generictypestudy.Fruit;
import banana.generictypestudy.LittleApple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class CollectFruit {

    private FruitHold<? extends Apple> fruitHold;

    private FruitHold<Apple> appleFruitHold;

    public void setFruitHold() {
        FruitHold<Apple> apple = new FruitHold<>();
        FruitHold<? extends Apple> littleApple = new FruitHold<>();
        Map<String, FruitHold<Apple>> appleMap = new HashMap<>();
        appleMap.put("apple", apple);
//        appleMap.put("apple", littleApple);
        Map<String, Object> objStringMap = new HashMap<>();
        objStringMap.put("apple", apple);
        objStringMap.put("apple", new Fruit<>());
//        LittleApple littleApple1 = new LittleApple();
//        littleApple1.setLittleName();
//
    }

    public static void main(String[] args) {

        double begin = 100000;
        double self = 100000;
        for (int i = 0; i < 10; i++) {
            begin = (begin+50000+(i+1)*5000) * 1.16;
            self += 50000 + (i + 1) * 6000;
        }
        System.out.println(begin);
        System.out.println(self);




    }


}
