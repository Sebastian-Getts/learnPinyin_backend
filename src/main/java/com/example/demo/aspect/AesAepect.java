package com.example.demo.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entities.CommonResult;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.annotation.Aes;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Slf4j
@Component
@Aspect
public class AesAepect {

    @Pointcut("@within(com.example.demo.utils.annotation.Aes)||@annotation(com.example.demo.utils.annotation.Aes)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public CommonResult<String> around(ProceedingJoinPoint point) {
        CommonResult<String> result = null;
        Object[] args = point.getArgs();
        Object target = point.getTarget();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        return result;
    }

    private String getMethodName(String name) {
        String first = name.substring(0, 1);
        String last = name.substring(1);
        first = first.toUpperCase();
        return "get" + first + last;
    }
}
