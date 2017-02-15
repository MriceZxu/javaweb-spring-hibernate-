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

/**
 * 聊天内容管理
 * 
 * @author zwh
 *
 */
public class TalkManager {
    @Autowired
    private static IAccountDao accountDao;
    @Autowired
    private static ITalkContentDao talkContentDao;
    // 容量
    @Autowired
    private static int max_size;

    // 服务器开始执行后，用户发送过的聊天内容
    private static Deque<TalkContent> talks = new ConcurrentLinkedDeque<>();

    public static int getMax_size() {
	return max_size;
    }

    public static void setMax_size(int max_size) {
	TalkManager.max_size = max_size;
    }

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

    private static void addToMem(List<TalkContent> talkContents) {
	if (talks.size() > max_size) {
	    talks.clear();
	}
	talks.addAll(talkContents);
    }

    private static void addToMem(TalkContent talkContent) {
	List<TalkContent> talkContents = new ArrayList<>();
	talkContents.add(talkContent);
	addToMem(talkContents);
    }

    /**
     * 保存聊天内容到数据库和内存中
     * 
     * @param srcAccountName
     * @param targetAccountName
     * @param talk
     */
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
	addToMem(talkContent);
	talkContentDao.save(talkContent);
    }

    /**
     * 保存聊天内容到内存中
     * 
     * @param talkContents
     */
    public static void saveTalksWithoutDatabase(List<TalkContent> talkContents) {
	addToMem(talkContents);
    }

    /**
     * 从数据库中获取历史内容
     * 
     * @param accountName
     * @param otherAccountName
     * @return
     */
    public static List<TalkContent> getHistory(String accountName, String otherAccountName) {
	Account account = accountDao.getByName(accountName);
	Account otherAccount = accountDao.getByName(otherAccountName);
	List<TalkContent> talks = talkContentDao.getTenContentByAccounts(account, otherAccount, 0);
	return talks;
    }

    /**
     * 从保存在内存的聊天内容中获取这两个用户的聊天内容，以TalkContent格式返回
     * 
     * @param accountName
     * @param otherAccountName
     * @return
     */
    public static List<TalkContent> getTalks(String accountName, String otherAccountName) {
	List<TalkContent> result = new ArrayList<>();
	for (TalkContent talk : talks) {
	    if (talk.isTheirTalk(accountName, otherAccountName)) {
		result.add(new TalkContent(talk));
	    }
	}
	return result;
    }

    /**
     * 从保存在内存的聊天内容中获取这两个用户的聊天内容，以String 列表形式返回
     * 
     * @param accountName
     * @param otherAccountName
     * @return
     */
    public static List<String> getTalksStrList(String accountName, String otherAccountName) {
	List<String> result = new ArrayList<>();
	for (TalkContent talkContent : getTalks(accountName, otherAccountName)) {
	    result.add(talkContent.getMessage());
	}
	return result;
    }

    /**
     * 从保存在内存的聊天内容中获取这两个用户的聊天内容，以String 形式返回
     * 
     * @param accountName
     * @param otherAccountName
     * @return
     */
    public static String getTalksStr(String accountName, String otherAccountName) {
	List<String> talks = getTalksStrList(accountName, otherAccountName);
	String result = "";
	for (String talk : talks) {
	    result += talk + "\n";
	}
	return result.length() > 0 ? result.substring(0, result.length() - 1) : result;
    }
}
