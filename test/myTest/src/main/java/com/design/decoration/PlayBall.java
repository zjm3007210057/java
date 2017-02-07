package com.design.decoration;

/**
 * Created by zhjm on 2016/11/17.
 */
public class PlayBall extends Decorate {

    private Animal animal;

    public PlayBall(Animal animal){
        super(animal);
    }

    public void play(){
        System.out.println("I am play ball now");
        super.play();
    }
}
