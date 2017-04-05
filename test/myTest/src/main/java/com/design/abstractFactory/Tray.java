package com.design.abstractFactory;

import java.util.ArrayList;

/**
 * Created by zjm on 2017/3/27.
 */
public abstract class Tray extends Item{

    protected ArrayList tray = new ArrayList();

    public Tray(String caption) {
        super(caption);
    }

    public void add(Item item){
        tray.add(item);
    }
}
