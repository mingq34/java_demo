package com.example.javaDemo;

import com.example.javaDemo.agent.AgentObject;
import com.example.javaDemo.agent.Cglib;
import com.example.javaDemo.agent.JdkAgent;
import com.example.javaDemo.agent.object.Impl.Object1;
import com.example.javaDemo.agent.object.Interface1;
import com.example.javaDemo.aspect.aop.service.TestService;
import com.example.javaDemo.drools.domain.User;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@SpringBootTest
class JavaDemoApplicationTests {
    @Autowired
    TestService testService;
    @Test
    void contextLoads() {
        testService.aopTest(1);
        testService.aopTest1(1000L);
    }
    //静态代理手动代理
    @Test
    void agent(){
        Object1 object1=new Object1();
        AgentObject agentObject=new AgentObject(object1);
        agentObject.doSomething();
    }

    //jde动态代理
    @Test
    void jdkAgent(){
        Interface1 interface1=new Object1();
        InvocationHandler invocationHandler=new JdkAgent(interface1);
        ClassLoader classLoader=interface1.getClass().getClassLoader();
        Class[] classes=interface1.getClass().getInterfaces();
        Interface1 interface11=(Interface1) Proxy.newProxyInstance(classLoader,classes,invocationHandler);
        interface11.doSomething();
    }

    //cglib动态代理
    @Test
    void cglib(){
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Object1.class);
        enhancer.setCallback(new Cglib());
        Object1 i=(Object1) enhancer.create();
        i.doSomething();

    }

    @Autowired
    private KieSession session;
    @Test
    void drools() {
        User user = new User();
        user.setAddress("上海");
        user.setAge("20");
        user.setName("echo");
        user.setPhone("136123456");
        user.setSex("1");
        user.setFlag("");
        System.out.println(user);
        // 插入用户
        session.insert(user);
        // 执行规则
        session.fireAllRules();

    }

    /**
     * 记得执行完成之后释放资源
     */
    @AfterEach
    public void runDispose() {
        session.dispose();
    }


}
