package banana.ucfpay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadC extends Thread {
    private Lock lockc;
    private Condition conditionA;
    private Condition conditionC;


    public ThreadC(Lock lockc, Condition conditionA, Condition conditionC) {
        this.lockc = lockc;
        this.conditionA = conditionA;
        this.conditionC = conditionC;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lockc.lock();
                super.run();
                System.out.println("C");
                conditionA.signal();
                conditionC.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockc.unlock();
            }
        }

    }
}
