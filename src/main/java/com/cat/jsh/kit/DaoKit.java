package com.cat.jsh.kit;

public class DaoKit {

	//private static final ConcurrentMap<String, String> cacheMap = new ConcurrentHashMap<>(); TODO

	//TODO classOrder == methodForward 可优化
	public static String findSpace(Class clazz, int classForward, boolean classOrder, int methodForward, boolean methodOrder) {
		String space = findSpace(clazz, classForward, classOrder, methodForward, methodOrder, true);
		if (space == null) {
			throw new RuntimeException("can't find the method called stack.");
		}
		return space;
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
	public static String findSpace(Class clazz, int classForward, boolean classOrder, int methodForward, boolean methodOrder, boolean face) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		String className, methodName;

		int classIndex = findStackIndex(elements, clazz, classOrder);
		if (classIndex == -1) {
			return null;
		}

		int methodIndex = findStackIndex(elements, clazz, methodOrder);
		if (methodIndex == -1) {
			return null;
		}

		int classLevel = classIndex + classForward, methodLevel = methodIndex + methodForward;
		if (classLevel > -1 && classLevel < elements.length && methodLevel > -1 && methodLevel < elements.length) {
			className = elements[classLevel].getClassName();
			methodName = elements[methodLevel].getMethodName();
			if (face) {
				try {
					className = Class.forName(className).getInterfaces()[0].getName();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				}
			}
			return className + "." + methodName;
		}
		return null;
	}

	private static int findStackIndex(Class clazz) {
		return findStackIndex(clazz, false);
	}

	private static int findStackIndex(Class clazz, boolean desc) {
		//从当前开始递推
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		return findStackIndex(elements, clazz, desc);
	}

	//desc:倒序查询
	private static int findStackIndex(StackTraceElement[] elements, Class clazz, boolean desc) {
		if (desc) {
			for (int i = elements.length - 1; i > -1; i--) {
				if (elements[i].getClassName().equals(clazz.getName())) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < elements.length; i++) {
				if (elements[i].getClassName().equals(clazz.getName())) {
					return i;
				}
			}
		}
		return -1;
	}

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
}
