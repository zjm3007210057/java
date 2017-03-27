package com.performance;

/**
 * Created by zhjm on 2017/2/16.
 */
public class ForTest {

    public static void main(String[] args) {
        int loop = 100000;
        int count = 100;
        long num = 0;
        long start = System.currentTimeMillis();
        for(int i=0; i<count; i++){
            for(int j=0; j<loop; j++){
                num++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("num is : " + num);//10

        num = 0;
        start = System.currentTimeMillis();
        for(int i=0; i<loop; i++){
            for(int j=0; j<count; j++){
                num++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);//6，效率比上面的高
        System.out.println("num is : " + num);
        /*
        int loop = 1000000000;
        int count = 1000;
        26954
        num is : 1000000000000
        42446
        num is : 1000000000000
        结论：除非数据量特别大会有效率差异，不然不会有效率差异
        */
    }
}
