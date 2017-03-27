package com.performance.thread;

/**
 * Created by zhjm on 2017/3/21.
 */
public class UnSharedThread extends Thread {

    private int count = 5;

    public UnSharedThread(String name){
        Thread.currentThread().setName(name);
    }

    public void run(){
        super.run();
        while(count > 0){
            count--;
            System.out.println(this.getName() + ", count=" +count);
        }
    }

    public static void main(String[] args) {
        UnSharedThread[] threads = new UnSharedThread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new UnSharedThread(String.valueOf(i));
        }
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }
    }
}
