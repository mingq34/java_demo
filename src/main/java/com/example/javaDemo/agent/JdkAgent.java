package com.example.javaDemo.agent;

import com.example.javaDemo.agent.object.Impl.Object1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkAgent implements InvocationHandler {
    Object o;
    public JdkAgent(Object o){
        this.o=o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk agent do something");
        return method.invoke(o,args);
    }
}
