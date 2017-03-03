package com.property.escape;

import java.util.concurrent.TimeUnit;

/**
 * 模拟this逃逸的类
 * Created by zhjm on 2017/1/15.
 */
public class ThisEscape {

    public final int id;
    public final String name;

    public ThisEscape(EventSource<AEventListener> eventSource){
        id = 1;
        eventSource.registerListener(
            new AEventListener() {
                public void onEvent(Object object) {
                    System.out.println("id:" + ThisEscape.this.id);
                    System.out.println("name:" + ThisEscape.this.name);
                }
            }
        );
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        name = "thisEscape test.";
    }
}
