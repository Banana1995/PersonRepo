package designpattern.observepattern;

import java.util.List;

/**
 * 抽象目标类
 */
public abstract class AttackTarget {

    //声明一个观察者集合
    protected List<AbsChaser> chaserlist;

    //通知所有的观察者
    public abstract void notifyAllChaser();

    //加入观察者集合
    public abstract void joinChasers(AbsChaser chaser);

    //退出观察者集合
    public abstract void exitChasers(AbsChaser chaser);
}
