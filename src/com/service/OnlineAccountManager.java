package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import com.bean.Account;

public class OnlineAccountManager {

    private static Map<String, Session> accountSessions = new ConcurrentHashMap<String, Session>();

    public static void regist(String name, Session session){
	accountSessions.put(name, session);
    }
    
    public static void delete(String name){
	accountSessions.remove(name);
    }
    
    public static void delete(Session session){
	delete(getName(session));
    }

    public static String getName(Session session) {
	for (Entry<String, Session> entry : accountSessions.entrySet()) {
	    if (entry.getValue().equals(session)) {
		return entry.getKey();
	    }
	}
	return null;
    }
    
    public static Session getSession(String name) {
	return accountSessions.get(name);
    }
    
    public static List<String> getOnlineAccountsName(){
	return new ArrayList<>(accountSessions.keySet());
    }
    
    public static int getOnlineNum(){
	return accountSessions.size();
    }
}
