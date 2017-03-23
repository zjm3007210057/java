package com.design.factory;

/**
 * Created by zjm on 2017/3/23.
 */
public class Computer extends Product {

    private String name;

    public Computer(String name) {
        this.name = name;
    }

    @Override
    public void use() {
        System.out.println(name + "使用电脑编写代码");
    }

    public String getName() {
        return name;
    }
}
