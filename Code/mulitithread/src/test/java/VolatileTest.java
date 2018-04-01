import banana.volatilestudy.NotAtomic;
import banana.volatilestudy.VolatileThread;

public class VolatileTest {

    public static void main(String[] args) {
        NotAtomic notAtomic = new NotAtomic();
        VolatileThread[] volatileThreads = new VolatileThread[50];
        for (int i =0 ;i<volatileThreads.length;i++){
            volatileThreads[i] = new VolatileThread(notAtomic);
        }
        for (int i =0 ;i<volatileThreads.length; i++){
            volatileThreads[i].start();
        }
    }


}
