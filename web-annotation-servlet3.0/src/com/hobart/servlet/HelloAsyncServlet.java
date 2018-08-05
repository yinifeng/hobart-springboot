package com.hobart.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/async",asyncSupported=true)
public class HelloAsyncServlet extends HttpServlet{

	private static final long serialVersionUID = -4684853848797836128L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1、支持异步处理 asyncSupported=true
		//2、开启异步
		System.out.println("主线程："+Thread.currentThread()+" start...."+System.currentTimeMillis());
		AsyncContext startAsync = req.startAsync();
		//3、业务逻辑进行异步处理
		startAsync.start(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("副线程："+Thread.currentThread()+" start...."+System.currentTimeMillis());
					sayHello();
					startAsync.complete();
					//获取异步上下文
					//AsyncContext asyncContext = req.getAsyncContext();
					//获取响应
					ServletResponse response = startAsync.getResponse();
					response.getWriter().write("hello...Async...");
					System.out.println("副线程："+Thread.currentThread()+" end...."+System.currentTimeMillis());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.out.println("主线程："+Thread.currentThread()+" end...."+System.currentTimeMillis());
	}
	
	private void sayHello(){
		try {
			System.out.println(Thread.currentThread()+" processing....");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
