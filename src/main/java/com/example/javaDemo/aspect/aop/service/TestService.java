package com.example.javaDemo.aspect.aop.service;

import org.springframework.stereotype.Service;


@Service
public class TestService {
    public void aopTest(int i){
        System.out.println("测试aop test 整型"+i);
    }

    public void aopTest1(long l){
        System.out.println("测试aop test1 长整型"+l);
    }
}
