package rabbitmq.rabbitmq_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.message.impl.PublishMessageImpl;
import com.message.interfaces.PublishMessage;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {

	private final static String QUEUE_NAME = "hello";

	private final static String EXCHANGE_NAME = "test";

	private final static String ROUTINGKEY = "test";

	@Autowired
	private static PublishMessage publishMessage;

	public static void main1(String[] args)throws IOException, TimeoutException{
		CachingConnectionFactory factory = new CachingConnectionFactory();
		RMMessage rmMessage = new RMMessage();
		RMMessage rmMessage1 = new RMMessage();
//		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
//		Connection connection = factory.newConnection();
//		Channel channel = connection.createChannel();
		RabbitTemplate amqpTemplate = new RabbitTemplate(factory);
//		DeclareOk q = channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		InputStreamReader reader = new InputStreamReader(System.in);
		while(true){
			String message = new BufferedReader(reader).readLine();
			rmMessage.setBody(message);
			rmMessage1.setBody(message + "@@@");
			String str = JSON.toJSONString(rmMessage);
			String str1 = JSON.toJSONString(rmMessage1);
			amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTINGKEY, str);
			amqpTemplate.convertAndSend(EXCHANGE_NAME, "test1", str1);
//			channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, str.getBytes());
			System.out.println(" [x] sent '" + message + "'");
		}
	}

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-main.xml");
//		(PublishMessage)context.getBean("publishMessage").sendMessage("hello world");
		PublishMessage publishMessage = (PublishMessage) context.getBean("publishMessage");
		publishMessage.sendMessage("test", "test1", "你哈喽");
		publishMessage.sendMessage("hello world");
	}

}
