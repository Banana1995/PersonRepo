import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadTest {

    static class ThreadC extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i =0 ;i<500;i++){
                System.out.println("i的值为："+i);
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        List<String> b = new ArrayList<>();
        b.add("123");
        System.out.println(b);

    }


}
