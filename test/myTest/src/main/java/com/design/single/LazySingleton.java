package com.design.single;

/**
 * 引入了同步锁，性能会有所下降
 * Created by zhjm on 2017/2/27.
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            return new LazySingleton();
        }
        return instance;
    }
}
