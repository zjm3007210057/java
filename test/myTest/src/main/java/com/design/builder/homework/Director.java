package com.design.builder.homework;

/**
 * Created by zjm on 2017/3/27.
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct(){
        builder.makeTitle("打招呼");
        builder.makeBody("不同的时间打招呼");
        builder.makeItems(new String[]{"早上好", "上午好", "晚上好"});
        builder.close();
    }
}
