package com.design.bridge;

/**
 * Created by zjm on 2017/4/7.
 */
public class CountDisplay extends Display{

    public CountDisplay(AbtractDisplay display) {
        super(display);
    }

    public void multiDisplay(int times){
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
