package com.design.factory;

/**
 * Created by zjm on 2017/3/22.
 */
public abstract class Factory {

    public final Product create(String ower){
        Product p = createProduct(ower);
        registerProduce(p);
        return p;
    }

    public abstract Product createProduct(String ower);

    public abstract void registerProduce(Product product);
}
