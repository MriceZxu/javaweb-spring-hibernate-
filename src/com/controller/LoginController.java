package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Account;
import com.bean.TalkContent;
import com.service.AccountManager;
import com.service.OnlineAccountManager;

@Controller
public class LoginController {
    
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String login(ModelMap model, Account account) {
	account = AccountManager.login(account);
	if (account != null) {
	    TalkContent talkContent = new TalkContent();
	    talkContent.setSrcAccount(account);
	    talkContent.setTargetAccountName("");
	    model.addAttribute("talkContent", talkContent);
	    model.addAttribute("onlineNum", OnlineAccountManager.getOnlineNum());
	    model.addAttribute("accounts", OnlineAccountManager.getOnlineAccountsName());
	    return "main";
	}
	model.addAttribute("warnMessage", AccountManager.ERROR_LOGIN);
	return "login";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String getLoginJsp(ModelMap model) {
	model.addAttribute("account", new Account());
	model.addAttribute("warnMessage", "");
	return "login";
    }
}
