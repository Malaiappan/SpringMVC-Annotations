package com.springmvc.sample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TranslationAspect {

	public TranslationAspect() {
		System.out.println("in");
	}

	@Before("execution(@com.springmvc.sample.Translatable * *(..))")
	public void doBeforeTask(JoinPoint joinPoint) {
		System.out.println("aop through annotation");
	}

	@Around("execution(* com.springmvc.sample.MainController.getPassenger(..))")
	public Object handledMemoize(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around");
		return pjp.proceed();
	}
}
