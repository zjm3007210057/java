package com.examples_1200.chapter4_string;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by zhjm on 2016/12/21.
 */
public class FormatNumber {

    public static void main(String[] args) {
        String s = "";
        Pattern pattern = Pattern.compile("\\d+\\.?\\d?");
        while(!pattern.matcher(s).matches()){
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入一个数字");
            s = scan.nextLine();
        }
        double number = Double.parseDouble(s);
        System.out.println("该数字用Locale类的以下常量作为构造化对象的构造参数，将获得不同的货币格式： ");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("Locale.CHINA: " + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Locale.US: " + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        System.out.println("Locale.ENGLISH: " + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
        System.out.println("Locale.TAIWAN: " + format.format(number));
    }
}
