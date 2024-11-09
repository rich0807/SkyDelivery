package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//元注解 注解上的注解
//@Target注解指明注解能在哪使用
@Target({ElementType.METHOD})
//@Retention注解指明注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
