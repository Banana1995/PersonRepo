package designpattern.observepattern;

/**
 * 具体的观察者实现类
 */
public class SpecificChaser implements AbsChaser {

    private String name;
    private SpeicificTarget target;

    public SpecificChaser(String name, SpeicificTarget target) {
        this.name = name;
        this.target = target;
    }

    /*实现了抽象观察者中的update方法*/
    @Override
    public void update() {
        System.out.println("追击者收到通知 "+this.name+"发起追击");
    }

    /*调用目标类的通知方法*/
    public void findTarget(SpeicificTarget target){
        target.notifyAllChaser();
    }

}
