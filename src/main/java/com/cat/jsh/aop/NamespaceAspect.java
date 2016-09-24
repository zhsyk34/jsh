package com.cat.jsh.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/*@Component
@Aspect*/
public class NamespaceAspect {

	//execution(<方法修饰符>? <方法返回值类型> <包名>.<类名>.<方法名>(<参数类型>) [throws <异常类型>]?)
	//‘*’代表0个或多个任意字符，包名中的..（两个点）代表当前包及其子包，参数列表中的..代表任意个参数。
	/*@Pointcut("execution(public * com.cat.jsh.aop.TestAop.*(..))")
	public void autoSpace() {
	}*/

	//	@Around("autoSpace()")
	public Object namespace(ProceedingJoinPoint point) {
		Object result;
		System.out.println("target 类名" + point.getThis());
		System.out.println("类名" + point.getTarget());
		System.out.println(point.getKind());
		String methodName = point.getSignature().getName();
//		System.out.println(methodName);
		Object[] args = point.getArgs();
		args[0] = "play";
//		System.out.println(Arrays.asList(args));

		try {
			//前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(point.getArgs()));
			//执行目标方法
			result = point.proceed(args);//修改方法参数
			//翻译通知
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method " + methodName + " occurs exception " + e);
			throw new RuntimeException(e);
		}
		//后置通知
//		System.out.println("The method " + methodName + " ends");
		return result;
	}

	//	@Around("autoSpace()")
	public void simple() {
		System.out.println("before");
	}

	/**
	 * Object[] getArgs：返回目标方法的参数
	 * Signature getSignature：返回目标方法的签名
	 * Object getTarget：返回被织入增强处理的目标对象
	 * Object getThis：返回AOP框架为目标对象生成的代理对象
	 */
	public Object better(ProceedingJoinPoint point) {
		System.out.println("begin");
		try {
			/*System.out.println(point.getThis().getClass().getName());
			System.out.println(point.getTarget().getClass().getName());
			Signature signature = point.getSignature();
			System.out.println(signature.getName());
			System.out.println(signature.getDeclaringType());*/
			Object[] args = point.getArgs();

			if (args.length > 0) {
				args[0] = point.getTarget().getClass().getName();
				args[1] = point.getSignature().getName();
			}
			args = new Object[0];
//			System.out.println(args.length);
			Object value = point.proceed(args);//修改方法参数
			System.out.println("end");
			return value + "结果被篡改";
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
