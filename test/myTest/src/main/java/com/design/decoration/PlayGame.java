package com.design.decoration;

/**
 * Created by zhjm on 2016/11/17.
 */
public class PlayGame extends Decorate {

    private Animal animal;

    public PlayGame(Animal animal) {
        super(animal);
    }

    public void play(){
        System.out.println("I am play games");
        super.play();
    }
}
