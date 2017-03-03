package com.property;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by zhjm on 2017/1/15.
 */
public class CacheTest {

    public static void main(String[] args) throws Exception{
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("1.txt")));
        out.writeChars("test");
        //因为test占用空间很小，所以暂时放入缓冲区，后来输入流想要从文件中读取内容，由于文件
        //还没有写入test，所以读不到test，必须调用out.close()或者out.flush()方法将
        //缓冲区的数据手动写入文件
        out.close();
        FileInputStream in = new FileInputStream("1.txt");
        int len = in.available();
        System.out.println(len);
        byte[] bytes = new byte[len];
        int actlen = in.read(bytes);
        String str = new String(bytes);
        System.out.println(str);
    }
}
