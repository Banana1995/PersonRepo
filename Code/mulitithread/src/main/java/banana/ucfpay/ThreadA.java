package banana.ucfpay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadA extends Thread{
    private Lock locka;
    private Condition conditionA;
    private Condition conditionB;

    public ThreadA(Lock locka, Condition conditionA, Condition conditionB) {
        this.locka = locka;
        this.conditionA = conditionA;
        this.conditionB = conditionB;
    }
    private void setLocka(){

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                locka.lock();
                super.run();

                System.out.println("A");
                conditionA.await();
                conditionB.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                locka.unlock();
            }
        }
    }
}
