package com.design.prototype;

/**
 * Created by zjm on 2017/3/23.
 */
public interface Prototype extends Cloneable{

    void use();

    Prototype createClone();
}
