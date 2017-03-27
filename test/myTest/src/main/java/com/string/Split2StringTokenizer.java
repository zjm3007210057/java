package com.string;

import java.util.StringTokenizer;

/**
 * Created by zhjm on 2017/2/13.
 */
public class Split2StringTokenizer {

    public static void main(String[] args) {
        String orgStr = null;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<5; i++){
            sb.append(i);
            sb.append(",");
        }
        orgStr = sb.toString();
        long start = System.currentTimeMillis();
        for(int i=0; i<5; i++){
            orgStr.split(",");
        }
        StringTokenizer st = new StringTokenizer(orgStr, ",");
    }
}
