package com.cat.jsh.kit;

import java.util.List;

public class DaoKit {

	private static String namespace(String className, String methodName) {
		return namespace(className, methodName, true);
	}

	/**
	 * @param isFace 是否将类名抽象为接口名
	 */
	private static String namespace(String className, String methodName, boolean isFace) {
		if (className == null || className.isEmpty() || methodName == null || methodName.isEmpty()) {
			return null;
		}
		if (isFace) {
			try {
				className = Class.forName(className).getInterfaces()[0].getName();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		return className + "." + methodName;
	}

	/**
	 * 默认顺序
	 */
	private static int findIndex(StackTraceElement[] elements, Class clazz) {
		return findIndex(elements, clazz, false);
	}

	/**
	 * @param desc 是否倒序查询
	 */
	private static int findIndex(StackTraceElement[] elements, Class clazz, boolean desc) {
		/*for (int i = 0; i < elements.length; i++) {
			System.out.println(i + ":" + elements[i].getClassName() + "." + elements[i].getMethodName());
		}*/

		String className;
		if (desc) {
			for (int i = elements.length - 1; i > -1; i--) {
				className = elements[i].getClassName();
				System.out.println(i + ":" + className + "." + elements[i].getMethodName());
				if (className.equals(clazz.getName())) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < elements.length; i++) {
				className = elements[i].getClassName();
				System.out.println(i + ":" + className + "." + elements[i].getMethodName());
				if (className.equals(clazz.getName())) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * @param clazz         基准类,查找起点
	 * @param classForward  类调用栈顶层次
	 * @param classOrder    顺序查询:为最近调用对象 A->X1->X2->C ==> X2,倒序查询:为最远调用对象 A->X1->X2->C ==> X1
	 * @param methodForward 方法调用栈顶层次
	 * @param methodOrder   同classOrder
	 * @param face          是否用接口替代
	 * @return 调用路径:包名+方法名
	 */
	//TODO classOrder == methodOrder 时可优化
	public static String findSpace(Class clazz, int classForward, boolean classOrder, int methodForward, boolean methodOrder, boolean face) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		String className, methodName;

		int classIndex = findIndex(elements, clazz, classOrder);
		if (classIndex == -1) {
			return null;
		}

		int methodIndex = findIndex(elements, clazz, methodOrder);
		if (methodIndex == -1) {
			return null;
		}

		//TODO 考虑方向+/-
		int classLevel = classIndex + classForward, methodLevel = methodIndex + methodForward;
		if (classLevel > -1 && classLevel < elements.length && methodLevel > -1 && methodLevel < elements.length) {
			className = elements[classLevel].getClassName();
			methodName = elements[methodLevel].getMethodName();
			return namespace(className, methodName, face);
		}
		return null;
	}

	/**
	 * 倒序搜寻,查询到第一个立即返回,查找次数一般较多 2016-09-23
	 */
	public static String findDesc(Class clazz) {

		//TODO
		/*Match match = new Match() {
			@Override
			public boolean match(List<String> list) {
				return false;
			}
		};*/

		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		int index = findIndex(elements, clazz, true);
		String className = elements[++index].getClassName(), methodName = elements[index].getMethodName();
		return namespace(className, methodName, true);
	}

	/**
	 * 顺序搜寻,直至不是类内部方法调用,速度较快 2016-09-22
	 */
	public static String findAsc(Class clazz) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		int index = -1;
		for (int i = 0; i < elements.length; i++) {
			String name = elements[i].getClassName();
			System.out.println(i + ":" + name + "." + elements[i].getMethodName());
			if (name.equals(clazz.getName())) {
				if (i + 1 < elements.length) {
					name = elements[i + 1].getClassName();
					System.out.println("search next for check.it's : " + name + "." + elements[i + 1].getMethodName());
					if (!name.equals(clazz.getName())) {
						index = i;
						break;
					}
				} else {
					index = i;
					break;
				}
			}
		}

		if (index == -1 || index == elements.length - 1) {
			return null;
		}

		index++;//调用者
		String className = elements[index].getClassName(), methodName = elements[index].getMethodName();
		return namespace(className, methodName, true);
	}

	/**
	 * @param value 主键值
	 * @return 是否已经持久化
	 */
	public static boolean hasPersistent(Object value) {
		if (value == null) {
			return false;
		}

		Class clazz = value.getClass();
		if (clazz == String.class) {
			return ((String) value).trim().length() > 0;
		}
		if (clazz == int.class || clazz == Integer.class) {
			return (Integer) value > 0;
		}
		return (clazz == long.class || clazz == Long.class) && (Long) value > 0;
	}

	public interface Match {
		boolean match(List<String> list);
	}
}
