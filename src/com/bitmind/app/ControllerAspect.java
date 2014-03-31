package com.bitmind.app;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

	@AfterReturning(value = "execution(* com.bitmind.controller.*.*(..))", returning = "result")
	public void afterRequest(Object result) throws Throwable {

	}

}
