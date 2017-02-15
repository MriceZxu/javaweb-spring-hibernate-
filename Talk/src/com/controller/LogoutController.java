package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.OnlineAccountManager;

@Controller
public class LogoutController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout(String name){
	OnlineAccountManager.delete(name);
	return "redirect:login.do";
    }
}
