import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadTest {

    public static void main(String[] args) throws InterruptedException {
//        MultiThread thread = new MultiThread();
//        for (int i=1;i<100;i++) {
//            thread.ImitateUser("第"+i+"节点");
//            Thread.sleep(2000);
//        }
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入：");
//        /*x,y均不大于n*/
//        /*x除以y的余数大于k*/
//        int n = sc.nextInt();//2=<n<=50
//        int k = sc.nextInt(); //0<k<n-1
//        int i = 0;
//        int x;
//        int y;
//        boolean flag = true;
//        while (flag) {
//            for (x=1;x<n;x++){
//                for (y=1;y<n;y++){
//                    float cc = x%y;
//                    if (cc>k){
//                        i++;
//                    }
//                }
//            }
//            flag =false;
//        }
//        System.out.println(i);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//n表示转向次数
        String turn = sc.next();//表示L/R的字符串
        char[] nc = turn.toCharArray();
        char l = 'L';
        char r = 'R';
        int l_num = 0;
        int r_num = 0;
        for (int i = 0; i < nc.length; i++) {
            if (l == nc[i]) {
                l_num++;
            } else {
                r_num++;
            }
        }
        //左转次数减去右转次数
        int resu_num = l_num - r_num;

        if (resu_num ==0){
            System.out.println("N");
        }else if (resu_num ==1 || resu_num == -3){
            System.out.println("W");
        }else if (resu_num ==2 || resu_num ==-2){
            System.out.println("S");
        }else if (resu_num==3|| resu_num == -1){
            System.out.println("E");
        }


    }


}
