package com.design.bridge;

/**
 * Created by zjm on 2017/4/7.
 */
public class DisplayMain {

    public static void main(String[] args) {
        Display display = new Display(new StringDisplayImpl("Hello, China."));
        Display dp = new CountDisplay(new StringDisplayImpl("Hello, world."));
        CountDisplay cd = new CountDisplay(new StringDisplayImpl("Hello, Universe."));
        display.display();
        dp.display();
        cd.display();
        cd.multiDisplay(5);
    }
}
