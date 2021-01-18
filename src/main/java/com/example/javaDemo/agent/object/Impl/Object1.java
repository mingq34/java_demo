package com.example.javaDemo.agent.object.Impl;

import com.example.javaDemo.agent.object.Interface1;
import org.springframework.stereotype.Component;

@Component
public class Object1 implements Interface1 {

    @Override
    public void doSomething() {
        System.out.println("object do something");
    }
}
