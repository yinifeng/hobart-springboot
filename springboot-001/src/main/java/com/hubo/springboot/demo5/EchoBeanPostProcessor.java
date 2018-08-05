package com.hubo.springboot.demo5;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class EchoBeanPostProcessor implements BeanPostProcessor{
	private List<String> packages;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		for(String pack:packages){
			if(bean.getClass().getName().startsWith(pack)){
				System.out.println("echo bean: "+bean.getClass().getName());
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	public List<String> getPackages() {
		return packages;
	}

	public void setPackages(List<String> packages) {
		this.packages = packages;
	}
	
	
}
