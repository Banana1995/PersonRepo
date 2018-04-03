package banana.ucfpay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadB extends Thread {
    private Lock lockb;
    private Condition conditionB;
    private Condition conditionC;

    public ThreadB(Lock lockb, Condition conditionB, Condition conditionC) {
        this.lockb = lockb;
        this.conditionB = conditionB;
        this.conditionC = conditionC;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                super.run();
                lockb.lock();

                System.out.println("B");
                conditionB.await();
                conditionC.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockb.unlock();
            }
        }
    }
}
