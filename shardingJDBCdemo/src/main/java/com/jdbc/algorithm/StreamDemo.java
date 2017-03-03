package com.jdbc.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zjm on 2017/2/12.
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> output = Arrays.stream(new String[]{"a", "b", "c"}).
        map(String::toUpperCase).
        collect(Collectors.toList());
        output.stream().forEach(s -> System.out.println(s));

        long start = System.currentTimeMillis();
        for(int i=0; i<1000000; i++){
            if("abcdgdfsjsiwiwkwkwkdjafa".startsWith("a")){
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for(int i=0; i<1000000; i++){
            if('a' == "abcdgdfsjsiwiwkwkwkdjafa".charAt(0)){
            }
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
