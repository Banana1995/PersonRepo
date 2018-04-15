package designpattern.observepattern;

public class observeClientUser {
    public static void main(String[] args) {
        SpeicificTarget target = new SpeicificTarget();
        //多个观察者
        SpecificChaser chaser1 = new SpecificChaser("chaser1",target);
        SpecificChaser chaser2 = new SpecificChaser("chaser2",target);
        SpecificChaser chaser3 = new SpecificChaser("chaser3",target);
        /*加入观察者队列*/
        target.joinChasers(chaser1);
        target.joinChasers(chaser2);
        target.joinChasers(chaser3);
        /*观察者调用目标类的通知方法*/
        chaser1.findTarget(target);
    }
}
