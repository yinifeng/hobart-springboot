package com.hobart.amqp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.hobart.amqp.model.Book;

@Service
public class BookService {
	
	@RabbitListener(queues="hubo.news")
	public void receiveMsg(Book book){
		System.out.println("收到消息："+book);
	}
	
	@RabbitListener(queues="hobart.news")
	public void receiveHeader(Message message){
		System.out.println(message.getBody());
		System.out.println(message.getMessageProperties());
	}
}
