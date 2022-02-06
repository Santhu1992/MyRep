package com.snatch.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getSimpleName());

    @Before("execution(* com.snatch.service.BidServiceImpl.*(..))")
    public void logAfterEnteringServiceMethods(JoinPoint joinPoint){
        logger.info("Entering service method {} with arg {}",joinPoint.getSignature(),Arrays.stream(joinPoint.getArgs()).map(o->o.toString()).collect(Collectors.joining(", ")));
    }

    @Before("execution(* com.snatch.controller.BidController.*(..))")
    public void logAfterEnteringControllerMethods(JoinPoint joinPoint){
        logger.info("Entering controller method {} with arg {}",joinPoint.getSignature(), Arrays.stream(joinPoint.getArgs()).map(o->o.toString()).collect(Collectors.joining(", ")));
    }

}
