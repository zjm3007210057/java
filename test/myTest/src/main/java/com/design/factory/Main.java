package com.design.factory;

/**
 * Created by zjm on 2017/3/22.
 */
public class Main {

    public static void main(String[] args) {
        Factory factory = new ComputerFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
    }
}
