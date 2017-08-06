package com.main;


import com.amazonaws.services.dynamodbv2.xspec.S;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjm on 28/04/2017.
 */
public class MatcherMain {

    //我要截取成为123 Media   147712345 456.com  1234511122  right-atf-300x250  1234564456  怎么搞，变成六列

    public static void main(String[] args) {
        String str = "123 Media (147712345) >> 456.com (1234511122) >> right-atf-300x250 (1234564456)";
        str = str.replaceAll("\\(", "");
        str = str.replaceAll("\\)", "");
        str = str.replaceAll(">>", "");
        File file = new File("/Users/zjm/Downloads/test");
        if(file.exists() && file.isDirectory()){
            System.out.println("@@@");
        }
        System.out.println(str);
        Integer integer = 1;
        Integer f = new Integer(1);
        System.out.println(integer == f);
        System.out.println(integer.compareTo(f));
        int m = 2;
        int n = new Integer(2);
        System.out.println(m == n);
        List<String> list = new ArrayList(5);
        System.out.println(list.size());
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.size());
        System.out.println(list.get(4));
    }
}
