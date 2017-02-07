package com.property.escape;

import java.util.List;

/**
 * 监听器线程
 * Created by zhjm on 2017/1/15.
 */
public class ListenerRunnable implements Runnable{

    private EventSource<AEventListener> source;

    public ListenerRunnable(EventSource<AEventListener> source){
        this.source = source;
    }

    public void run(){
        List<AEventListener> listeners = null;
        try{
            listeners = source.retrieveListeners();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        for(AEventListener eventListener : listeners){
            eventListener.onEvent(new Object());
        }
    }
}
