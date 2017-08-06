package com.me.collection;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zjm on 2017/4/5.
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection){
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection collection){
        return CollectionUtils.isNotEmpty(collection);
    }

    public static boolean isEmpty(Map map){
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map){
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object[] objects){
        if(objects == null || objects.length == 0){
            return true;
        }else{
            for (int i = 0; i < objects.length; i++) {
                if(objects[i] != null){
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }
}
