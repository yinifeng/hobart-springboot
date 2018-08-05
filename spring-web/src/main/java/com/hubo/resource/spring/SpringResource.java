package com.hubo.resource.spring;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * spring 相关加载资源文件
 * @author hobart
 * 
 * ByteArrayResource：二进制数组表示的资源，二进制数组资源可以在内存中通过程序构造。
 * ClassPathResource：类路径下的资源，资源以相对于类路径的方式表示。
 * FileSystemResource：文件系统资源，资源以文件系统路径的方式表示，如 /Users/benjamin/Desktop/a.txt。
 * InputStreamResource：对应一个InputStream的资源。
 * ServletContextResource：为访问Web容器上下文中的资源而设计的类，负责以相对于Web应用根目录的路径加载资源，它支持以流和URL的方式访问，在WAR解包的情况下，也可以通过File的方式访问，该类还可以直接从JAR包中访问资源。
 * UrlResource：封装了java.net.URL，它使用户能够访问任何可以通过URL表示的资源，如文件系统的资源、HTTP资源、FTP资源等。
 *
 */
public class SpringResource {
	public static void main(String[] args) throws Exception {
		//1.文件系统资源
		Resource r1 = new FileSystemResource("D:\\logs\\cust-base.txt");
		System.out.println(r1.contentLength()+"<--->"+r1.getDescription());
		
		//2.类路径下的资源
		Resource r2 = new ClassPathResource("conf/a.txt");
		System.out.println(r2.contentLength()+"<--->"+r2.getDescription());
		
		//3.web应用资源
		//Resource r3 = new ServletContextResource(null, "/WEB-INF/classes/conf/a.txt");
		
		
		
	}
}
