import com.google.common.collect.Queues;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThread extends Thread{

    private ExecutorService executorService;

    private ScheduledExecutorService singleScheduledExecutorService;

    private LinkedBlockingQueue<String> queue;

    public static void main(String[] args) {
        char a = 'A'+655;
        char d = '\u0041';
        String b = "A";
        Thread dd = new Thread();
        int[] aa = {1};
        int i , x, y =0;
        String c = String.valueOf(2);
    }

//    CountDownLatch
    /**
     * 模仿用户获取序列号方法 往队列中放入数据
     *
     * @param user
     * @throws InterruptedException
     */
    public void ImitateUser(String user) throws InterruptedException {
        queue.put(user);
        Map<String,Integer> ass = new HashMap<>();
//        ass.c

        System.out.println(Thread.currentThread().getName()+"线程添加节点："+user+"，队列中共有"+queue.size()+"条数据,当前线程状态为:"+Thread.currentThread().getState());
    }

    private Runnable ImitateDeleteNode() {
        return new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getState()+" 删除了节点:"+MultiThread.this.queue.poll()+Thread.currentThread().getState());
            }
        };
    }

    /**
     * 模仿构造器方法
     */
    public MultiThread() {
        //region 初始化线程池
        this.executorService = Executors.newFixedThreadPool(1);
        //endregion

        //region 初始化单线程池
        this.singleScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //endregion

        this.queue = Queues.newLinkedBlockingQueue(3);
        System.out.println("===连接zk");

        //region 单线程间隔一段时间执行一次 删除zk所有节点
        this.singleScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (int j =0 ;!MultiThread.this.queue.isEmpty();j++) {
                    System.out.println("删除前队列节点数量为："+MultiThread.this.queue.size());
                    System.out.println(Thread.currentThread().getName()+"循环第"+j+"次"+ new Date());
                    MultiThread.this.executorService.submit(MultiThread.this.ImitateDeleteNode());
                }
            }
        }, 10, 3, TimeUnit.SECONDS);
        //endregion

        System.out.println("===结束连接zk");

    }

}
