package designpattern.singlepattern;

public class SingletonFactory {

    /*实现一个枚举类，域为单例类对象*/
    private enum SingletonEnum {
        SINGLETON;
        private Singleton singleton;
        /*实现一个构造器，在初始化枚举的时候加载这个域，即单例类的实例化对象*/
        SingletonEnum() {
            singleton = new Singleton();
        }
        /*获取枚举域信息的get方法*/
        public Singleton getSingleton() {
            return singleton;
        }
    }

    /*实现一个获取单例类对象的静态方法*/
    public static Singleton getSingletonByFactory() {
        return SingletonEnum.SINGLETON.getSingleton();
    }
}

/*实现单例的类*/
class Singleton {
    /*单例类的构造器*/
    public Singleton() {

    }
}

