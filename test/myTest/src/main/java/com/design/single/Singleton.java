package com.design.single;

/**
 * 简单的列子，此例子的缺点是无法对实例进行延迟加载，只要其他类中用到Singleton，
 * jvm就会创建该实例
 * Created by zhjm on 2017/2/27.
 */
public class Singleton {

    private static Singleton instance = new Singleton();
    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}
