package designModel.observer;

/**
 * Created by zhjm on 2016/10/5.
 */
public class ConcreteObserver implements Observer{

    private String observerState;

    @Override
    public void update(Subject subject) {
        observerState = ((ConcreteSubject)subject).getSubjectState();
    }
}
