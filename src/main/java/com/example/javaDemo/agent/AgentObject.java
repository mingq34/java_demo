package com.example.javaDemo.agent;

import com.example.javaDemo.agent.object.Impl.Object1;
import com.example.javaDemo.agent.object.Interface1;
import org.springframework.stereotype.Component;

@Component
public class AgentObject implements Interface1 {
    Object1 object1;

    public AgentObject(Object1 o){
        this.object1=o;
    }

    @Override
    public void doSomething() {
        System.out.println("agent do something");
        object1.doSomething();
    }
}
