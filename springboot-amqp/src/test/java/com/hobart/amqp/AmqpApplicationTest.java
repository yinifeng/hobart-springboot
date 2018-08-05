package com.hobart.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hobart.amqp.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpApplicationTest {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	/**
	 * 创建Exchange Queue Binding
	 */
	@Test
	public void create(){
		//创建Exchange
		//amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
		//创建队列
		//amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
		//创建绑定
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", DestinationType.QUEUE, "amqpAdmin.exchange", "amqp.haha", null));
		
		//amqpAdmin.deleteQueue(queueName);
	}
	
	/**
	 * 1、单播(点对点)
	 */
	@Test
	public void sendMsg() {
		//Message需要自己构造一个；定义消息体内容和消息头
		//rabbitTemplate.send(exchange, routingKey, message);
		
		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		//rabbitTemplate.convertAndSend(exchange, routingKey, object);
		Map<String,Object> map=new HashMap<>();
		map.put("msg", "这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		
		//对象被默认序列化以后发送出去 application/x-java-serialized-object
		//可以配置指定序列化格式
		rabbitTemplate.convertAndSend("hobart.direct", "hobart.news", new Book("西游记","吴承恩"));
	}
	
	/**
	 * 接收消息
	 */
	@Test
	public void receiveMsg(){
		
		//SimpleMessageConverter默认是这个做的消息转换
		//可以自己配置指定
		Object obj = rabbitTemplate.receiveAndConvert("hobart.news");
		System.out.println(obj.getClass());
		System.out.println(obj.toString());
	}
	
	/**
	 * 广播模式
	 */
	@Test
	public void sendAllMsg(){
		rabbitTemplate.convertAndSend("hobart.fanout", "", new Book("红楼梦", "曹雪芹"));
	}
}
