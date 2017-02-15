package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.controller.bean.RegistAccount;
import com.service.AccountManager;

@Controller
public class RegistController {

    @RequestMapping(method = RequestMethod.POST, path = "/regist")
    public String regist(ModelMap model, RegistAccount registAccount) {
	if (!registAccount.canRegist()) {
	    model.addAttribute("warnMessage", registAccount.getErrorMessage());
	    model.addAttribute("name", registAccount.getName());
	    return "regist";
	}
	if (AccountManager.save(registAccount)) {
	    model.addAttribute("warnMessage", AccountManager.SUCCESS_SAVE);
	    return "regist";
	} else {
	    model.addAttribute("warnMessage", AccountManager.ERROR_SAVE);
	    return "regist";
	}
    }

    @RequestMapping(method = RequestMethod.GET, path = "/regist")
    public String getRegistPage(ModelMap model) {
	model.addAttribute("registAccount", new RegistAccount());
	return "regist";
    }

}
