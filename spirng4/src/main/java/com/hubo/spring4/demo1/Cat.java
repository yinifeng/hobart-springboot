package com.hubo.spring4.demo1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 容器启动时初始化
 * 容器关闭时销毁
 * @author hubo
 *
 */
public class Cat implements InitializingBean,DisposableBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("============Cat afterPropertiesSet===========");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("============Cat destroy===========");
		
	}

}
