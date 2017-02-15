package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bean.Account;
import com.bean.TalkContent;
import com.dao.ITalkContentDao;
import com.hql.HQLGenerator;
import com.utils.time.TimeInterval;

@Repository
public class TalkContentDao extends CommonDao<TalkContent> implements ITalkContentDao {

    private static final String SRCACCOUNT_PROP_NAME = "srcAccount";
    private static final String TARGETACCOUNT_PROP_NAME = "targetAccount";

    public TalkContentDao() {
	super(new TalkContent());
    }

    @Override
    public List<TalkContent> getBySrcAccount(Account account) {
	return this.queryList(HQLGenerator.generateSingleEqualQueryHql(this.CLASS_SIMPLENAME,
		SRCACCOUNT_PROP_NAME, String.valueOf(account.getId())));
    }

    @Override
    public List<TalkContent> getByTargetAccount(Account account) {
	return this.queryList(HQLGenerator.generateSingleEqualQueryHql(this.CLASS_SIMPLENAME,
		TARGETACCOUNT_PROP_NAME, String.valueOf(account.getId())));
    }

    @Override
    public List<TalkContent> getTenContentByAccounts(Account account, Account otherAccount, int id) {
	return this.queryList(HQLGenerator.generateGetContentByAccounts(account, otherAccount));
    }

    @Override
    public List<TalkContent> getTenContentByAccounts(Account account, Account otherAccount, TimeInterval timeInterval,
	    int id) {
	return this.queryList(HQLGenerator.generateGetContentByAccountAndTime(account, otherAccount, timeInterval));
    }


}
