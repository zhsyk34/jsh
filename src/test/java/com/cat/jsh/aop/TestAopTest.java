package com.cat.jsh.aop;

import com.cat.jsh.dao.InitTestDao;
import org.junit.Test;

import javax.annotation.Resource;

public class TestAopTest extends InitTestDao {

	@Resource
	private TestAop testAop;
//	private TestAop testAop = new TestAop();

	@Test
	public void save() throws Exception {
		System.out.println(testAop.save("work", "sleep"));
//		System.out.println(testAop.save());
	}

	@Test
	public void save2() throws Exception {
		System.out.println(testAop);
		long count = 1;
		long begin = System.nanoTime();
		for (int i = 0; i < count; i++) {
			testAop.save("work", "sleep");//63270+72244+69693
		}
		long end = System.nanoTime();

		System.out.println("base " + (end - begin) / 1000 + " .us");
	}

}