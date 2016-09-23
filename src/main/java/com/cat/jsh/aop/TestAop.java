package com.cat.jsh.aop;

import org.springframework.stereotype.Component;

@Component
public class TestAop {

	public String save() {
		return "正常执行任务...";
	}

}
