package concurrency.pratice.taskexecutor;

import java.util.concurrent.LinkedBlockingDeque;

public class PracticeCancleInterrupt {


    public static void main(String[] args) {

        PrimeProducer producer = new PrimeProducer(new LinkedBlockingDeque<Integer>(100));
        producer.start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < 3; i++) {
            producer.cancle();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
