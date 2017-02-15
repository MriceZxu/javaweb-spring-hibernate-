package com.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bean.TalkContent;
import com.service.OnlineAccountManager;
import com.service.TalkManager;

@Controller
@ServerEndpoint("/talk")
public class TalkWebSocket extends TextWebSocketHandler {

    private static final String REGIST = "regist";
    private static final String REGIST_PATTERN = REGIST + "\\((.*?)\\)";
    private static final String SRCACCOUNT_PATTERN = "from\\((.*?)\\)";
    private static final String TARGETACCOUNT_PATTERN = "to\\((.*?)\\)";
    private static final String TALK_PATTERN = "talk\\((.*?)\\)";
    
    /**
     * 接受客户端发送的信息，如果为注册信息则注册，否则为聊天信息，如果为有效聊天信息则保存到数据库，并且发送给目标账户
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
	message = URLDecoder.decode(message, "UTF-8");
	if(message.startsWith(REGIST)){
	    OnlineAccountManager.regist(regexGroupOne(REGIST_PATTERN, message), session);
	}else{
	    String srcAccountName = regexGroupOne(SRCACCOUNT_PATTERN, message);
	    String targetAccountName = regexGroupOne(TARGETACCOUNT_PATTERN, message);
	    String talk = regexGroupOne(TALK_PATTERN, message);
	    if(targetAccountName == null){
		return ;
	    }
	    Session targetSession = OnlineAccountManager.getSession(targetAccountName);
	    if(targetSession != null){
		TalkManager.saveTalkInDatabase(srcAccountName, targetAccountName, talk);
		targetSession.getBasicRemote().sendText(srcAccountName + ">" + talk);
	    }
	}
    }

    @OnOpen
    public void onOpen(Session session) {
	
    }

    @OnClose
    public void onClose(Session session) {
	OnlineAccountManager.delete(session);
    }
    
    private String regexGroupOne(String patternStr, String str){
	Pattern pattern = Pattern.compile(patternStr);
	Matcher matcher = pattern.matcher(str);
	return matcher.find() ? matcher.group(1) : null;
    }
    /*
     * @OnError public void onError(Session session) {
     * 
     * }
     */
}
