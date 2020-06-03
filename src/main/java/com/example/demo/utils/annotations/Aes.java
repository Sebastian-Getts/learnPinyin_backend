package com.example.demo.utils.annotations;

import java.lang.annotation.*;

/**
 * @author sebastian
 * @date 2020/5/10 21:41
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aes {

    boolean encryptOutput() default true;
}
