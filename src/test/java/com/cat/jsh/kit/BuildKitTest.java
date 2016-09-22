package com.cat.jsh.kit;

import com.cat.jsh.entity.Role;
import org.junit.Test;

public class BuildKitTest {
	@Test
	public void build() throws Exception {
		String str = "D:\\iwork\\jsh\\src\\main\\resources\\mapper\\";
		BuildKit.build(Role.class, "roleId", str + Role.class.getSimpleName() + ".xml");
	}

}