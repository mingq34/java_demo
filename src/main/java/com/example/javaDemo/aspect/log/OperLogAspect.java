package com.example.javaDemo.aspect.log;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OperLogAspect {
    //操作切入点
    @Pointcut("@annotation(com.example.javaDemo.aspect.log.OperLog)")
    public void operLogPoinCut() {}

    //正常返回通知
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLog operlog = new OperationLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            OperLog opLog = method.getAnnotation(OperLog.class);
            methodName = className + "." + methodName;
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = JSON.toJSONString(rtnMap);

            operlog.setOperId(IdUtil.randomUUID());
            operlog.setOperModul(opLog.operModul());
            operlog.setOperType(opLog.operType());
            operlog.setOperDesc(opLog.operDesc());
            operlog.setOperMethod(methodName); // 请求方法
            operlog.setOperRequParam(params); // 请求参数
            operlog.setOperRespParam(JSON.toJSONString(keys)); // 返回结果
            operlog.setOperUri(request.getRequestURI()); // 请求URI
            operlog.setOperCreateTime(new Date()); // 创建时间

            //打印日志
            System.out.println(JSON.toJSONString(operlog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //转换request 请求参数
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

}
