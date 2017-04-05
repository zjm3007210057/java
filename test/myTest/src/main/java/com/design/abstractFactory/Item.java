package com.design.abstractFactory;

/**
 * Created by zjm on 2017/3/27.
 */
public abstract class Item {

    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHtml();
}
