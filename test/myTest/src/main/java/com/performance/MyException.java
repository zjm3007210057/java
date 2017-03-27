package com.performance;

import java.util.ArrayList;
import java.util.List;

/**
 * Java中的异常一次只能抛出一个，比如，doStuff方法有两个逻辑代码片段，如果在第一个逻辑片段中抛出异常，
 * 则第二个逻辑片段就不执行了，也就无法抛出第二个异常了。那么如何才能一次抛出两个异常呢？
 * 如下自行封装一个异常容器解决该问题
 * Created by zhjm on 2017/2/15.
 */
public class MyException extends Exception {

    private List<Throwable> causes = new ArrayList<Throwable>();

    //构造函数，传递一个异常列表
    public MyException(List<? extends Throwable> _causes){
        causes.addAll(_causes);
    }

    //读取所有的异常
    public List<Throwable> getCauses(){
        return causes;
    }
}
