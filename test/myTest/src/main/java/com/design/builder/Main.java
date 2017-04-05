package com.design.builder;

/**
 * Created by zjm on 2017/3/27.
 */
public class Main {

    public static void main(String[] args) {
        Builder textBuilder = new TextBuilder();
        Builder htmlBuilder = new HTMLBuilder();
        Director td = new Director(textBuilder);
        Director hd = new Director(htmlBuilder);
        td.construct();
        hd.construct();
        String res = (String) textBuilder.getResult();
        String fileName = (String) htmlBuilder.getResult();
        System.out.println(res);
        System.out.println(fileName);
    }
}
