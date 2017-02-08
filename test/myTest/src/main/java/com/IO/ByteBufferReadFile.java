package com.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhjm on 2016/12/31.
 */
public class ByteBufferReadFile {

    public ByteBuffer readFile(String fileName){
        File file = new File(fileName);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer resBuffer = ByteBuffer.allocate((int)file.length());
        FileInputStream in;
        FileChannel channel;
        byte[] bytes = new byte[1024];
        int num;
        try{
            in = new FileInputStream(file);
            channel = in.getChannel();
            while((num = channel.read(buffer)) != -1){
                buffer.flip();
                resBuffer.put(buffer);
                System.out.println(new String(buffer.array(), "utf-8"));
                buffer.clear();
            }
            in.close();
            channel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return resBuffer;
    }

    public void writeFromBuffer(String srcFileName, String desFileName){
        ByteBuffer buffer = this.readFile(srcFileName);
        File file = new File(desFileName);
        FileOutputStream os;
        FileChannel channel;
        try{
            os = new FileOutputStream(file);
            channel = os.getChannel();
            buffer.flip();
            channel.write(buffer);
            os.close();
            channel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ByteBufferReadFile demo = new ByteBufferReadFile();
        demo.readFile("D:\\虚拟机创建的50个步骤和100个知识点.txt");
//        demo.writeFromBuffer("D:\\虚拟机创建的50个步骤和100个知识点.txt", "D:\\java-IO.txt");
    }
}
