package com.dao;

import java.util.List;

import com.bean.Account;
import com.bean.TalkContent;
import com.utils.time.TimeInterval;

public interface ITalkContentDao extends ICommonDao<TalkContent> {
    List<TalkContent> getBySrcAccount(Account account);
    List<TalkContent> getByTargetAccount(Account account);
    /**
     * 获取10条这两个用户之间的对话，按照时间先后排序，并且大于指定的id
     * @param srcAccount
     * @param targetAccount
     * @return
     */
    List<TalkContent> getTenContentByAccounts(Account account, Account otherAccount, int id);
    /**
     * 获取10条指定时间范围内的这两个用户之间的对话，按照时间先后排序，并且大于指定的id
     * @param srcAccount
     * @param targetAccount
     * @return
     */
    List<TalkContent> getTenContentByAccounts(Account account, Account otherAccount, TimeInterval timeInterval, int id);
}
