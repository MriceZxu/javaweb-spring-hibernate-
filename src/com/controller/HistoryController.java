package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.TalkContent;
import com.service.OnlineAccountManager;
import com.service.TalkManager;

@Controller
public class HistoryController {

    @RequestMapping(method = RequestMethod.POST, value = "/history")
    public String getHistoryMessage(ModelMap model, @RequestParam("srcName") String srcName,
	    @RequestParam("targetName") String targetName) {
	TalkContent talkContent = new TalkContent();
	talkContent.setTargetAccountName(targetName);
	talkContent.setSrcAccountName(srcName);
	model.addAttribute("talkContent", talkContent);
	List<TalkContent> talkContents = TalkManager.getHistory(srcName, targetName);
	TalkManager.saveTalksWithoutDatabase(talkContents);
	model.addAttribute("talks", TalkManager.getTalksStr(srcName, targetName));
	model.addAttribute("onlineNum", OnlineAccountManager.getOnlineNum());
	model.addAttribute("accounts", OnlineAccountManager.getOnlineAccountsName());
	return "main";
    }
}
