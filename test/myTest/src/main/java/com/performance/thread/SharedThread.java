package com.performance.thread;

/**
 * Created by zhjm on 2017/3/21.
 */
public class SharedThread extends Thread {

    private int count = 5;

    public void run(){
        super.run();
        count--;
        System.out.println(Thread.currentThread().getName() + ", count=" +count);
    }

    public static void main(String[] args) {
        SharedThread thread = new SharedThread();
        Thread aa = new Thread(thread, "aa");
        Thread bb = new Thread(thread, "bb");
        Thread cc = new Thread(thread, "cc");
        aa.start();
        bb.start();
        cc.start();
        for(int i=0; i<10; i++){
            System.out.format("percent complete: %d%%", i);
        }
    }
}
