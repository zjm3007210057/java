package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by zjm on 2017/3/5.
 */
public class Main {

    public static void main(String[] args) {
        TicketProxy proxy = new TicketProxy();
        ClassLoader loader = proxy.getClass().getClassLoader();
        Class[] classes = proxy.getClass().getInterfaces();

        InvocationHandler handler = new MyInvocationHandler(proxy);

        Object o = Proxy.newProxyInstance(loader, classes, handler);
        TicketService service = (TicketService)o;
        service.sellTicket();
        service.changeTicket();
        service.backTicket();
    }
}
