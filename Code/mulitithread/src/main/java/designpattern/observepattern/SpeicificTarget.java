package designpattern.observepattern;

import java.util.ArrayList;

/**
 * 具体的观察目标
 */
public class SpeicificTarget extends AttackTarget {

    public SpeicificTarget() {
        this.chaserlist = new ArrayList();
    }

    //通知所有的观察者
    @Override
    public void notifyAllChaser() {
        for (AbsChaser chaser : chaserlist
                ) {
            chaser.update();
        }
    }

    //加入观察者集合
    @Override
    public void joinChasers(AbsChaser chaser) {
        this.chaserlist.add(chaser);
    }

    //退出观察者集合
    @Override
    public void exitChasers(AbsChaser chaser) {
        this.chaserlist.remove(chaser);
    }
}
