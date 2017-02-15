package com.service;

import java.util.HashSet;
import java.util.Set;

import com.bean.Account;
import com.controller.bean.RegistAccount;
import com.dao.IAccountDao;

public class AccountManager {
    
    public static final String ERROR_SAVE = "注册出现问题，请再试一遍";
    public static final String SUCCESS_SAVE = "注册成功";
    public static final String ERROR_LOGIN = "登录失败，请检查你的账户名与密码";
    private static IAccountDao accountDao;

    public static IAccountDao getAccountDao() {
        return accountDao;
    }

    public static void setAccountDao(IAccountDao accountDao) {
        AccountManager.accountDao = accountDao;
    }
    
    public static boolean isExitName(String name){
	Account account = accountDao.getByName(name);
	return ! (account == null);
    }
    
    public static boolean save(RegistAccount registAccount){
	return accountDao.save(registAccount.toAccount());
    }
    
    public static Account login(Account account){
	String inputPassword = account.getPassword();
	account = accountDao.getByName(account);
	if(account != null){
	    if(inputPassword.equals(account.getPassword())){
		return account;
	    }
	}
	return null;
    }
}
