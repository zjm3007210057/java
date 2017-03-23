package com.design.factory;

/**
 * Created by zjm on 2017/3/22.
 */
public class Television extends Product {

    private String channel;

    public Television(String channel) {
        this.channel = channel;
    }

    @Override
    public void use() {
        System.out.println("播放" + channel + "台");
    }

    public String getChannel() {
        return channel;
    }
}
