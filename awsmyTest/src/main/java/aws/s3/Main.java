package aws.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zjm on 2016/12/7.
 */
@SpringBootApplication
@ComponentScan(basePackages = "aws.s3")
@ImportResource("spring-message-listen.xml")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
