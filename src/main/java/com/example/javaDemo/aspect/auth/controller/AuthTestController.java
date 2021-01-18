package com.example.javaDemo.aspect.auth.controller;

import com.example.javaDemo.aspect.auth.PermissionAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permission")
public class AuthTestController {
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @PermissionAnnotation()
    public String getGroupList(@RequestParam String userName) {
        return "Hello "+userName;
    }
}
