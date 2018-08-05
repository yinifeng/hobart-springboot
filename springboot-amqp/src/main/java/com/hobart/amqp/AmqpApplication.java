package com.hobart.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 	1、RabbitAutoConfiguration
 * 	2、有自动配置了连接工厂ConnectionFactory
 * 	3、RabbitProperties封装了 RabbitMQ的配置
 * 	4、RabbitTemplate 给RabbitMQ发送和接收消息
 * 	5、AmqpAdmin RabbitMQ系统管理功能组件
 * 		创建Exchange Queue Binding
 * 	6、消息监听 @EnableRabbit + @RabbitListener
 * @author hobart
 *
 */
@EnableRabbit
@SpringBootApplication
public class AmqpApplication {
	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
	}
}
