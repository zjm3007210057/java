package com.netty.chapter7;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhjm on 2016/10/26.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter{

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0; i<10; i++){
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i){
        SubscribeReq req = new SubscribeReq();
        req.setAddress("德国美因茨大学");
        req.setPhoneNumber("15002617562");
        req.setProductName("Netty 权威指南");
        req.setSubReqId(i);
        req.setUserName("heyang");
        return req;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server Response : [" + msg + "]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx)throws Exception{
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
