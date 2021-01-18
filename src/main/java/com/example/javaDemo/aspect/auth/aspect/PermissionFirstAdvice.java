package com.example.javaDemo.aspect.auth.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class PermissionFirstAdvice {
    @Pointcut("@annotation(com.example.javaDemo.aspect.auth.PermissionAnnotation)")
    private void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(System.currentTimeMillis());

        //获取请求参数
        Object[] objects = joinPoint.getArgs();
        String userName = (String) objects[0];

        if (!userName.equals("admin")) {
            return "失败";
        }
        return joinPoint.proceed();
    }

}
