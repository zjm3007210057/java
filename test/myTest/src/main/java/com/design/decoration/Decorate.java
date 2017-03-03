package com.design.decoration;

/**
 * Created by zhjm on 2016/11/17.
 */
public abstract class Decorate implements Animal{

    private Animal animal;

    public Decorate(Animal animal) {
        this.animal = animal;
    }

    public void play(){
        animal.play();
    }
}
