package com.example.javaDemo.aspect.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspect {

    //切入点1：匹配xxx类下的方法名以demo开头、参数类型为int的public方法
    @Pointcut("execution(public * com.example.javaDemo.aspect.aop.service.TestService.aopTest*(int))")
    public void matchCondition() {}

    //切入点2：匹配xxx类下的方法名以demo开头、参数类型为long的public方法
    @Pointcut("execution(public * com.example.javaDemo.aspect.aop.service.TestService.aopTest1*(long))")
    public void matchCondition_() {}

    //前置
    @Before("matchCondition()")
    public void before() {
        System.out.println("前置 Before");
    }

    //全局后置
    @After("matchCondition()")
    public void after(){
        System.out.println("后置 after");
    }

    //返回后置
    @AfterReturning("matchCondition()")
    public void afterReturning(){
        System.out.println("返回后置 afterReturning");
    }

    //抛出后置
    @AfterThrowing("matchCondition()")
    public void afterThrowing(){
        System.out.println("抛出后置 afterThrowing");
    }

    @Around("matchCondition_()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        System.out.println("test1 around 前置 before");
        try{
            result = joinPoint.proceed(joinPoint.getArgs());//获取参数
            System.out.println("test1 around 返回后置 after");
        } catch (Throwable e) {
            System.out.println("test1 around 抛出后置 after exception");
            e.printStackTrace();
        } finally {
            System.out.println("test1 around 后置finally");
        }
        return result;
    }
}
