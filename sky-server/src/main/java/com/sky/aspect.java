package com.sky;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class aspect {
    //切入点

    @Pointcut("execution(* com.sky.mapper.*.*(..))&&@annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    //通知
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行公共字段填充");
        //1.获取注解属性
        MethodSignature signature =  (MethodSignature)joinPoint.getSignature(); //获取代理的方法签名,joinPoint.getSignature()//MethodSignature向下转型
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获取方法本身,获取方法上的注解,获取注解上的属性
        OperationType value = autoFill.value();

        //2.通过反射拿到方法的参数对象
        Object[] args = joinPoint.getArgs();
        if (args==null||args.length==0){
            return;
        }
        Object arg = args[0];
        //3.获取声明方法并在参数上运行
 if(value==OperationType.INSERT){
     //判断注解属性值进行不同的业务
     //如果是插入操作，四个公共字段都需要赋值
   arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class)
           .invoke(arg,LocalDateTime.now());

            arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class)
                    .invoke(arg, BaseContext.getCurrentId());
            arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class)
                    .invoke(arg,LocalDateTime.now());

            arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class)
                    .invoke(arg,BaseContext.getCurrentId());

        }
 else if (value==OperationType.UPDATE){
     arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class)
             .invoke(arg,LocalDateTime.now());
     arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class)
             .invoke(arg,BaseContext.getCurrentId());
 }
        log.info("进行了公共字段赋值，赋值后为：{}",arg);
    }


}
