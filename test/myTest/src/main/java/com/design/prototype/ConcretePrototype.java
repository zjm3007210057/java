package com.design.prototype;

/**
 * Created by zjm on 2017/3/23.
 */
public class ConcretePrototype implements Prototype {

    private String prototypeName;

    @Override
    public void use() {

    }

    @Override
    public Prototype createClone() {
        return null;
    }
}
