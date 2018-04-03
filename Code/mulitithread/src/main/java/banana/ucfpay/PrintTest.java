package banana.ucfpay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        ThreadA a = new ThreadA(lock, conditionA, conditionB);
        ThreadB b = new ThreadB(lock, conditionB, conditionC);
        ThreadC c = new ThreadC(lock, conditionA, conditionC);
        a.start();
        Thread.sleep(2);
        b.start();
        Thread.sleep(2);
        c.start();
    }

}
