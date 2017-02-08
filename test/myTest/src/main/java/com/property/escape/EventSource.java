package com.property.escape;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 * Created by zhjm on 2017/1/15.
 */
public class EventSource<T> {

    private final List<T> eventListeners;

    public EventSource(){
        eventListeners = new ArrayList<T>();
    }

    public synchronized void registerListener(T eventListener){
        this.eventListeners.add(eventListener);
        this.notifyAll();
    }

    public synchronized List<T> retrieveListeners() throws InterruptedException{
        List<T> dest = null;
        if(eventListeners.size() <= 0){
            this.wait();
        }
        dest = new ArrayList<T>(eventListeners.size());
        dest.addAll(eventListeners);
        return dest;
    }
}
