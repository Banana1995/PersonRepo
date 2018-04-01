import banana.notify_wait_study.ThreadA;
import banana.notify_wait_study.ThreadB;

public class NotifyWaitTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadB b = new ThreadB(lock);
        a.start();
        Thread.sleep(50);
        b.start();

    }


}
