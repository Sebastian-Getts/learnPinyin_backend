package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.entities.CommonResult;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.annotations.Aes;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author sebastian
 * @date 2020/5/10 21:49
 */
@Configuration
@Slf4j
@Aspect
public class AesConfig {

    @Pointcut("@within(com.example.demo.utils.annotations.Aes)||" +
            "@annotation(com.example.demo.utils.annotations.Aes)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public CommonResult<JSON> around(ProceedingJoinPoint proceedingJoinPoint) {
        CommonResult<JSON> result = null;
        Object[] args = proceedingJoinPoint.getArgs();
        Object target = proceedingJoinPoint.getTarget();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        try {
//            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
//            Aes aes = method.getAnnotation(Aes.class);
//            if (StringUtils.isEmpty(args)) {
//                log.debug("{} has no args", proceedingJoinPoint.getSignature().getName());
//            } else {
//                String data = (String) args[0];
//                AesUtil.desEncrypt(data);
//            }
//
//            result = (CommonResult<JSON>) proceedingJoinPoint.proceed(args);
//
//            if (aes.encryptOutput()) {
//                log.debug("{} needs to encrypt its output...", proceedingJoinPoint.getSignature().getName());
//            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
