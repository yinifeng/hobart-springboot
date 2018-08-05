package com.hobart.springmvc.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.web.context.request.async.DeferredResult;

public class CreateOrderQueen {
	private static final Queue<DeferredResult<Object>> queen=new ConcurrentLinkedQueue<>();
	
	public static void save(DeferredResult<Object> dResult){
		queen.add(dResult);
	}
	
	public static DeferredResult<Object> get(){
		return queen.poll();
	}
}
