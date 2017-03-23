package com.design.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 2017/3/22.
 */
public class IdCardFactory extends Factory {

    private List<String> owers;

    private String idNum = "100";

    public IdCardFactory() {
        owers = new ArrayList<String>();
    }

    @Override
    public Product createProduct(String ower) {
        return new IdCard(ower, idNum);
    }

    @Override
    public void registerProduce(Product product) {
        owers.add(((IdCard)product).getOwer());
    }

    public List<String> getOwers() {
        return owers;
    }
}
