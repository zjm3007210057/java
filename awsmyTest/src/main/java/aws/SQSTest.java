package aws;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

import javax.jms.*;

/**
 * Created by zjm on 2016/11/8.
 */
public class SQSTest {

    public static void main(String[] args) {
//        System.out.println(System.getenv("AWS_ACCESS_KEY_ID"));
        SQSConnectionFactory connectionFactory = SQSConnectionFactory.builder()
                .withRegion(Region.getRegion(Regions.CN_NORTH_1))
                .withAWSCredentialsProvider(new EnvironmentVariableCredentialsProvider())
                .build();
        try {
            SQSConnection connection = connectionFactory.createConnection();
            AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();
            if(!client.queueExists("user-update")){
                client.createQueue("user-update");
            }
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Queue queue = session.createQueue("user-update");
            //创建一个生产者发送消息
            MessageProducer producer = session.createProducer(queue);
//            创建文本消息并将它发送到队列
            TextMessage message = session.createTextMessage("Hello World");

            MessageConsumer consumer = session.createConsumer(queue);
            connection.start();
            Message receivedMessage = consumer.receive(1000);
            if (receivedMessage != null) {
                System.out.println("Received: " + ((TextMessage)
                receivedMessage).getText());
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Listing all queues in my count.\n");
    }
}
