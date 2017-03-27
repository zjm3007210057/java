package com.string;

/**
 * String的concat方法效率比+的效率高,StringBuilder的效率最高， StringBuffer次之
 * 因为StringBuffer的所有方法几乎都是同步的，而StringBuilder并不是，但是在多线程中不能使用StringBuilder
 * Created by zhjm on 2017/2/13.
 */
public class StringConcat {

    public static void main(String[] args) {
        String str = null;
        String res = "";
        long start = System.currentTimeMillis();
        for(int i=0; i<10000; i++){
            str += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);//结果为364

        start = System.currentTimeMillis();
        for(int i=0; i<10000; i++){
            res = res.concat(String.valueOf(i));
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);//结果为80

        StringBuffer sb = new StringBuffer();
        start = System.currentTimeMillis();
        for(int i=0; i<50000; i++){
            sb.append(i);
        }
        sb.toString();
        end = System.currentTimeMillis();
        System.out.println(end - start);//结果为9

        StringBuilder builder = new StringBuilder();
        start = System.currentTimeMillis();
        for(int i=0; i<50000; i++){
            builder.append(i);
        }
        builder.toString();
        end = System.currentTimeMillis();
        System.out.println(end - start);//结果为5
    }
}
