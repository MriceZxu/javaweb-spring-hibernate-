package com.bean;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.utils.time.TimeFormatter;

@Entity
@Table(name = "tbl_talk")
public class TalkContent extends CommonBean {

    @Column(name = "content")
    private String talk;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "srcAccountId")
    private Account srcAccount;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "targetAccountId")
    private Account targetAccount;
    @Column(name = "time", updatable = false, insertable = false)
    private Timestamp time;
    
    public TalkContent(){
	
    }
    
    public TalkContent(TalkContent talkContent) {
	this.id = talkContent.id;
	this.talk = talkContent.talk;
	this.srcAccount = new Account(talkContent.srcAccount);
	this.targetAccount = new Account(talkContent.targetAccount);
	this.time = talkContent.time;
    }

    @Override
    public String toString() {
	return "TalkContent [Content=" + talk + ", srcAccount=" + srcAccount + ", targetAccount=" + targetAccount
		+ ", time=" + TimeFormatter.getTime(time) + ", id=" + id + "]";
    }
    
    public String getMessage(){
	return this.getSrcAccountName() + ">" + this.talk;
    }

    public String getTalk() {
	return talk;
    }

    public void setTalk(String talk) {
	this.talk = talk;
    }

    public Account getSrcAccount() {
	return srcAccount;
    }

    public void setSrcAccount(Account srcAccount) {
	this.srcAccount = srcAccount;
    }

    public Account getTargetAccount() {
	return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
	this.targetAccount = targetAccount;
    }

    public String getTime() {
	return TimeFormatter.getTime(time);
    }

    @Override
    public String getClassSimpleName() {
	return TalkContent.class.getSimpleName();
    }
    
    public boolean isTheirTalk(String accountName, String otherAccountName){
	if((this.getSrcAccountName().equals(accountName) && this.getTargetAccountName().equals(otherAccountName))
		|| (this.getSrcAccountName().equals(otherAccountName) && this.getTargetAccountName().equals(accountName))){
	    return true;
	}
	return false;
    }
    
    public boolean containAccount(String name){
	if(this.getSrcAccountName().equals(name) || this.getTargetAccountName().equals(name)){
	    return true;
	}
	return false;
    }

    public String getSrcAccountName() {
	return this.srcAccount == null ? "" : this.srcAccount.getName();
    }

    public String getTargetAccountName() {
	return this.targetAccount == null ? "" : this.targetAccount.getName();
    }
    
    public void setSrcAccountName(String srcAccountName) {
	this.srcAccount = new Account();
	this.srcAccount.setName(srcAccountName);
    }

    public void setTargetAccountName(String targetAccountName) {
	this.targetAccount = new Account();
	this.targetAccount.setName(targetAccountName);
    }
}
