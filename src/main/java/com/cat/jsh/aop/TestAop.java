package com.cat.jsh.aop;

import org.springframework.stereotype.Component;

@Component
public class TestAop {

	public String save(String first, String second) {
		return first + "-" + second;
	}

	public String save() {
		return "good.";
	}

}
