package com.jdbc.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zjm on 2017/2/12.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.forEach(name -> System.out.println(name));
        StringBuffer s = new StringBuffer();
        s.append("dsds ds   df");
        s.append("\n");
        s.append("fdfs fd   sdew");
        System.out.println(s.toString());
        List<String> words = Arrays.asList(s.toString().split(" "));
        System.out.println(words.size());
        words.stream().forEach(w -> System.out.println(w));
        long count = words.stream().filter(w -> w.length() > 2).count();
        System.out.println(count);
    }
}
