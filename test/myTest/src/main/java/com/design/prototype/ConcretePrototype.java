package com.design.prototype;

/**
 * Created by zjm on 2017/3/23.
 */
public class ConcretePrototype implements Prototype {

    private String anything;

    public ConcretePrototype(String anything) {
        this.anything = anything;
    }

    @Override
    public void use() {
        System.out.println(anything);
    }

    @Override
    public Prototype createClone() {
        try {
            return (Prototype) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
