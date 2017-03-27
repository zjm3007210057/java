package com.performance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhjm on 2017/2/15.
 */
public class MyExceptionTest {

    public static void doStuff()throws MyException{
        List<Throwable> list = new ArrayList<Throwable>();
        //第一个逻辑片段
        try{
            //todo
            throw new Exception("exception出现");
        }catch(Exception e){
            list.add(e);
        }

        //第二个逻辑片段
        try{
            //todo
        }catch(Exception e){
            list.add(e);
        }

        if(list.size() > 0){
            throw new MyException(list);
        }
    }

    public static void main(String[] args) {
        try{
            doStuff();
        }catch(MyException e){
            e.printStackTrace();
        }
    }
}
