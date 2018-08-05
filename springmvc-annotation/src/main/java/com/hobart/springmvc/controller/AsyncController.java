package com.hobart.springmvc.controller;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.hobart.springmvc.service.CreateOrderQueen;

@Controller
public class AsyncController {
	
	@ResponseBody
	@RequestMapping("/createOrder")
	public DeferredResult<Object> createOrder(){
		//3秒没处理 响应异常消息
		DeferredResult<Object> dResult=new DeferredResult<>((long)3000, "create fail...");
		CreateOrderQueen.save(dResult);
		return dResult;
	}
	
	@ResponseBody
	@RequestMapping("/create")
	public String create(){
		//创建订单
		//模拟消息中间件消费订单创建
		DeferredResult<Object> deferredResult = CreateOrderQueen.get();
		String orderId = UUID.randomUUID().toString();
		deferredResult.setResult(orderId);
		return "success===>"+orderId;
	}
	
	/**
	 * 
	 * 1、控制器返回Callable
	 * 2、spring异步处理，将Callable 提交到TaskExecutor 使用一个隔离的线程执行
	 * 3、DispatcherServlet和所有的Filter退出web容器的线程，但是response 保持打开状态
	 * 4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理；
	 * 5、根据Callable返回的结果。SpringMVC继续进行视图
	 * 
	 * 
	 * 
	 *  MyHandlerInterceptor...preHandle.../springmvc-annotation/async01
	 *	主线程开始Thread[http-bio-8080-exec-10,5,main]=>1528446322046
	 *	主线程结束Thread[http-bio-8080-exec-10,5,main]=>1528446322047
	 *	================DispatcherServlet和所有的Filter退出线程==========
	 *
	 *	================等待Callable执行================================
	 *	副线程开始Thread[MvcAsync1,5,main]=>1528446322051
	 *	副线程结束Thread[MvcAsync1,5,main]=>1528446325051
	 *	=================Callable执行完成===============================
	 *
	 *	MyHandlerInterceptor...preHandle.../springmvc-annotation/async01
	 *	MyHandlerInterceptor...postHandle...(Callable的之前的返回值就是目标方法的返回值)
	 *	MyHandlerInterceptor...afterCompletion...
	 */
	@ResponseBody
	@RequestMapping("/async01")
	public Callable<String> async01(){
		System.out.println("主线程开始"+Thread.currentThread()+"=>"+System.currentTimeMillis());
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("副线程开始"+Thread.currentThread()+"=>"+System.currentTimeMillis());
				Thread.sleep(3000);
				System.out.println("副线程结束"+Thread.currentThread()+"=>"+System.currentTimeMillis());
				return "hello async mvc..";
			}
		};
		System.out.println("主线程结束"+Thread.currentThread()+"=>"+System.currentTimeMillis());
		return callable;
	}
}
