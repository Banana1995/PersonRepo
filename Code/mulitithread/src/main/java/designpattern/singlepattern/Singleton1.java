package designpattern.singlepattern;

import java.io.Serializable;

public enum Singleton1 implements Serializable {

    SINGLETON;

    //定义实例
    private  MySingleton entity;

    //枚举类的构造方法在类加载的时候被实例化
    Singleton1() {
        //new一个单例类对象作为枚举的域
        entity = new MySingleton();
    }

    public MySingleton getEntity() {
        return entity;
    }

    //需要实现单例的类
    class MySingleton{
        public MySingleton(){

        }
    }
}
