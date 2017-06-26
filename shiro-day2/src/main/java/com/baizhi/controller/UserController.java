package com.baizhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/6/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(String name,String password){
        System.out.println("controller层的用户名"+name);
        System.out.println("controller层的密码"+password);

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(name,password));
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        } catch(IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "redirect:/index.jsp";
    }
}
