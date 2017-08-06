package aws.kinesis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhang.jianming on 2017/1/5.
 */
public class Log4jTest {

    private static Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    public void writeLogger(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        String dateString;
        for(int i=0; i<100000; i++){
            date = new Date();
            dateString = format.format(date);
            logger.info(dateString + "this is logger test " + i);
        }
    }

    public void readResourceFile(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties");
        InputStreamReader reader = new InputStreamReader(is);
        /*char[] chars = new char[1024];
        int num;
        try {
            while((num = reader.read(chars)) != -1){
                System.out.println(new String(chars, 0, num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //或者通过BufferedReader读取
        BufferedReader br = new BufferedReader(reader);
        String line;
        try {
            while((line = br.readLine()) != null){
                //每次读取一行
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new Log4jTest().writeLogger();
        new Log4jTest().readResourceFile();
    }
}
