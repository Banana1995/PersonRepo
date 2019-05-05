package concurrency.pratice.taskexecutor;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.*;

public class PracticeFuture {


    public void handleService() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable ra = new Runnable() {
            @Override
            public void run() {
                System.out.println("is is ra runable");
            }
        };
        Callable<Integer> ci = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("this is callable return 666");
//                Thread.sleep(10000);
                return 666;
            }

            String a = "fafef";



        };
        FutureTask<Integer> fs = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(new String("future task in call method"));
                String a = "123";

                char c= ' ';
            StringBuilder te=    new StringBuilder(c);
                String[] afae = new String[2];
                StringUtils.isEmpty(a);
                return 999;
            }
        });
        executorService.submit(fs);
        Future<?> subFuture = executorService.submit(ra);
        Future<Integer> integerFuture = executorService.submit(ci);

        try {
            Integer ciResult = integerFuture.get();
            System.out.println("the callable resule is " + ciResult);
            Object o = subFuture.get();
            fs.get();
            System.out.println("future task get method return:" + fs.get());
            System.out.println("future task get method second return:");
            System.out.println("future task get method thrid return:" + fs.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        String a = "";
        if (a.length()==0) {
            System.out.println("faef");
        }
    }


}
