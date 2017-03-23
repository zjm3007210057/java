package com.design.proxy;

/**
 * Created by zjm on 2017/3/5.
 */
public class TicketProxy implements TicketService {

    @Override
    public void sellTicket() {
        System.out.println("代售点卖票。。。");
    }

    @Override
    public void backTicket() {
        System.out.println("请到火车站退票，谢谢 ！");
        throw new RuntimeException("代售点不接受退票。。。");
    }

    @Override
    public void changeTicket() {
        System.out.println("代售点改签票。。。");
    }
}
