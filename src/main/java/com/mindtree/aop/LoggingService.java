package com.mindtree.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingService {

	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * com.mindtree.services.ShoppingCartService.get*())")
	public void getPointCut() {
	}

	@Before("getPointCut()")
	public void loggingBeforeGetter(JoinPoint joinPoint) {
		logger.info("Method with signature called " + joinPoint.getSignature());
		System.out.println("Method with signature called " + joinPoint.getSignature());
	}
}
