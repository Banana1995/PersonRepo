package designpattern.singlepattern;

public class SingletonThreadTest extends Thread {
    @Override
    public void run() {
        //输出单例对象的hashcode
        System.out.println(SingletonFactory.getSingletonByFactory().hashCode());
    }

    public static void main(String[] args) {

        SingletonThreadTest[] sg = new SingletonThreadTest[100];
        for (int i =0 ;i<sg.length;i++){
            sg[i]= new SingletonThreadTest();
        }
        for (int i =0 ;i<sg.length;i++){
            sg[i].start();
        }

    }


}
