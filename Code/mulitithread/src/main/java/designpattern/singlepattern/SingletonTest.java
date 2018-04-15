package designpattern.singlepattern;

import designpattern.singlepattern.Singleton1;

import java.io.*;

public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        //region 将单例对象序列化
//        Singleton1 singleton = Singleton1.getEntity();
        File file = new File("Singleton1.txt");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            //执行序列化
//            oos.writeObject(singleton);
            //输出被序列化对象的hashcode
//            System.out.println(singleton.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //endregion


        //region 反序列化单例对象
        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            Singleton1 deSerialEntity = (Singleton1) ois.readObject();
            //输出反序列化后对象的hashcode
            System.out.println(deSerialEntity.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //endregion
    }
}
