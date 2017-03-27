package com.string;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhjm on 2017/2/13.
 */
public class EncodingTest {

    public static void main(String[] args) throws UnsupportedEncodingException{
        System.out.println("user.country: " + System.getProperty("user.country"));
        System.out.println("user.language: " + System.getProperty("user.language"));
        System.out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
        System.out.println("file.encoding: " + System.getProperty("file.encoding"));
    }
}
