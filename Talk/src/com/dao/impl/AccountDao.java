package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bean.Account;
import com.dao.IAccountDao;
import com.hql.HQLGenerator;

@Repository
public class AccountDao extends CommonDao<Account> implements IAccountDao {

    private static final String NAME_PROP_NAME = "name";
    
    public AccountDao() {
	super(new Account());
    }

    @Override
    public Account getByName(String name) {
	return this.queryUnique(HQLGenerator.generateSingleEqualQueryHql(this.CLASS_SIMPLENAME, NAME_PROP_NAME, name));
    }

    @Override
    public Account getByName(Account account) {
	return this.getByName(account.getName());
    }

}
