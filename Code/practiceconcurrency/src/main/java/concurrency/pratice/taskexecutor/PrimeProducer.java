package concurrency.pratice.taskexecutor;

import java.util.concurrent.BlockingDeque;

public class PrimeProducer extends Thread {

    private final BlockingDeque<Integer> queue;

    public PrimeProducer(BlockingDeque<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Integer p = 1;
            for (int j =0 ;j<23; j++) {
                while (!PrimeProducer.interrupted()) {
                    Thread.sleep(100);
                    p++;
                }
                System.out.println("the interrupt flag value :"+PrimeProducer.interrupted());
                System.out.println("the p value :"+p);
                if (PrimeProducer.interrupted()) {
                    System.out.println("the interrupt flag is trigger and  reset,the p value: " + p);
                }
            }
            System.out.println("the interrupt flag is true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancle() {
        System.out.println("cancle triggered!");
        interrupt();
    }
}
