package com.design.builder.homework;

/**
 * Created by zjm on 2017/3/27.
 */
public class HomeMain {

    public static void main(String[] args) {
        Builder builder = new PlainBuilder();
        Director director = new Director(builder);
        director.construct();
        String s = (String)builder.getResult();
        System.out.println(s);
    }
}
