package com.controller.bean;

import com.bean.Account;
import com.service.AccountManager;

public class RegistAccount extends Account {

    private String passwordAgain = "";
    private static final String EXITED_NAME = "该账户名字已被注册";
    private static final String ERROR_PASSWORD = "密码不一致";
    private String errorMessage = "";
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public RegistAccount() {
	
    }

    @Override
    public String toString() {
	return "RegistAccount [passwordAgain=" + passwordAgain + ", Name=" + getName() + ", Password="
		+ getPassword() + "]";
    }

    public String getPasswordAgain() {
	return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
	this.passwordAgain = passwordAgain;
    }

    @Override
    public String getClassSimpleName() {
	return RegistAccount.class.getSimpleName();
    }
    
    public boolean canRegist(){
	if(AccountManager.isRegisted(this.getName())){
	    this.errorMessage = EXITED_NAME;
	    return false;
	}
	System.out.println(this);
	if(! this.passwordAgain.equals(this.getPassword())){
	    this.errorMessage = ERROR_PASSWORD;
	    return false;
	}
	return true;
    }
    
    public Account toAccount(){
	Account account = new Account();
	account.setName(this.getName());
	account.setPassword(passwordAgain);
	return account;
    }
}
