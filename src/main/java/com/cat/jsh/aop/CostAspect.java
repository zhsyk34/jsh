package com.cat.jsh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class CostAspect {

	//execution(<方法修饰符>? <方法返回值类型> <包名>.<类名>.<方法名>(<参数类型>) [throws <异常类型>]?)
	//‘*’代表0个或多个任意字符，包名中的..（两个点）代表当前包及其子包，参数列表中的..代表任意个参数。
	@Pointcut("execution(public * com.cat.jsh.aop.TestAop.*(..))")
//	@Pointcut("execution(* com.cat.jsh.dao.base.TemplateSession.namespace(..))")
	public void times() {
	}

	@Around("times()")
	public Object time(ProceedingJoinPoint point) {
		Object result;
		String methodName = point.getSignature().getName();
		long begin = System.nanoTime();
		try {
			//前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(point.getArgs()));
			//执行目标方法
			result = point.proceed();
			//翻译通知
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method " + methodName + " occurs exception " + e);
			throw new RuntimeException(e);
		}
		long end = System.nanoTime();
		System.out.println("namespace is : [" + result + "],spend : " + (end - begin) / 1000 + " us.");
		//后置通知
		System.out.println("The method " + methodName + " ends");
		return result + " aop ";
	}
}
