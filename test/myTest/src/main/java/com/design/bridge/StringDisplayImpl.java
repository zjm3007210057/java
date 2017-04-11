package com.design.bridge;

/**
 * Created by zjm on 2017/4/7.
 */
public class StringDisplayImpl extends AbtractDisplay {

    private String string;

    private int width;

    public StringDisplayImpl(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    public void printLine(){
        System.out.println("+");
        for (int i = 0; i < 5; i++) {
            System.out.println("-");
        }
        System.out.println("+");
    }

    @Override
    public void rawOpen() {
        printLine();
    }

    @Override
    public void rawPrint() {
        System.out.println("|" + string + "|");
    }

    @Override
    public void rawClose() {
        printLine();
    }

}
