package com.design.builder;

/**
 * Created by zjm on 2017/3/27.
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct(){
        builder.makeTitle("say-hello");
        builder.makeBody("one day");
        builder.makeItems(new String[]{"good morning", "good afternoon", "good night"});
        builder.close();
    }
}
