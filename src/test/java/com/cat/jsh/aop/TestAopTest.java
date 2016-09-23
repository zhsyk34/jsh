package com.cat.jsh.aop;

import com.cat.jsh.dao.InitTestDao;
import org.junit.Test;

import javax.annotation.Resource;

public class TestAopTest extends InitTestDao {

	@Resource
	private TestAop testAop;

	@Test
	public void save() throws Exception {
		System.out.println(testAop);
		String s = testAop.save();
		System.out.println(s);
	}

}