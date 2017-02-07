package com.design.decoration;

import com.others.Size;
import org.omg.CORBA.IntHolder;

/**
 * Created by zhjm on 2016/11/17.
 */
public class Main {

    public static void main(String[] args) {
        Animal animal = new ConcretAnimal();
        new PlayGame(new PlayBall(animal)).play();
        Size size = Enum.valueOf(Size.class, "SMALL");
        String s = size.getValue();
        Size[] sizes = Size.values();
        for(Size si : sizes){
            System.out.println(si);
        }
        System.out.println(s);
    }
}
