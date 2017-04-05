package com.design.builder;

/**
 * Created by zjm on 2017/3/27.
 */
public abstract class Builder<T> {

    private boolean flag = false;

    protected abstract void buildTitle(String title);

    protected abstract void buildBody(String body);

    protected abstract void buildItems(String[] items);

    public void makeTitle(String title){
        if(!flag){
            buildTitle(title);
            flag = true;
        }
    }

    public void makeBody(String body){
        if(flag){
            buildBody(body);
        }
    }

    public void makeItems(String[] items){
        if(flag){
            buildItems(items);
        }
    }

    public abstract void close();

    public abstract T getResult();
}
