package designpattern;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton implements Serializable {

    //定义实例
    private static Singleton entity;

    static {
        entity = new Singleton();
    }
    //私有化构造器
    private Singleton() {
    }

    //提供一个访问单例对象的静态方法
    public static Singleton getEntity()   {

        return entity;
    }

//    private Object readResolve(){
//        return entity;
//    }

}
