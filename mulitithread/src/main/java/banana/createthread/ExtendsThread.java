package banana.createthread;

/**
 * 创建线程的三种方式
 */
public class ExtendsThread extends Thread {
    /**
     * 重写run方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(this.getName() + "输出数字：" + i);
        }
    }
}

class testThread {
    public static void main(String[] args) throws InterruptedException {

        Thread ts = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过匿名内部类的方式实现Runnable接口，并创建实例作为Thread的构造参数");
                for (int i =0 ;i<10000; i++){
                    System.out.println(Thread.currentThread().getName()+i);
                }
            }
        },"新线程");
        ts.start();
        for (int i =0 ;i<100000; i++){
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}