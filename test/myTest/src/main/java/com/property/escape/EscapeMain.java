package com.property.escape;


import java.util.*;

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
        int[] data = {1,2,3,4};
        List list = Arrays.asList(data);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
        List list1 = new LinkedList();
        list1.add(3);
        if(list1 instanceof RandomAccess){
            System.out.println(list1.get(0));
        }else {
            System.out.println("list1 is not Ran...");
        }
        List list2 = Collections.synchronizedList(list);//线程安全的List
    }
}
