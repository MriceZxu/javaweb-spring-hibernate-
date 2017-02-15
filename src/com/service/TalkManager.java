package com.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Account;
import com.bean.TalkContent;
import com.dao.IAccountDao;
import com.dao.ITalkContentDao;

public class TalkManager {
    @Autowired
    private static IAccountDao accountDao;
    @Autowired
    private static ITalkContentDao talkContentDao;
    
    private static Deque<TalkContent> talks = new ConcurrentLinkedDeque<>();
    
    public static ITalkContentDao getTalkContentDao() {
        return talkContentDao;
    }

    public static void setTalkContentDao(ITalkContentDao talkContentDao) {
        TalkManager.talkContentDao = talkContentDao;
    }

    public static IAccountDao getAccountDao() {
	return accountDao;
    }

    public static void setAccountDao(IAccountDao accountDao) {
	TalkManager.accountDao = accountDao;
    }
    
    public static void saveTalkInDatabase(String srcAccountName, String targetAccountName, String talk) {
	TalkContent talkContent = new TalkContent();
	Account srcAccount = accountDao.getByName(srcAccountName);
	Account targetAccount = accountDao.getByName(targetAccountName);
	if (srcAccount == null || targetAccount == null || talk == null || talk.equals("")) {
	    return;
	}
	talkContent.setSrcAccount(srcAccount);
	talkContent.setTargetAccount(targetAccount);
	talkContent.setTalk(talk);
	talks.addLast(talkContent);
	talkContentDao.save(talkContent);
    }
    
    public static void saveTalksWithoutDatabase(List<TalkContent> talkContents){
	talks.addAll(talkContents);
    }
    
    public static List<TalkContent> getHistory(String accountName, String otherAccountName){
	Account account = accountDao.getByName(accountName);
	Account otherAccount = accountDao.getByName(otherAccountName);
	List<TalkContent> talks = talkContentDao.getTenContentByAccounts(account, otherAccount, 0);
	return talks;
    }

    public static List<TalkContent> getTalks(String accountName, String otherAccountName) {
	List<TalkContent> result = new ArrayList<>();
	for (TalkContent talk : talks) {
	    if (talk.isTheirTalk(accountName, otherAccountName)) {
		result.add(new TalkContent(talk));
	    }
	}
	return result;
    }

    public static List<String> getTalksStrList(String accountName, String otherAccountName) {
	List<String> result = new ArrayList<>();
	for (TalkContent talkContent : getTalks(accountName, otherAccountName)) {
	    result.add(talkContent.getMessage());
	}
	return result;
    }
    
    public static String getTalksStr(String accountName, String otherAccountName) {
	List<String> talks = getTalksStrList(accountName, otherAccountName);
	String result = "";
	for(String talk : talks){
	    result += talk + "\n";
	}
	return result.length() > 0 ? result.substring(0, result.length() - 1) : result;
    }

    public static List<TalkContent> getTalks(String name) {
	List<TalkContent> result = new ArrayList<>();
	for (TalkContent talk : talks) {
	    if (talk.containAccount(name)) {
		result.add(new TalkContent(talk));
	    }
	}
	return result;
    }

    public static List<String> getTalksStrList(String name) {
	List<String> result = new ArrayList<>();
	for (TalkContent talkContent : getTalks(name)) {
	    result.add(talkContent.getMessage());
	}
	return result;
    }
    
    public static String getTalksStr(String name){
	List<String> talks = getTalksStrList(name);
	String result = "";
	for(String temp : talks){
	    result += temp + "\n";
	}
	result = result.length() > 0 ? result.substring(0, result.length() - 1) : result;
	return result;
    }

}
