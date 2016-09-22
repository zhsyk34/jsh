package com.cat.jsh.kit;

import sun.reflect.Reflection;

import java.lang.reflect.Method;

public class CallKit {

	public static void main(String[] args) {
		testMethod(new Reflections());
		testMethod(new Threads());
		testMethod(new Throwables());
		testMethod(new Securitys());
		testMethod(new Suns());
	}

	private static void testMethod(Caller caller) {
		String title = caller.getClass().getSimpleName();
		long count = 1;
		long startTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
			System.out.print(caller.getCallerClassName(1) + " ");
		}
		long endTime = System.nanoTime();
		System.out.println(title + ": " + (endTime - startTime) / count + " ms.");
	}

	/**
	 * Abstract class for testing different methods of getting the caller class name
	 */
	private interface Caller {
		String getCallerClassName(int callStackDepth);
	}

	/**
	 * Uses the internal Reflection class
	 */
	@Deprecated
	private static class Reflections implements Caller {
		@Override
		public String getCallerClassName(int callStackDepth) {
			return Reflection.getCallerClass(callStackDepth).getName();
		}
	}

	/**
	 * Get a stack trace from the current thread
	 */
	private static class Threads implements Caller {
		@Override
		public String getCallerClassName(int callStackDepth) {
			return Thread.currentThread().getStackTrace()[callStackDepth].getClassName();
		}
	}

	/**
	 * Get a stack trace from a new Throwable
	 */
	private static class Throwables implements Caller {
		@Override
		public String getCallerClassName(int callStackDepth) {
			return new Throwable().getStackTrace()[callStackDepth].getClassName();
		}
	}

	/**
	 * Use the SecurityManager.getClassContext()
	 */
	private static class Securitys implements Caller {
		private static final MySecurityManager mySecurityManager = new MySecurityManager();

		@Override
		public String getCallerClassName(int callStackDepth) {
			return mySecurityManager.getCallerClassName(callStackDepth);
		}

		/**
		 * A custom security manager that exposes the getClassContext() information
		 */
		static class MySecurityManager extends SecurityManager {
			public String getCallerClassName(int callStackDepth) {
				return getClassContext()[callStackDepth].getName();
			}
		}
	}

	private static class Suns implements Caller {
		private static final boolean SUPPORTED;
		private static final Method METHOD;

		static {
			Method caller;
			try {
				final Class<?> sunReflectionClass = Class.forName("sun.reflect.Reflection");
				caller = sunReflectionClass.getDeclaredMethod("getCallerClass", int.class);
			} catch (final Exception e) {
				System.out.println("sun.reflect.Reflection.getCallerClass is not supported." + e);
				caller = null;
			}
			SUPPORTED = caller != null;
			METHOD = caller;
		}

		@Override
		public String getCallerClassName(int callStackDepth) {
			/*try {
				int i = 0;
				Class<?> clazz = (Class<?>) METHOD.invoke(null, i);
				while (clazz != null) {
					clazz = (Class<?>) METHOD.invoke(null, ++i);
				}
			} catch (Exception e) {
				System.out.println("Exception navigating through frames");
			}*/

			/*if (callStackDepth < 0) {
				throw new IndexOutOfBoundsException(Integer.toString(callStackDepth));
			}*/
			if (SUPPORTED) {
				try {
					Class<?> clazz = (Class<?>) METHOD.invoke(null, callStackDepth + 1);
					return clazz.getName();
				} catch (final Exception e) {
					System.out.println("Error in ReflectionUtil.getCallerClass({}), index<" + callStackDepth + ">" + ", exception< " + e + ">");
					return null;
				}
			}
			return null;
		}
	}

}