package com.hubo.springboot.demo5;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;


/**
 * @EnableAsync 启用异步
 * @author hubo
 *
 */
@Component
public class Jeep implements Runnable {
	
	
	/**
	 * 异步执行的方法
	 */
	@Async
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("=============>" + i);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
