package com.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zjm on 11/05/2017.
 */
public class DistributeCandies {

    public int distributeCandies(int[] candies){
        Set set = new HashSet();
        int len = candies.length;
        for(int i=0; i<len; i++){
            set.add(candies[i]);
        }
        return set.size() > len / 2 ? len / 2 : set.size();
    }

}
