package com.design.abstractFactory;

/**
 * Created by zjm on 2017/3/27.
 */
public abstract class Link extends Item{

    protected String url;

    public Link(String caption, String url){
        super(caption);
        this.url = url;
    }
}
