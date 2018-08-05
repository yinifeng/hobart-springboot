package com.hubo.resource.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 服务发现的思想
 * 当服务的提供者提供了服务接口的一种实现之后，
 * 必须根据SPI约定在 META-INF/services/ 目录里创建一个以服务接口命名的文件
 * @author hobart
 *
 */
public class SPITest {
	public static void main(String[] args) {
		ServiceLoader<Animal> services = ServiceLoader.load(Animal.class);
		Iterator<Animal> it = services.iterator();
		while (it.hasNext()) {
			Animal animal = it.next();
			animal.eat();
		}
		
		System.out.println(System.getProperty("java.class.path"));
	}
}
