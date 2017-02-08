package rabbitmq.rabbitmq_client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.message.handler.ServiceCallBack;
import com.message.interfaces.ConsumeMessage;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receive implements MessageListener {

	@Autowired
	private static ServiceCallBack serviceCallBack;

	private static String QUEUE_NAME = "hello";

	private static String EXCHANGE_NAME = "test";

	private static String ROUTINGKEY = "test";
	
	public String getMessage()throws IOException, TimeoutException, InterruptedException{
//		CachingConnectionFactory factory = new CachingConnectionFactory();
//		RabbitTemplate amqpTemplate = new RabbitTemplate(factory);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, ROUTINGKEY);
		System.out.println("[*] Wating for message. ");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 指定消费队列
		boolean ack = false ; //打开应答机制
		channel.basicConsume(queueName, ack, consumer);
		while (true)
		{
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();

			delivery.getBody();
			String message = new String(delivery.getBody());

//			serviceCallBack.doAction(message);


			System.out.println(message + " [x] Done");
			//发送应答
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

		}
		/*Consumer consumer = new DefaultConsumer(channel){
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)throws IOException{
				String message = new String(body, "utf-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, consumer);*/
//		return message;
//		Object o = amqpTemplate.receiveAndConvert(QUEUE_NAME);
//		System.out.println(String.valueOf(o));
	}

	public void onMessage(Message message){
		System.out.println(message);
		System.out.println("进入 onMessage 方法");
	}
	public static void main(String[] args){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-main.xml");
//			String msg = new Receive().getMessage();
//			System.out.println(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
