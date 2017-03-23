package com.design.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2017/3/23.
 */
public class ComputerFactory extends Factory {

    private List<String> computers;

    public ComputerFactory() {
        computers = new ArrayList<>(5);
    }

    @Override
    public Product createProduct(String computer) {
        return new Computer(computer);
    }

    @Override
    public void registerProduce(Product product) {
        computers.add(((Computer)product).getName());
    }

    public List<String> getComputers() {
        return computers;
    }
}
