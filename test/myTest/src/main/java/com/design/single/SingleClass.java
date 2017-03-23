package com.design.single;

/**
 * Created by zjm on 2016/11/30.
 */
public class SingleClass {

    private static SingleClass singleClass;

    private SingleClass(){}

    /**
     * 加上synchronized是为了防止多线程同时访问造成产生多个实例
     * 但是使用同步会造成性能下降，频繁的调用甚至下降100倍
     * @return
     */
    public synchronized static SingleClass getInstance(){
        if(singleClass == null){
            singleClass = new SingleClass();
        }
        return singleClass;
    }

    /**
     * 如果你比较急切，也可以使用下面的方法
     * private static SingleClass singleClass = new SingleClass();
     */
}
