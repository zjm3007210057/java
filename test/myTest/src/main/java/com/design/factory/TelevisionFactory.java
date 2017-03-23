package com.design.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2017/3/22.
 */
public class TelevisionFactory extends Factory {

    private List<String> channels;

    public TelevisionFactory() {
        channels = new ArrayList<>(3);
    }

    @Override
    public Product createProduct(String ower) {
        return new Television(ower);
    }

    @Override
    public void registerProduce(Product product) {
        channels.add(((Television)product).getChannel());
    }

    public List<String> getChannels() {
        return channels;
    }
}
