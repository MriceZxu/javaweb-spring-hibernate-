package com.service;

import com.bean.Account;
import com.controller.bean.RegistAccount;
import com.dao.IAccountDao;

/**
 * 管理用户登录、注册、信息获取
 * @author zwh
 *
 */
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
    
    /**
     * 是否已经注册（数据库是否存在改名字）
     * @param name
     * @return
     */
    public static boolean isRegisted(String name){
	Account account = accountDao.getByName(name);
	return account == null ? false : true;
    }
    
    /**
     * 保存到数据库
     * @param registAccount
     * @return
     */
    public static boolean save(RegistAccount registAccount){
	return accountDao.save(registAccount.toAccount());
    }
    
    /**
     * 检测账户名字与密码是否匹配
     * @param account
     * @return
     */
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
