package banana.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;

public class callableThread implements Callable<String> {
    /**
     * call方法作为线程执行体
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println("这是call方法的输出语句");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            Thread.sleep(10);
        }
        return "awesome";
    }
}

class callableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(new callableThread());
        Thread testThread = new Thread(futureTask, "获取返回值线程");
        testThread.start();
        Thread.sleep(100);
        boolean ca = futureTask.cancel(false);
        System.out.println("ca:" + ca);

        String abc = null;
//        abc = futureTask.get(5, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName() + "返回值为：" + abc);
    }
}
