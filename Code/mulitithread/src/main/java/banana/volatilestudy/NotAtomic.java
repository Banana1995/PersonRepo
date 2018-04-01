package banana.volatilestudy;

public class NotAtomic {

    private static volatile int na;

    public synchronized void naIncreament(){
        for(int i=0;i<100;i++){
           na++;
        }
        System.out.println("na的值为："+na);
    }

}
