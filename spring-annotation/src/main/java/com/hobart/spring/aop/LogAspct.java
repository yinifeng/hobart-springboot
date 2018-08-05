package com.hobart.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义的日志切面
 * @Aspect 注解告诉容器为一个切面
 * @author hobart
 *
 *
 *pointcut 切面表达式 切入点表达式

下面给出一些常见切入点表达式的例子。

任意公共方法的执行：
execution(public * *(..))

任何一个以“set”开始的方法的执行：
execution(* set*(..))

AccountService 接口的任意方法的执行：
execution(* com.xyz.service.AccountService.*(..))

定义在service包里的任意方法的执行：
execution(* com.xyz.service.*.*(..))

定义在service包或者子包里的任意方法的执行：
execution(* com.xyz.service..*.*(..))

在service包里的任意连接点（在Spring AOP中只是方法执行） ：
within(com.xyz.service.*)

在service包或者子包里的任意连接点（在Spring AOP中只是方法执行） ：
within(com.xyz.service..*)

实现了 AccountService 接口的代理对象的任意连接点（在Spring AOP中只是方法执行） ：
this(com.xyz.service.AccountService)
'this'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得代理对象可以在通知体内访问到的部分。

实现了 AccountService 接口的目标对象的任意连接点（在Spring AOP中只是方法执行） ：
target(com.xyz.service.AccountService)
'target'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得目标对象可以在通知体内访问到的部分。

任何一个只接受一个参数，且在运行时传入的参数实现了 Serializable 接口的连接点 （在Spring AOP中只是方法执行）
args(java.io.Serializable)
'args'在binding form中用的更多：- 请常见以下讨论通知的章节中关于如何使得方法参数可以在通知体内访问到的部分。
请注意在例子中给出的切入点不同于 execution(* *(java.io.Serializable))： args只有在动态运行时候传入参数是可序列化的（Serializable）才匹配，而execution 在传入参数的签名声明的类型实现了 Serializable 接口时候匹配。

有一个 @Transactional 注解的目标对象中的任意连接点（在Spring AOP中只是方法执行）
@target(org.springframework.transaction.annotation.Transactional)
'@target' 也可以在binding form中使用：请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。

任何一个目标对象声明的类型有一个 @Transactional 注解的连接点（在Spring AOP中只是方法执行）
@within(org.springframework.transaction.annotation.Transactional)
'@within'也可以在binding form中使用：- 请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。

任何一个执行的方法有一个 @Transactional annotation的连接点（在Spring AOP中只是方法执行）
@annotation(org.springframework.transaction.annotation.Transactional)
'@annotation' 也可以在binding form中使用：- 请常见以下讨论通知的章节中关于如何使得annotation对象可以在通知体内访问到的部分。

任何一个接受一个参数，并且传入的参数在运行时的类型实现了 @Classified annotation的连接点（在Spring AOP中只是方法执行）
@args(com.xyz.security.Classified)
'@args'
 *
 *
 *
 *
 */
@Aspect
public class LogAspct {
	
	//抽取公共的切入表达式
	//要切入的位置 利用切面表达式
	@Pointcut("execution(public int com.hobart.spring.aop.MathCalculator.*(..))")
	public void pointCut(){}
	
	@Before("pointCut()")
	public void logBefore(JoinPoint joinpoint) {
		System.out.println(joinpoint.getSignature().getName()
				+"=>前置通知...@Before..."+"参数为:"
				+Arrays.asList(joinpoint.getArgs()));
	}
	
	//外部类使用的这个切面表达式
	@After("com.hobart.spring.aop.LogAspct.pointCut()")
	public void logAfter(JoinPoint joinpoint) {
		//类似于finally
		System.out.println(joinpoint.getSignature().getName()
				+"=>后置通知...@After...");
	}
	
	//JoinPoint一定要出现参数表的第一位
	@AfterReturning(value="pointCut()",returning="result")
	public void logAfterReturning(JoinPoint joinpoint,Object result) {
		System.out.println(joinpoint.getSignature().getName()
				+"=>返回通知...@AfterReturning..."+"结果为:"
				+result);
	}
	
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void logAfterThrowing(JoinPoint joinpoint,Throwable exception) {
		System.out.println(joinpoint.getSignature().getName()
				+"=>异常通知...@AfterThrowing..."+"异常为:"
				+exception);
	}
	
	//@Around("pointCut()")
	public void logAround(JoinPoint joinpoint) {
		System.out.println(joinpoint.getSignature().getName()
				+"=>环绕通知...@Around...");
	}
}
