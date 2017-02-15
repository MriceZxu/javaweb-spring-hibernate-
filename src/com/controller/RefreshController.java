package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.TalkContent;
import com.service.OnlineAccountManager;
import com.service.TalkManager;

@Controller
public class RefreshController {

    @RequestMapping(method = RequestMethod.POST, path = "/refresh")
    public String refresh(ModelMap model, @RequestParam("srcName") String srcName,
	    @RequestParam("targetName") String targetName) {
	TalkContent talkContent = new TalkContent();
	talkContent.setTargetAccountName(targetName);
	talkContent.setSrcAccountName(srcName);
	model.addAttribute("talkContent", talkContent);
	model.addAttribute("talks", TalkManager.getTalksStr(srcName, targetName));
	model.addAttribute("onlineNum", OnlineAccountManager.getOnlineNum());
	model.addAttribute("accounts", OnlineAccountManager.getOnlineAccountsName());
	return "main";
    }
}
