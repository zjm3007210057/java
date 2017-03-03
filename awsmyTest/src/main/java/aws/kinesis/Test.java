package aws.kinesis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhang.jianming on 2016/12/30.
 */
public class Test {

    public static void putStream(String fileName){
        File file = new File(fileName);
        FileInputStream inputStream = null;
        FileChannel fin = null;
        try {
            inputStream = new FileInputStream(file);
            fin = inputStream.getChannel();
            byte[] bytes;
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int num;
            while((num = fin.read(buffer)) != -1){
                buffer.flip();
                System.out.println(buffer.asReadOnlyBuffer().toString());
                bytes = buffer.array();
                System.out.println(new String(bytes));
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fin != null){
                try{
                    fin.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void byteBufferTest(){
        for(int i=0; i<10; i++){
            ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
            System.out.println(new String(buffer.array()));
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(System.currentTimeMillis());
        byteBufferTest();
//        putStream("D:\\谷歌书签.html");
    }
}
