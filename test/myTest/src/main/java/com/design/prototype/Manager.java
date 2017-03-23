package com.design.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjm on 2017/3/23.
 */
public class Manager {

    private Map map = new HashMap();

    public void register(String prototypeName, Prototype prototype){
        map.put(prototypeName, prototype);
    }

    public Prototype create(String prototypeName){
        Prototype p =  (Prototype) map.get(prototypeName);
        return p.createClone();
    }

}
