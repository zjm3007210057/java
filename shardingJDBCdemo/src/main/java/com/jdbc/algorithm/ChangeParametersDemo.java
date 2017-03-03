package com.jdbc.algorithm;

import java.io.PrintStream;

/**
 * Created by zjm on 2017/2/10.
 */
public class ChangeParametersDemo {

    public static void changeParameters(int a, int b){
        PrintStream stream = new PrintStream(System.out){
            public void println(String s){
                if("a = 10".equals(s)){
                    s = "a = 100";
                }else{
                    s = "b = 200";
                }
                super.println(s);
            }
        };
        System.setOut(stream);
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        changeParameters(a, b);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
