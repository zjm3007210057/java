package com.design.bridge;

/**
 * Created by zjm on 2017/4/7.
 */
public class Display {

    private AbtractDisplay abtractDisplay;

    public Display(AbtractDisplay display) {
        this.abtractDisplay = display;
    }

    public void open(){
        abtractDisplay.rawOpen();
    }

    public void print(){
        abtractDisplay.rawPrint();
    }

    public void close(){
        abtractDisplay.rawClose();
    }

    public void display(){
        open();
        print();
        close();
    }
}
