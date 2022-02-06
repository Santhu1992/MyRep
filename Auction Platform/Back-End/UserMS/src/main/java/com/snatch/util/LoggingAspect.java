package com.snatch.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getSimpleName());

    @Before("execution(* com.snatch.service.UserServiceImpl.*(..))")
    public void logAfterEnteringServiceMethods(JoinPoint joinPoint){
        logger.info("Entering service method {}",joinPoint.getSignature());
    }

    @Before("execution(* com.snatch.controller.UserController.*(..))")
    public void logAfterEnteringControllerMethods(JoinPoint joinPoint){
        logger.info("Entering controller method {}",joinPoint.getSignature());
    }

}
