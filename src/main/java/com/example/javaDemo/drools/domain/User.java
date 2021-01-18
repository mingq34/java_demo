package com.example.javaDemo.drools.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String age;
    private String address;
    private String phone;
    private String sex;
    private String flag;
}
