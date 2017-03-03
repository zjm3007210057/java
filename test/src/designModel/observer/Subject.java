package designModel.observer;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，知道观察它的观察者，并提供注册和删除观察者的接口
 * Created by zhjm on 2016/10/5.
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    protected void notifyObservers(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }
}
