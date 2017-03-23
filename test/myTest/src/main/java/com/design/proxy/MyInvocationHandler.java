package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zjm on 2017/3/5.
 */
public class MyInvocationHandler implements InvocationHandler {

    private TicketProxy ticketProxy;

    public MyInvocationHandler(TicketProxy ticketProxy){
        this.ticketProxy = ticketProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("欢迎来到火车票代售点。。。");
        method.invoke(ticketProxy, null);
        System.out.println("欢迎下次再来。。。");
        return null;
    }
}
