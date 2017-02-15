package com.dao;

import com.bean.Account;

public interface IAccountDao extends ICommonDao<Account> {
    Account getByName(String name);
    Account getByName(Account account);
}
