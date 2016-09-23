package com.cat.jsh.dao.base;

import com.cat.jsh.kit.DaoKit;
import org.springframework.stereotype.Repository;

/**
 * 仅供直接调用,嵌套调用会产生statement错误
 */
@Repository
public class TemplateSession extends CustomSession {

	/**
	 * 1.未重写namespace()方法时,调用父类继承而来的方法session.*时,将直接执行父类方法,此时的this为子类,搜索不到
	 * 2.简单的super.namespace()会导致。。。
	 * 3.Override 采用findAsc因为调用方法依然为父类...
	 * 4.父类abstract namespace()方法...
	 * 5.findDesc()在AOP时被代理方法阻断出错
	 */
	@Override
	protected String namespace() {
		DaoKit.findDesc(this.getClass());
		return "com.cat.jsh.dao.RoleDao.save";
	}
}