package com.design.builder.homework;

/**
 * Created by zjm on 2017/3/27.
 */
public interface Builder<T> {

    void makeTitle(String title);

    void makeBody(String body);

    void makeItems(String[] items);

    void close();

    T getResult();
}
