package banana.notify_wait_study;

public class ThreadB extends Thread {

    private Object lock;
    public ThreadB(Object lock) {
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
            boolean f=true;
            while (f) {
                if (MyList.size()==5){
                    System.out.println("开始发出通知："+System.currentTimeMillis());
                    lock.notify();
                    System.out.println("通知结束："+System.currentTimeMillis());
                    f =false;
                }else {
                    MyList.add();
                    System.out.println("添加了元素:"+MyList.size());
                }
            }
        }
    }
}
