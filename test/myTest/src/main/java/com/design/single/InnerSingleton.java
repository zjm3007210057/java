package com.design.single;

/**
 * 由于实例的创建是在类加载时完成，因此天生对多线程友好
 * Created by zhjm on 2017/2/27.
 */
public class InnerSingleton {

    private InnerSingleton(){}

    private static class SingletonHandler{
        private static InnerSingleton instance = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return SingletonHandler.instance;
    }
}
