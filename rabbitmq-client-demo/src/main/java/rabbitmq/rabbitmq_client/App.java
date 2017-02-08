package rabbitmq.rabbitmq_client;

import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties pro = new Properties();
        InputStream in = Object.class.getResourceAsStream("/test.properties");
        try{
            pro.load(in);
            String name = pro.getProperty("name");
            String value = pro.getProperty("age");
            System.out.println(name + ":" + value);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
