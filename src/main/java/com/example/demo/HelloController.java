package com.example.demo;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author xxz
 * Created on 04/06/2018
 */
@RestController
public class HelloController {

    @RequestMapping(value = "user/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello,user";
    }

    @RequestMapping(value = "bug/hello")
    public String aloha(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "hello,bug";
    }


    @RequestMapping(value = "user/login")
    public String login(){
        return "log in!!!!!";
    }
}
