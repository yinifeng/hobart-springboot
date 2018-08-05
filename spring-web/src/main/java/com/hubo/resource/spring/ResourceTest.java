package com.hubo.resource.spring;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 
 * @author hobart
 * Spring提供了一个强大的加载资源的机制，
 * 不但能通过 "classpath:"、"file:" 等资源地址前缀识别不同的资源类型，
 * 还支持Ant风格贷通配符的资源地址
 *
 * ResourceLoader 接口仅有一个 getResource() 方法，可以根据一个资源地址加载文件资源，
 * 不过，资源地址仅支持带资源类型前缀的表达式，不支持Ant风格的资源路径表达式。 
 * ResourcePatternResolver 扩展自 ResourceLoader 接口，
 * 定义了一个新的接口方法：getResources() ，
 * 该方法支持带资源类型前缀及Ant风格的资源路径表达式
 *
 *
 *
 *其中 classpath: 和 classpath*: 的区别为：
 * classpath: 只会在第一个包下查找，而 classpath*: 会扫描所有这些JAR包及类路径下出现的包。
 * Ant风格资源地址支持3种匹配符：
 * ? ：匹配文件名中的一个字符。
 * * ：匹配文件名中的任意个字符。
 * ** ：匹配多层路径
 */
public class ResourceTest {
	public static void main(String[] args) throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		//获取单个文件
		Resource resource = resolver.getResource("classpath:spring.xml");
		System.out.println(resource.contentLength()+"<-->"+resource.getDescription());
		
		//获取多个文件
		Resource[] resources = resolver.getResources("classpath*:spring*.xml");
		for(Resource r:resources){
			System.out.println(r.contentLength()+"<-->"+r.getDescription());
		}
		
	}
}
