package com.design.single;

/**
 * Created by zjm on 2016/11/30.
 */
public class SingleClassBetter {

    /**
     * 利用双重加锁，首先检查实例是否已经创建，如果没有，才进行同步，
     * 这样就只需要同步一次，提升性能
     */
    private volatile static SingleClassBetter singleClass;

    public static SingleClassBetter getInstance(){
        if(singleClass == null){
            synchronized (SingleClassBetter.class){
                if(singleClass == null){
                    singleClass = new SingleClassBetter();
                }
            }
        }
        return singleClass;
    }
}
