package banana.notify_wait_study;

public class ThreadA extends Thread {


    private Object lock;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public ThreadA(Object lock) {
        this.lock = lock;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        super.run();
        synchronized (lock){
            if (MyList.size()!=5) {
                System.out.println("当MyList没有到5的时候开始等待..."+System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待结束"+System.currentTimeMillis());
                for (int i =0 ;i<3;i++){
                    MyList.add();
                    System.out.println("添加元素："+MyList.size());
                }
            }
        }


    }
}
