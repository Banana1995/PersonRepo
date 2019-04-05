import com.banana.multithread.UEHLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThreadTest {

    static class ThreadC extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 500; i++) {
                System.out.println("i的值为：" + i);
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        final String npe = null;
        Runnable npeRunable = new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    try {
                    Thread.currentThread().setUncaughtExceptionHandler(new UEHLogger());
                    System.out.println("before NPE");
                    npe.charAt(0);
                    System.out.println("after NPE");

//                        Thread.sleep(1000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
//        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
////                System.out.println("fahfa");
//                r.run();
//                Thread.currentThread().setUncaughtExceptionHandler(new UEHLogger());
//                return null;
//            }
//        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.execute(npeRunable);
        executorService.submit(npeRunable);
        Thread.sleep(10000000);
    }


}
