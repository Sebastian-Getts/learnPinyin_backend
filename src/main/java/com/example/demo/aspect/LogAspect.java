package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author sebastian
 * @date 07/06/2020 18:09
 */
@Configuration
@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void logBefore(JoinPoint joinPoint) {
        if (StringUtils.isEmpty(joinPoint.getArgs())) {
            log.info("at log before ===>>> {} parameters: empty!", joinPoint.getSignature().getName());
        } else {
            log.info("at log before ===>>> {} parameters: {}", joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
        }
    }


    @AfterReturning(value = "log()", returning = "object")
    public void logAfter(JoinPoint joinPoint, Object object) {
        log.info("at log after ===>>> {} returning: {}", joinPoint.getSignature().getName(), object);
    }

    @AfterThrowing(value = "log()", throwing = "exception")
    public void logThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("at log throwing ====>>> {} error at: {}", joinPoint.getSignature().getName(), exception);
    }
}
