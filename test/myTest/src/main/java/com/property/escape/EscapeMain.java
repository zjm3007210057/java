package com.property.escape;


import java.util.ArrayList;
import java.util.List;

/**
 * 深入JAVA002_对象的发布和逃逸 --this解惑
 * http://blog.csdn.net/u010001838/article/details/45691913
 * Created by zhjm on 2017/1/15.
 */
public class EscapeMain {

    public static void main(String[] args) {
        EventSource<AEventListener> eventSource = new EventSource<AEventListener>();
        ListenerRunnable runnable = new ListenerRunnable(eventSource);
        new Thread(runnable).start();
        System.out.println();
        ThisEscape escape = new ThisEscape(eventSource);
        System.out.println(escape);
    }
}
